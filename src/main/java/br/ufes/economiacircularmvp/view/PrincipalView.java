package br.ufes.economiacircularmvp.view;

import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PrincipalView extends JFrame implements IPrincipalView{
    private JDesktopPane desktopPane;
    private JMenuItem menuItemCadastrarPerfil;

    public PrincipalView() {
        setTitle("Sistema Principal - Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        criarMenu();
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Cadastros");
        menuItemCadastrarPerfil = new JMenuItem("Criar Novo Perfil");

        menuArquivo.add(menuItemCadastrarPerfil);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);
    }

    @Override
    public void exibir() {
        setVisible(true);
    }

    @Override
    public void adicionarJanelaInterna(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    @Override
    public void adicionarAcaoMenuCadastro(ActionListener listener) {
        menuItemCadastrarPerfil.addActionListener(listener);
    }
}
