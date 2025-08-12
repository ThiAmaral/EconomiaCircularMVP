package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.service.AutenticacaoService;
import br.ufes.economiacircularmvp.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class LoginPresenter {
    
    private LoginView view;
    private AutenticacaoService autenticacaoService;
    private CadastroUsuarioPresenter cadastroUsuarioPresenter;
    private IUsuarioRepository repository;
    
    public LoginPresenter(IUsuarioRepository repository){
        this.view = new LoginView();
        this.repository = repository;
        this.autenticacaoService = new AutenticacaoService(repository);
        view.setVisible(false);
        configurar();
    }
    
    public void configurar(){
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
    
    public void limparCampos() {
        view.getUsuarioField().setText("");
        view.getSenhaField().setText("");
    }
    
    private void autenticar() throws SQLException{
        String nomeUsuario = view.getUsuarioField().toString();
        String senha = view.getSenhaField().toString();
        Usuario usuario = new Usuario("",nomeUsuario, senha,"");

        autenticacaoService.autenticar(usuario);

        if (usuario.isAutenticado()) {
            try {
                //Instanciaria a presenter da tela principal
                // passando o usuario autenticado como parametro
                JOptionPane.showMessageDialog(view, "Usuário com e-mail "
                        + usuario.getUsuario()+ " autenticado\n Simulando a abertura da janela principal");
                Thread.sleep(4);
                view.dispose();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

    }
    
    private void cadastrar() throws SQLException{
        view.dispose();
        this.cadastroUsuarioPresenter = new CadastroUsuarioPresenter(this.repository);
        
    }

    private void cancelar() {
        view.dispose();
    }
}
