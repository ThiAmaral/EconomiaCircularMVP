/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.service.AutenticacaoService;
import br.ufes.economiacircularmvp.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public final class LoginPresenter {
    
    private LoginView view;
    private AutenticacaoService autenticacaoService;
    private CadastroUsuarioPresenter cadastroUsuarioPresenter;
    private IUsuarioRepository repository;
    
    public LoginPresenter(LoginView view, IUsuarioRepository repository){
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
    
    private void autenticar() throws SQLException{
        String nomeUsuario = view.getUsuario();
        String senha = view.getSenha();
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
        view.fechar();
        this.cadastroUsuarioPresenter = new CadastroUsuarioPresenter(this.repository);
        
    }

    private void cancelar() {
        view.fechar();
    }
}
