package br.ufes.economiacircularmvp.presenter.usuario;

import br.ufes.economiacircularmvp.adapter.ILogAdapter;
import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.presenter.PrincipalPresenter;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.service.AutenticacaoService;
import br.ufes.economiacircularmvp.view.IPrincipalView;
import br.ufes.economiacircularmvp.view.LoginView;
import br.ufes.economiacircularmvp.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class LoginPresenter {
    
    private LoginView view;
    private AutenticacaoService autenticacaoService;
    private CadastroUsuarioPresenter cadastroUsuarioPresenter;
    private IUsuarioRepository repository;
    private ILogAdapter log;
    
    public LoginPresenter(IUsuarioRepository repository, ILogAdapter log){
        this.view = new LoginView();
        this.repository = repository;
        this.autenticacaoService = new AutenticacaoService(repository);
        this.log = log;
        view.setVisible(false);
        configurar();
    }
    
    private void configurar(){
        view.getLoginButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    autenticar();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Falha: " + ex.getMessage());
                }

            }
        });
        view.getCadastrarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastrar();
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
        view.setVisible(true);
    }
    
    private void limparCampos() {
        view.getUsuarioField().setText("");
        view.getSenhaField().setText("");
    }
    
    private void autenticar() throws SQLException{
        String nomeUsuario = view.getUsuarioField().getText();
        String senha = new String(view.getSenhaField().getPassword());
        Usuario usuario = new Usuario("",nomeUsuario, senha,"","","");

        autenticacaoService.autenticar(usuario);

        if (usuario.isAutenticado()) {
            try {
                //Instanciaria a presenter da tela principal
                // passando o usuario autenticado como parametro
                JOptionPane.showMessageDialog(view, "Usuário com e-mail "
                        + usuario.getUsuario()+ " autenticado\n Simulando a abertura da janela principal");
                Thread.sleep(4);
                log.logSucesso("Autenticar", nomeUsuario, "Login bem-sucedido!");
                view.dispose();
                // 1. Cria a View principal
                IPrincipalView principalView = new PrincipalView();

                // 2. Cria o Presenter principal e injeta a View
                PrincipalPresenter principalPresenter = new PrincipalPresenter(principalView);

                // 3. Inicia a aplicação
                principalPresenter.iniciar();
                
            } catch (InterruptedException ex) {
                
                throw new RuntimeException(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(view, "Usuário ou Senha Inválidos","" ,JOptionPane.INFORMATION_MESSAGE);
            log.logFalha("Autenticar", nomeUsuario, "Usuário ou Senha Inválidos");
        }
    }
    
    private void cadastrar() throws SQLException{
        view.dispose();
        this.cadastroUsuarioPresenter = new CadastroUsuarioPresenter(this.repository, this.log);
        
    }

    private void cancelar() {
        view.dispose();
    }
}
