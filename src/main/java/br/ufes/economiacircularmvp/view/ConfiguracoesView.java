package br.ufes.economiacircularmvp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ConfiguracoesView extends JInternalFrame implements IConfiguracoesView {
    private JRadioButton jsonRadio;
    private JRadioButton csvRadio;
    private JButton salvarButton;

    public ConfiguracoesView() {
        super("Configurações do Sistema", true, true, true, true);
        setSize(400, 200);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Selecione o formato de Log:"), gbc);

        jsonRadio = new JRadioButton("JSON");
        csvRadio = new JRadioButton("CSV (Ponto e vírgula)");
        ButtonGroup group = new ButtonGroup();
        group.add(jsonRadio);
        group.add(csvRadio);
        
        JPanel radioPanel = new JPanel();
        radioPanel.add(jsonRadio);
        radioPanel.add(csvRadio);
        
        gbc.gridy = 1;
        add(radioPanel, gbc);

        salvarButton = new JButton("Salvar Configurações");
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(salvarButton, gbc);
    }

    @Override
    public JRadioButton getJsonRadio() {
        return jsonRadio;
    }

    @Override
    public JRadioButton getCsvRadio() {
        return csvRadio;
    }

    @Override
    public void adicionarAcaoSalvar(ActionListener listener) {
        salvarButton.addActionListener(listener);
    }
}