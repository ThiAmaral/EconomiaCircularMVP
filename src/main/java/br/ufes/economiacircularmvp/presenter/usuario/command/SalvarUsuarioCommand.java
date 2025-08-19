package br.ufes.economiacircularmvp.presenter.usuario.command;

import br.ufes.economiacircularmvp.command.IProjetoCommand;
import br.ufes.economiacircularmvp.model.Usuario;
import javax.swing.JOptionPane;

public class SalvarUsuarioCommand implements IProjetoCommand {

    @Override
    public void executar() {
    /*
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
    */
    }
}
