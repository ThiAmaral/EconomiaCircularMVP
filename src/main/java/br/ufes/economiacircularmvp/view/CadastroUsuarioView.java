package br.ufes.economiacircularmvp.view;

import br.ufes.economiacircularmvp.command.IProjetoCommand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CadastroUsuarioView extends JFrame {

    private JTextField nomeField;
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JTextField contatoField;
    private JCheckBox perfilVendedorCheck;
    private JCheckBox perfilCompradorCheck;
    private JCheckBox isAdminCheck;
    private JButton salvarButton;

    private IProjetoCommand salvarCommand;

    public CadastroUsuarioView() {
        setTitle("Cadastro de Novo Usuário");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Não fecha a aplicação toda
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

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1; senhaField = new JPasswordField(25); add(senhaField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Contato (Telefone/Email):"), gbc);
        gbc.gridx = 1; contatoField = new JTextField(25); add(contatoField, gbc);

        // --- Checkboxes de Perfil ---
        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Perfis:"), gbc);
        JPanel perfisPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        perfilVendedorCheck = new JCheckBox("Vendedor");
        perfilCompradorCheck = new JCheckBox("Comprador");
        perfisPanel.add(perfilVendedorCheck);
        perfisPanel.add(perfilCompradorCheck);
        gbc.gridx = 1; add(perfisPanel, gbc);

        // --- Checkbox de Admin ---
        gbc.gridx = 0; gbc.gridy = 5; add(new JLabel("Administrador:"), gbc);
        gbc.gridx = 1; isAdminCheck = new JCheckBox("É administrador do sistema"); add(isAdminCheck, gbc);

        // --- Botão de Salvar ---
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        salvarButton = new JButton("Salvar Cadastro");
        add(salvarButton, gbc);

        // Ação do botão executa o Command
        salvarButton.addActionListener((ActionEvent e) -> {
            if (salvarCommand != null) {
                salvarCommand.executar();
            }
        });
    }

    // --- MÉTODOS PÚBLICOS PARA O PRESENTER ---

    public String getNome() { return nomeField.getText(); }
    public String getUsuario() { return usuarioField.getText(); }
    public String getSenha() { return new String(senhaField.getPassword()); }
    public String getContato() { return contatoField.getText(); }
    public boolean isPerfilVendedorSelecionado() { return perfilVendedorCheck.isSelected(); }
    public boolean isPerfilCompradorSelecionado() { return perfilCompradorCheck.isSelected(); }
    public boolean isAdminSelecionado() { return isAdminCheck.isSelected(); }

    public void setSalvarCommand(IProjetoCommand command) {
        this.salvarCommand = command;
    }
    
    public void setAdminCheckbox(boolean selecionado, boolean habilitado) {
        this.isAdminCheck.setSelected(selecionado);
        this.isAdminCheck.setEnabled(habilitado);
    }
    
    public void limparCampos() {
        nomeField.setText("");
        usuarioField.setText("");
        senhaField.setText("");
        contatoField.setText("");
        perfilVendedorCheck.setSelected(false);
        perfilCompradorCheck.setSelected(false);
        isAdminCheck.setSelected(false);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
