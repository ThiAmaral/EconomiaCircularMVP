package br.ufes.economiacircularmvp.presenter.usuario;

import br.ufes.economiacircularmvp.adapter.ILogAdapter;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.view.CadastroUsuarioView;
import br.ufes.economiacircularmvp.command.IProjetoCommand;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import com.pss.senha.validacao.ValidadorSenha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public final class CadastroUsuarioPresenter {
    
    private final CadastroUsuarioView view;
    private final IUsuarioRepository repository;
    private final ILogAdapter log;

    public CadastroUsuarioPresenter(IUsuarioRepository repository, ILogAdapter log) throws SQLException{
        this.view = new CadastroUsuarioView();
        view.setVisible(false);
        this.repository = repository;
        this.log = log;
        limparCampos();
        configurar();
        // Cria o comando que chama o método 'salvarUsuario' do próprio Presenter.
        IProjetoCommand comandoSalvar = this::salvarUsuario; 

        // Injeta o comando na View.
        //view.setSalvarCommand(comandoSalvar);
    }

    private void configurar() throws SQLException {
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
            view.getPerfisLabel().setVisible(false);
            view.isPerfilCompradorSelecionado().setVisible(false);
            view.isPerfilVendedorSelecionado().setVisible(false);
            view.isPerfilCompradorSelecionado().setSelected(false);
            view.isPerfilVendedorSelecionado().setSelected(false);
            view.isPerfilCompradorSelecionado().setEnabled(false);
            view.isPerfilVendedorSelecionado().setEnabled(false);
            view.isAdminSelecionado().setVisible(true);
            view.isAdminSelecionado().setSelected(true);
            view.isAdminSelecionado().setEnabled(false);
            exibirMensagem("Info: O primeiro usuário cadastrado será um Administrador.");
        } else {
            // 4. Caso contrário, a caixa fica habilitada para escolha manual.
            view.getAdminLabel().setVisible(false);
            view.isAdminSelecionado().setVisible(false);
            view.isAdminSelecionado().setSelected(false);
            view.isAdminSelecionado().setEnabled(false);
        }

        view.setVisible(true);
    }
   
    private void limparCampos() {
        view.getNomeField().setText("");
        view.getUsuarioField().setText("");
        view.getSenhaField().setText("");
        view.getTelefoneField().setText("");
        view.getEmailField().setText("");
        view.isPerfilVendedorSelecionado().setSelected(false);
        view.isPerfilVendedorSelecionado().setSelected(false);
        view.isAdminSelecionado().setSelected(false);
    }
    
    private void salvarUsuario() {
        // 1. O Presenter busca os dados da View
        String nome = view.getNomeField().getText();
        String usuario = view.getUsuarioField().getText();
        String senha = new String(view.getSenhaField().getPassword());
        String contato = view.getTelefoneField().getText();
        String email = view.getEmailField().getText();
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
        if (nome.isBlank() || usuario.isBlank()) {
            exibirMensagem("Erro: Os campos Nome, Usuário (login) e Senha são obrigatórios.");
            return; // Interrompe a execução se a validação falhar
        }
        
        if (!isAdmin && perfis.isEmpty()) {
            exibirMensagem("Erro: Selecione ao menos um perfil (Vendedor ou Comprador).");
            return;
        }

        // 1. Chama o método de validação que agora retorna a lista de erros.
        List<String> errosSenha = validarSenha(senha);

        // 2. Verifica se a lista de erros está vazia.
        if (errosSenha.isEmpty()) {
            // Se a lista está vazia, a senha é válida e o processo continua.
            try {
                // Validação extra: verifica se o login já existe
                if (repository.buscarUsuarioPorLogin(usuario).isPresent()) {
                    exibirMensagem("Erro: O login '" + usuario + "' já está em uso. Por favor, escolha outro.");
                    return;
                }

                // Monta o objeto de modelo (UsuarioDTO)
                UsuarioDTO novoUsuario = new UsuarioDTO(nome, usuario, senha, contato, email, isAdmin);

                // ... e manda salvar no repositório.
                repository.adicionarUsuario(novoUsuario);

                // Dá feedback ao usuário através da View
                exibirMensagem("Usuário cadastrado com sucesso!");
                log.logSucesso("Cadastrar", usuario, "Cadastro bem-sucedido!");
                limparCampos(); // Limpa os campos para um novo cadastro
                view.dispose(); // Fecha a janela de cadastro após o sucesso

                // A linha abaixo estava fora do try-catch no seu código original.
                // Movida para cá para garantir que só seja executada em caso de sucesso.
                LoginPresenter loginPresenter = new LoginPresenter(repository, log);

            } catch (SQLException e) {
                // Trata possíveis erros de banco de dados
                exibirMensagem("Erro ao salvar no banco de dados: " + e.getMessage());
            } catch (Exception e) {
                // Trata outros erros inesperados
                exibirMensagem("Ocorreu um erro inesperado: " + e.getMessage());
            }
        } else {
            try {
                // 3. Se a lista contém erros, constrói uma mensagem e a exibe.
                StringBuilder mensagemDeErro = new StringBuilder("Sua senha não é válida. Por favor, corrija os seguintes pontos:\n\n");
                for (String erro : errosSenha) {
                    mensagemDeErro.append("- ").append(erro).append("\n");
                }
                log.logFalha("Cadastrar", usuario, "Campos inválidos");
                exibirMensagem(mensagemDeErro.toString());
            } catch (SQLException ex) {
                System.getLogger(CadastroUsuarioPresenter.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    private List<String> validarSenha(String senha){
        ValidadorSenha validador = new ValidadorSenha();
        return validador.validar(senha);
    }

    private void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(view, mensagem,"" ,JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cancelar() {
        view.dispose();
        LoginPresenter loginPresenter = new LoginPresenter(repository, log);
    }
}
