package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.view.CadastroUsuarioView;
import br.ufes.economiacircularmvp.command.IProjetoCommand;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public final class CadastroUsuarioPresenter {
    
    private final CadastroUsuarioView view;
    private final IUsuarioRepository repository; // Repositório que acessa o banco de dados

    public CadastroUsuarioPresenter(IUsuarioRepository repository) throws SQLException{
        this.view = new CadastroUsuarioView();
        view.setVisible(false);
        this.repository = repository;
        limparCampos();
        configurar();
        // Cria o comando que chama o método 'salvarUsuario' do próprio Presenter.
        IProjetoCommand comandoSalvar = this::salvarUsuario; 

        // Injeta o comando na View.
        //view.setSalvarCommand(comandoSalvar);
    }

    public void configurar() throws SQLException {
        view.getSalvarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvarUsuario();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Falha: " + ex.getMessage());
                }

            }
        });
        view.getCancelarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Falha: " + ex.getMessage());
                }

            }
        });
        // 1. Verificar se existem usuários no sistema
        boolean naoExistelUsuarios = repository.buscarTodosUsuarios().isEmpty() ;

        // 2. Se for o primeiro usuário (total == 0)
        if (naoExistelUsuarios) {
            // 3. O Presenter manda a View se ajustar:
            //    marca a caixa "isAdmin" e a desabilita para o usuário não poder mudar.
            view.isAdminSelecionado().setVisible(true);
            view.isAdminSelecionado().setSelected(true);
            view.isAdminSelecionado().setEnabled(false);
            exibirMensagem("Info: O primeiro usuário cadastrado será um Administrador.");
        } else {
            // 4. Caso contrário, a caixa fica habilitada para escolha manual.
            view.isAdminSelecionado().setVisible(false);
            view.isAdminSelecionado().setSelected(false);
            view.isAdminSelecionado().setEnabled(false);
        }

        view.setVisible(true);
    }
   
    public void limparCampos() {
        view.getNomeField().setText("");
        view.getUsuarioField().setText("");
        view.getSenhaField().setText("");
        view.getContatoField().setText("");
        view.isPerfilVendedorSelecionado().setSelected(false);
        view.isPerfilVendedorSelecionado().setSelected(false);
        view.isAdminSelecionado().setSelected(false);
    }
    
    private void salvarUsuario() {
        // 1. O Presenter busca os dados da View
        String nome = view.getNomeField().getText();
        String usuario = view.getUsuarioField().getText();
        String senha = new String(view.getSenhaField().getPassword());
        String contato = view.getContatoField().getText();
        boolean isAdmin = view.isAdminSelecionado().isSelected();
        
        List<String> perfis = new ArrayList<>();
        
        if (view.isPerfilVendedorSelecionado().isSelected()) {
            perfis.add("Vendedor");
        }
        if (view.isPerfilCompradorSelecionado().isSelected()) {
            perfis.add("Comprador");
        }
        // ... buscar todos os outros campos

       // 2. O Presenter executa a lógica de negócio (validações)
        if (nome.isBlank() || usuario.isBlank() || senha.isBlank()) {
            exibirMensagem("Erro: Os campos Nome, Usuário (login) e Senha são obrigatórios.");
            return; // Interrompe a execução se a validação falhar
        }
        
        if (perfis.isEmpty()) {
            exibirMensagem("Erro: Selecione ao menos um perfil (Vendedor ou Comprador).");
            return;
        }

        try {
            // Validação extra: verifica se o login já existe
            if (repository.buscarUsuarioPorLogin(usuario).isPresent()) {
                exibirMensagem("Erro: O login '" + usuario + "' já está em uso. Por favor, escolha outro.");
                return;
            }

            // 3. Monta o objeto de modelo (UsuarioDTO)
            // Assumindo que seu UsuarioDTO tem um construtor que aceita esses parâmetros.
            // Adapte se o construtor for diferente.
            UsuarioDTO novoUsuario = new UsuarioDTO(nome, usuario, senha, contato, isAdmin);

            // ... e manda salvar no repositório.
            repository.adicionarUsuario(novoUsuario);

            // 4. Dá feedback ao usuário através da View
            exibirMensagem("Usuário cadastrado com sucesso!");
            limparCampos(); // Limpa os campos para um novo cadastro
            view.dispose(); // Fecha a janela de cadastro após o sucesso

            } catch (SQLException e) {
                // Trata possíveis erros de banco de dados
                exibirMensagem("Erro ao salvar no banco de dados: " + e.getMessage());
            } catch (Exception e) {
                // Trata outros erros inesperados
                exibirMensagem("Ocorreu um erro inesperado: " + e.getMessage());
            }
        // 4. Dá feedback ao usuário através da View
        exibirMensagem("Usuário cadastrado com sucesso!");
        view.dispose();
        LoginPresenter loginPresenter = new LoginPresenter(repository);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(view, mensagem,"" ,JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cancelar() {
        view.dispose();
    }
}
