package br.ufes.economiacircularmvp.view;

import java.awt.GridLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class DashboardAdminView extends JInternalFrame implements IDashboardAdminView {

    public DashboardAdminView() {
        super("Dashboard de Métricas (Admin)", true, true, true, true);
        setSize(900, 700);
        setLayout(new GridLayout(2, 2, 10, 10));

        // Placeholder para os gráficos
        add(createChartPlaceholder("Distribuição de Níveis de Reputação"));
        add(createChartPlaceholder("Evolução Semanal de GWP Evitado"));
        add(createChartPlaceholder("Participação de Materiais"));
        add(createChartPlaceholder("Ranking de Crescimento"));
    }

    private JPanel createChartPlaceholder(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(title));
        panel.add(new JLabel("Gráfico (" + title + ") será exibido aqui.", SwingConstants.CENTER));
        // Aqui você adicionaria o ChartPanel do JFreeChart
        return panel;
    }
}