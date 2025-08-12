package br.ufes.economiacircularmvp.view;

import br.ufes.economiacircularmvp.command.IProjetoCommand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastrarButton;
    private JButton cancelarButton; // Botão adicionado

    private IProjetoCommand loginCommand;

    public LoginView() {
        setTitle("Tela de Login");
        setSize(400, 220); // Aumentei a largura para caber os 3 botões
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Rótulo e campo de texto para o usuário
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Usuário:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        usuarioField = new JTextField(20);
        add(usuarioField, gbc);

        // Rótulo e campo de texto para a senha
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        senhaField = new JPasswordField(20);
        add(senhaField, gbc);

        // Painel para os botões de ação
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        cadastrarButton = new JButton("Cadastrar");
        cancelarButton = new JButton("Cancelar"); // Botão instanciado
        botoesPanel.add(loginButton);
        botoesPanel.add(cadastrarButton);
        botoesPanel.add(cancelarButton); // Botão adicionado ao painel

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(botoesPanel, gbc);
    }

    public JTextField getUsuarioField() { return usuarioField; }
    public JPasswordField getSenhaField() { return senhaField; }
    public JButton getLoginButton() { return loginButton; }
    public JButton getCadastrarButton() { return cadastrarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
    
}