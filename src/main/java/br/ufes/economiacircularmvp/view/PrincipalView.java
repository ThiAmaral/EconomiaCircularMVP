package br.ufes.economiacircularmvp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class PrincipalView extends JFrame implements IPrincipalView {
    private JDesktopPane desktopPane;
    private JMenuItem menuItemCadastroPerfil;
    private JMenuItem menuItemNovoItem;
    private JMenuItem menuItemCatalogo;
    private JMenuItem menuItemConfiguracoes;
    private JMenuItem menuItemDashboard; // Para o admin
    private JMenu menuRelatorios; // Para o admin
    private JLabel rodapeLabel;


    public PrincipalView() {
        setTitle("Sistema de Economia Circular MVP");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        criarMenu();
        criarRodape();
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Cadastros
        JMenu menuCadastros = new JMenu("Cadastros");
        menuItemCadastroPerfil = new JMenuItem("Gerenciar Perfis");
        menuItemNovoItem = new JMenuItem("Novo Item no Catálogo");
        menuCadastros.add(menuItemCadastroPerfil);
        menuCadastros.add(menuItemNovoItem);
        
        // Menu Consultas
        JMenu menuConsultas = new JMenu("Consultas");
        menuItemCatalogo = new JMenuItem("Catálogo de Itens");
        menuConsultas.add(menuItemCatalogo);

        // Menu Relatórios (visível apenas para admin)
        menuRelatorios = new JMenu("Relatórios");
        menuItemDashboard = new JMenuItem("Dashboard de Métricas");
        menuRelatorios.add(menuItemDashboard);
        menuRelatorios.setVisible(false); // Inicia invisível

        // Menu Configurações
        JMenu menuConfiguracoes = new JMenu("Sistema");
        menuItemConfiguracoes = new JMenuItem("Configurações");
        menuConfiguracoes.add(menuItemConfiguracoes);

        menuBar.add(menuCadastros);
        menuBar.add(menuConsultas);
        menuBar.add(menuRelatorios);
        menuBar.add(menuConfiguracoes);
        setJMenuBar(menuBar);
    }
    
    private void criarRodape() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 20));
        statusPanel.setLayout(new BorderLayout());
        rodapeLabel = new JLabel("Usuário não logado");
        rodapeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(rodapeLabel, BorderLayout.CENTER);
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
    public void adicionarAcaoMenuCadastroPerfil(ActionListener listener) {
        menuItemCadastroPerfil.addActionListener(listener);
    }

    @Override
    public void adicionarAcaoMenuNovoItem(ActionListener listener) {
        menuItemNovoItem.addActionListener(listener);
    }

    @Override
    public void adicionarAcaoMenuCatalogo(ActionListener listener) {
        menuItemCatalogo.addActionListener(listener);
    }

    @Override
    public void adicionarAcaoMenuConfiguracoes(ActionListener listener) {
        menuItemConfiguracoes.addActionListener(listener);
    }

    @Override
    public void adicionarAcaoMenuDashboard(ActionListener listener) {
        menuItemDashboard.addActionListener(listener);
    }
    
    @Override
    public JLabel getRodapeLabel() {
        return rodapeLabel;
    }
    
    // Método para controlar a visibilidade do menu de admin
    public JMenu getMenuRelatorios() {
        return menuRelatorios;
    }
}