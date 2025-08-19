package br.ufes.economiacircularmvp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroPerfilView extends JInternalFrame implements ICadastroPerfilView {

    private JTextField nomeField;
    private JTextField contatoField;
    private JCheckBox compradorCheck;
    private JCheckBox vendedorCheck;
    private JButton salvarButton;
    private JButton cancelarButton;

    public CadastroPerfilView() {
        super("Cadastro de Novo Perfil", true, true, true, true);
        setSize(400, 250);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // --- Componentes ---
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1; nomeField = new JTextField(20); add(nomeField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Contato (Tel/Email):"), gbc);
        gbc.gridx = 1; contatoField = new JTextField(20); add(contatoField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Perfis:"), gbc);
        JPanel perfilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        compradorCheck = new JCheckBox("Comprador");
        vendedorCheck = new JCheckBox("Vendedor");
        perfilPanel.add(compradorCheck);
        perfilPanel.add(vendedorCheck);
        gbc.gridx = 1; add(perfilPanel, gbc);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");
        botoesPanel.add(salvarButton);
        botoesPanel.add(cancelarButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(botoesPanel, gbc);
    }

    @Override
    public String getNome() { return nomeField.getText(); }

    @Override
    public String getContato() { return contatoField.getText(); }

    @Override
    public boolean isCompradorSelecionado() { return compradorCheck.isSelected(); }

    @Override
    public boolean isVendedorSelecionado() { return vendedorCheck.isSelected(); }

    @Override
    public void fechar() { dispose(); }

    @Override
    public void limparCampos() {
        nomeField.setText("");
        contatoField.setText("");
        compradorCheck.setSelected(false);
        vendedorCheck.setSelected(false);
    }

    @Override
    public void exibirMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }

    @Override
    public void adicionarAcaoSalvar(ActionListener listener) {
        salvarButton.addActionListener(listener);
    }

    @Override
    public void adicionarAcaoCancelar(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
}
