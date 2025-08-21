package br.ufes.economiacircularmvp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManterItemView extends JInternalFrame implements IManterItemView {
    private JTextField idcField, subcategoriaField, tamanhoField, corField, composicaoField, massaField, precoBaseField;
    private JComboBox<String> tipoPecaCombo, estadoConservacaoCombo;
    private List<JCheckBox> defeitosCheckBoxes;
    private JLabel precoFinalLabel, gwpLabel, mciLabel;
    private JButton salvarButton, excluirButton, cancelarButton;

    public ManterItemView() {
        super("Publicar / Editar Item", true, true, true, true);
        setSize(550, 650);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        // Componentes do formulário
        idcField = addFormField("ID-C (se revenda):", new JTextField(20), gbc, y++);
        tipoPecaCombo = addFormField("Tipo de Peça:", new JComboBox<>(new String[]{"Vestuário", "Calçados", "Bolsas e mochilas", "Bijuterias e acessórios"}), gbc, y++);
        subcategoriaField = addFormField("Subcategoria:", new JTextField(20), gbc, y++);
        tamanhoField = addFormField("Tamanho:", new JTextField(20), gbc, y++);
        corField = addFormField("Cor Predominante:", new JTextField(20), gbc, y++);
        composicaoField = addFormField("Composição Principal:", new JTextField(20), gbc, y++);
        massaField = addFormField("Massa Estimada (kg):", new JTextField(20), gbc, y++);
        estadoConservacaoCombo = addFormField("Estado de Conservação:", new JComboBox<>(new String[]{"Novo", "Usado", "Com defeito"}), gbc, y++);
        
        // Checkboxes de defeitos
        gbc.gridx = 0; gbc.gridy = y; add(new JLabel("Defeitos:"), gbc);
        JPanel defeitosPanel = new JPanel(new GridLayout(0, 1));
        defeitosCheckBoxes = new ArrayList<>();
        String[] defeitos = {"Rasgo estruturante", "Mancha permanente", "Zíper parcialmente funcional", "Sola sem relevo funcional", "Alça reparada"};
        for (String defeito : defeitos) {
            JCheckBox cb = new JCheckBox(defeito);
            defeitosCheckBoxes.add(cb);
            defeitosPanel.add(cb);
        }
        gbc.gridx = 1; add(new JScrollPane(defeitosPanel), gbc);
        y++;

        precoBaseField = addFormField("Preço-base (R$):", new JTextField(20), gbc, y++);
        
        // Labels para valores calculados
        precoFinalLabel = addInfoLabel("Preço Final Calculado:", "R$ 0.00", gbc, y++);
        gwpLabel = addInfoLabel("GWP Evitado:", "0.00 kg CO₂e", gbc, y++);
        mciLabel = addInfoLabel("Índice de Circularidade:", "0.00", gbc, y++);

        // Botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        salvarButton = new JButton("Salvar");
        excluirButton = new JButton("Excluir");
        cancelarButton = new JButton("Cancelar");
        botoesPanel.add(salvarButton);
        botoesPanel.add(excluirButton);
        botoesPanel.add(cancelarButton);
        gbc.gridy = y; gbc.gridx = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.NONE;
        add(botoesPanel, gbc);
    }

    private <T extends Component> T addFormField(String label, T component, GridBagConstraints gbc, int y) {
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(component, gbc);
        return component;
    }

    private JLabel addInfoLabel(String labelText, String valueText, GridBagConstraints gbc, int y) {
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);
        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 1;
        add(valueLabel, gbc);
        return valueLabel;
    }

    // Implementação dos getters da interface
    @Override public JTextField getIdcField() { return idcField; }
    @Override public JComboBox<String> getTipoPecaCombo() { return tipoPecaCombo; }
    @Override public JTextField getSubcategoriaField() { return subcategoriaField; }
    @Override public JTextField getTamanhoField() { return tamanhoField; }
    @Override public JTextField getCorField() { return corField; }
    @Override public JTextField getComposicaoField() { return composicaoField; }
    @Override public JTextField getMassaField() { return massaField; }
    @Override public JComboBox<String> getEstadoConservacaoCombo() { return estadoConservacaoCombo; }
    @Override public List<JCheckBox> getDefeitosCheckBoxes() { return defeitosCheckBoxes; }
    @Override public JTextField getPrecoBaseField() { return precoBaseField; }
    @Override public JLabel getPrecoFinalLabel() { return precoFinalLabel; }
    @Override public JLabel getGwpLabel() { return gwpLabel; }
    @Override public JLabel getMciLabel() { return mciLabel; }
    @Override public JButton getSalvarButton() { return salvarButton; }
    @Override public JButton getExcluirButton() { return excluirButton; }
    @Override public JButton getCancelarButton() { return cancelarButton; }

    @Override public void adicionarAcaoSalvar(ActionListener listener) { salvarButton.addActionListener(listener); }
    @Override public void adicionarAcaoExcluir(ActionListener listener) { excluirButton.addActionListener(listener); }
    @Override public void adicionarAcaoCancelar(ActionListener listener) { cancelarButton.addActionListener(listener); }
    
    @Override public void fechar() { dispose(); }
    @Override public void exibirMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }
}