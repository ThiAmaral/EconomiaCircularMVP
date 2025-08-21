package br.ufes.economiacircularmvp.view;

import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public interface IConfiguracoesView {
    void setVisible(boolean visible);
    JRadioButton getJsonRadio();
    JRadioButton getCsvRadio();
    void adicionarAcaoSalvar(ActionListener listener);
}