package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.dto.ItemDTO;
import br.ufes.economiacircularmvp.repository.IItemRepository;
import br.ufes.economiacircularmvp.view.ICatalogoItensView;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CatalogoItensPresenter {
    private ICatalogoItensView view;
    private IItemRepository itemRepository;

    public CatalogoItensPresenter(ICatalogoItensView view, IItemRepository itemRepository) {
        this.view = view;
        this.itemRepository = itemRepository;
        carregarItens();
    }

    private void carregarItens() {
        try {
            List<ItemDTO> itens = itemRepository.buscarTodos();
            JPanel panel = view.getItensPanel();
            panel.removeAll();

            if (itens.isEmpty()) {
                panel.setLayout(new BorderLayout());
                panel.add(new JLabel("Nenhum item no catálogo.", SwingConstants.CENTER), BorderLayout.CENTER);
            } else {
                for (ItemDTO item : itens) {
                    panel.add(createItemPanel(item));
                }
            }
            
            panel.revalidate();
            panel.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar itens do catálogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createItemPanel(ItemDTO item) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel nomeLabel = new JLabel(item.getTipoPeca() + " - " + item.getSubcategoria(), SwingConstants.CENTER);
        nomeLabel.setFont(nomeLabel.getFont().deriveFont(Font.BOLD));
        panel.add(nomeLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        infoPanel.add(new JLabel(String.format("Preço: R$ %.2f", item.getPrecoFinal())));
        infoPanel.add(new JLabel(String.format("CO₂e evitado: %.2f kg", item.getGwpAvoided())));
        infoPanel.add(new JLabel(String.format("Circularidade: %.2f", item.getMciItem())));
        panel.add(infoPanel, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoesPanel.add(new JButton("Ver Detalhes"));
        botoesPanel.add(new JButton("Fazer Oferta"));
        panel.add(botoesPanel, BorderLayout.SOUTH);

        return panel;
    }
}