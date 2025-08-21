package br.ufes.economiacircularmvp.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DetalhesItemView extends JInternalFrame implements IDetalhesItemView {

    public DetalhesItemView() {
        super("Detalhes do Item", true, true, true, true);
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel de informações
        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        infoPanel.setBorder(new TitledBorder("Informações do Item"));
        infoPanel.add(new JLabel("<html><b>Item:</b> Camiseta de Algodão</html>"));
        infoPanel.add(new JLabel("<html><b>Preço Final:</b> R$ 45,00</html>"));
        infoPanel.add(new JLabel("<html><b>Defeitos:</b> Desgaste por pilling acentuado</html>"));
        infoPanel.add(new JLabel("<html><b>CO₂e evitado:</b> 2.47 kg</html>"));
        infoPanel.add(new JLabel("<html><b>Circularidade:</b> 0.90</html>"));
        
        // Painel de Oferta
        JPanel ofertaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ofertaPanel.add(new JLabel("Sua Oferta (R$):"));
        ofertaPanel.add(new JTextField(10));
        ofertaPanel.add(new JButton("Enviar Oferta"));
        infoPanel.add(ofertaPanel);
        
        infoPanel.add(new JButton("Registrar Denúncia"));
        
        mainPanel.add(infoPanel);

        // Painel da Linha do Tempo
        JPanel timelinePanel = new JPanel(new BorderLayout());
        timelinePanel.setBorder(new TitledBorder("Linha do Tempo"));
        JTextArea timelineArea = new JTextArea("20/08/2025: Publicado\n");
        timelineArea.setEditable(false);
        timelinePanel.add(new JScrollPane(timelineArea), BorderLayout.CENTER);
        
        mainPanel.add(timelinePanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
}