package br.ufes.economiacircularmvp.view;

import br.ufes.economiacircularmvp.command.IProjetoCommand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CadastroUsuarioView extends JFrame {

    private JTextField nomeField;
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JTextField emailField; // NOVO: Campo dedicado para o e-mail
    private JTextField telefoneField; // ALTERADO: Antigo 'contatoField'
    private JLabel perfisLabel;
    private JCheckBox perfilVendedorCheck;
    private JCheckBox perfilCompradorCheck;
    private JLabel adminLabel;
    private JCheckBox isAdminCheck;
    private JButton salvarButton;
    private JButton cancelarButton;

    private IProjetoCommand salvarCommand;

    public CadastroUsuarioView() {
        setTitle("Cadastro de Novo Usuário");
        setSize(450, 430); // Aumentei um pouco a altura para caber o novo campo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // --- Campos de Texto ---
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1; nomeField = new JTextField(25); add(nomeField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Usuário (login):"), gbc);
        gbc.gridx = 1; usuarioField = new JTextField(25); add(usuarioField, gbc);

        // NOVO: Adicionando o campo de e-mail
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1; emailField = new JTextField(25); add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1; senhaField = new JPasswordField(25); add(senhaField, gbc);

        // ALTERADO: Campo agora é específico para telefone
        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; telefoneField = new JTextField(25); add(telefoneField, gbc);

        // --- Checkboxes de Perfil ---
        gbc.gridx = 0; gbc.gridy = 5; perfisLabel = new JLabel("Perfis:"); add(perfisLabel, gbc);
        JPanel perfisPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        perfilVendedorCheck = new JCheckBox("Vendedor");
        perfilCompradorCheck = new JCheckBox("Comprador");
        perfisPanel.add(perfilVendedorCheck);
        perfisPanel.add(perfilCompradorCheck);
        gbc.gridx = 1; add(perfisPanel, gbc);

        // --- Checkbox de Admin ---
        gbc.gridx = 0; gbc.gridy = 6; adminLabel = new JLabel("Administrador:"); add(adminLabel, gbc);
        gbc.gridx = 1; isAdminCheck = new JCheckBox("É administrador do sistema"); add(isAdminCheck, gbc);

        // --- Botões de Ação (Salvar e Cancelar) ---
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        salvarButton = new JButton("Salvar Cadastro");
        cancelarButton = new JButton("Cancelar");
        botoesPanel.add(salvarButton);
        botoesPanel.add(cancelarButton);

        gbc.gridx = 0;
        gbc.gridy = 7; // A posição Y aumentou devido ao novo campo
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botoesPanel, gbc);

        // --- Ações dos Botões ---
        salvarButton.addActionListener((ActionEvent e) -> {
            if (salvarCommand != null) {
                salvarCommand.executar();
            }
        });
    }

    // --- MÉTODOS PÚBLICOS PARA O PRESENTER ---

    public JTextField getNomeField() { return nomeField; }
    public JTextField getUsuarioField() { return usuarioField; }
    public JPasswordField getSenhaField() { return senhaField; }
    public JTextField getEmailField() { return emailField; } // NOVO: Getter para o campo de e-mail
    public JTextField getTelefoneField() { return telefoneField; } // ALTERADO: Getter renomeado
    public JLabel getPerfisLabel() { return perfisLabel; }
    public JCheckBox isPerfilVendedorSelecionado() { return perfilVendedorCheck; }
    public JCheckBox isPerfilCompradorSelecionado() { return perfilCompradorCheck; }
    public JLabel getAdminLabel() { return adminLabel; }
    public JCheckBox isAdminSelecionado() { return isAdminCheck; }
    public JButton getSalvarButton() { return salvarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
}