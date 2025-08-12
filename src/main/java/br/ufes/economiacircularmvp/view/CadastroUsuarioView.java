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
    private JButton cancelarButton; // NOVO: Declaração do botão de cancelar

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

        // --- Botões de Ação (Salvar e Cancelar) ---
        // NOVO: Criando um painel para organizar os botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        salvarButton = new JButton("Salvar Cadastro");
        cancelarButton = new JButton("Cancelar"); // NOVO: Instanciando o botão
        botoesPanel.add(salvarButton);
        botoesPanel.add(cancelarButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botoesPanel, gbc); // NOVO: Adicionando o painel de botões à janela

        // --- Ações dos Botões ---

        // Ação do botão SALVAR executa o Command
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
    public JTextField getContatoField() { return contatoField; }
    public JCheckBox isPerfilVendedorSelecionado() { return perfilVendedorCheck; }
    public JCheckBox isPerfilCompradorSelecionado() { return perfilCompradorCheck; }
    public JCheckBox isAdminSelecionado() { return isAdminCheck; }
    public JButton getSalvarButton() { return salvarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
}