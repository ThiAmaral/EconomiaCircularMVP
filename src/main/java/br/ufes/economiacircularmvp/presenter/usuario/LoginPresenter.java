package br.ufes.economiacircularmvp.presenter.usuario;

import br.ufes.economiacircularmvp.adapter.ILogAdapter;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.presenter.PrincipalPresenter;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.service.AutenticacaoService;
import br.ufes.economiacircularmvp.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JOptionPane;

public final class LoginPresenter {
    
    private LoginView view;
    private AutenticacaoService autenticacaoService;
    private IUsuarioRepository repository;
    private ILogAdapter log;
    
    public LoginPresenter(IUsuarioRepository repository, ILogAdapter log){
        this.view = new LoginView();
        this.repository = repository;
        this.autenticacaoService = new AutenticacaoService(repository);
        this.log = log;
        configurar();
        view.setVisible(true);
    }
    
    private void configurar(){
        view.getLoginButton().addActionListener(e -> {
            try {
                autenticar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Falha no banco de dados: " + ex.getMessage());
            }
        });
        view.getCadastrarButton().addActionListener(e -> {
            try {
                cadastrar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Falha ao abrir cadastro: " + ex.getMessage());
            }
        });
        view.getCancelarButton().addActionListener(e -> cancelar());
    }
    
    private void autenticar() throws SQLException {
        String nomeUsuario = view.getUsuarioField().getText();
        String senha = new String(view.getSenhaField().getPassword());
        
        // Objeto temporário para o serviço de autenticação
        Usuario usuarioParaAutenticar = new Usuario("", nomeUsuario, senha, "", "", "");
        autenticacaoService.autenticar(usuarioParaAutenticar);

        if (usuarioParaAutenticar.isAutenticado()) {
            // Se autenticado, busca o DTO completo para passar para a próxima tela
            Optional<UsuarioDTO> usuarioDTOOpt = repository.buscarUsuarioPorLogin(nomeUsuario);

            if (usuarioDTOOpt.isPresent()) {
                UsuarioDTO usuarioLogado = usuarioDTOOpt.get();
                
                JOptionPane.showMessageDialog(view, "Usuário " + usuarioLogado.getNome() + " autenticado com sucesso!");
                log.logSucesso("Autenticar", nomeUsuario, "Login bem-sucedido!");
                
                view.dispose(); // Fecha a tela de login
                
                // Instancia o Presenter Principal, passando o DTO do usuário logado
                new PrincipalPresenter(usuarioLogado);
                
            } else {
                // Caso raro: autenticado mas não encontrado no banco. Indica inconsistência.
                JOptionPane.showMessageDialog(view, "Erro de inconsistência de dados do usuário.", "Erro Crítico", JOptionPane.ERROR_MESSAGE);
                log.logFalha("Autenticar", nomeUsuario, "Inconsistência de dados: autenticado mas não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Usuário ou Senha Inválidos", "Falha na Autenticação", JOptionPane.ERROR_MESSAGE);
            log.logFalha("Autenticar", nomeUsuario, "Usuário ou Senha Inválidos");
        }
    }
    
    private void cadastrar() throws SQLException {
        view.dispose();
        new CadastroUsuarioPresenter(this.repository, this.log);
    }

    private void cancelar() {
        view.dispose();
        // Opcional: System.exit(0); se o cancelar deve fechar a aplicação
    }
}