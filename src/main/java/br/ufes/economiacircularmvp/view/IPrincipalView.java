package br.ufes.economiacircularmvp.view;

import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;

public interface IPrincipalView {
    void exibir();
    void adicionarJanelaInterna(JInternalFrame frame);
    void adicionarAcaoMenuCadastro(ActionListener listener);
}
