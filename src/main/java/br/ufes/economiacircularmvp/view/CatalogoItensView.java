package br.ufes.economiacircularmvp.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CatalogoItensView extends JInternalFrame implements ICatalogoItensView {

    private JPanel itensPanel;

    public CatalogoItensView() {
        super("Catálogo de Itens", true, true, true, true);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));

        // Painel de Filtros
        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtroPanel.setBorder(new TitledBorder("Filtros"));
        filtroPanel.add(new JLabel("Tipo de Peça:"));
        filtroPanel.add(new JComboBox<>(new String[]{"Todos", "Vestuário", "Calçados"}));
        filtroPanel.add(new JButton("Filtrar"));
        add(filtroPanel, BorderLayout.NORTH);

        // Painel com a lista de itens
        itensPanel = new JPanel(new GridLayout(0, 3, 10, 10)); 
        add(new JScrollPane(itensPanel), BorderLayout.CENTER);
    }

    @Override
    public JPanel getItensPanel() {
        return itensPanel;
    }
}