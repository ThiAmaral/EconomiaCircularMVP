package br.ufes.economiacircularmvp.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GerenciarOfertasView extends JInternalFrame implements IGerenciarOfertasView {

    public GerenciarOfertasView() {
        super("Gerenciar Ofertas Recebidas", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JLabel itemLabel = new JLabel("Ofertas para: Camiseta de Algodão", SwingConstants.CENTER);
        itemLabel.setFont(itemLabel.getFont().deriveFont(Font.BOLD));
        itemLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(itemLabel, BorderLayout.NORTH);

        JPanel ofertasPanel = new JPanel();
        ofertasPanel.setLayout(new BoxLayout(ofertasPanel, BoxLayout.Y_AXIS));
        
        // Exemplo de ofertas
        ofertasPanel.add(createOfertaPanel("Comprador A", "R$ 42,50", "20/08/2025 22:30"));
        ofertasPanel.add(createOfertaPanel("Comprador B", "R$ 40,00", "20/08/2025 22:35"));
        
        add(new JScrollPane(ofertasPanel), BorderLayout.CENTER);
    }

    private JPanel createOfertaPanel(String comprador, String valor, String data) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(new JLabel(String.format("<html><b>Comprador:</b> %s | <b>Valor:</b> %s | <b>Data:</b> %s</html>", comprador, valor, data)));
        panel.add(new JButton("Aceitar"));
        panel.add(new JButton("Rejeitar"));
        return panel;
    }
}