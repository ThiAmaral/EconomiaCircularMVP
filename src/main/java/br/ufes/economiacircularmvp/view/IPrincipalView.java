package br.ufes.economiacircularmvp.view;

import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public interface IPrincipalView {
    void exibir();
    void adicionarJanelaInterna(JInternalFrame frame);
    void adicionarAcaoMenuCadastroPerfil(ActionListener listener);
    void adicionarAcaoMenuNovoItem(ActionListener listener);
    void adicionarAcaoMenuCatalogo(ActionListener listener);
    void adicionarAcaoMenuConfiguracoes(ActionListener listener);
    void adicionarAcaoMenuDashboard(ActionListener listener); // Para o admin
    JLabel getRodapeLabel();
}