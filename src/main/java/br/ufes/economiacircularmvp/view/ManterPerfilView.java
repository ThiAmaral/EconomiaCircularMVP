package br.ufes.economiacircularmvp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ManterPerfilView extends JInternalFrame implements IManterPerfilView {

    public ManterPerfilView() {
        super("Gerenciar Perfis", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel do Perfil de Vendedor
        JPanel vendedorPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        vendedorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        vendedorPanel.add(new JLabel("Nível de Reputação:"));
        vendedorPanel.add(new JLabel("Bronze")); // Placeholder
        vendedorPanel.add(new JLabel("Total de Estrelas:"));
        vendedorPanel.add(new JLabel("0.0")); // Placeholder
        vendedorPanel.add(new JLabel("Vendas Concluídas:"));
        vendedorPanel.add(new JLabel("0")); // Placeholder
        vendedorPanel.add(new JLabel("Benefício Climático:"));
        vendedorPanel.add(new JLabel("0.0 kg CO₂e")); // Placeholder
        
        // Painel do Perfil de Comprador
        JPanel compradorPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        compradorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        compradorPanel.add(new JLabel("Nível de Reputação:"));
        compradorPanel.add(new JLabel("Bronze")); // Placeholder
        compradorPanel.add(new JLabel("Total de Estrelas:"));
        compradorPanel.add(new JLabel("0.0")); // Placeholder
        compradorPanel.add(new JLabel("Compras Concluídas:"));
        compradorPanel.add(new JLabel("0")); // Placeholder
        compradorPanel.add(new JLabel("Selo Verificador Confiável:"));
        compradorPanel.add(new JLabel("Não")); // Placeholder

        tabbedPane.addTab("Perfil de Vendedor", vendedorPanel);
        tabbedPane.addTab("Perfil de Comprador", compradorPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }
}