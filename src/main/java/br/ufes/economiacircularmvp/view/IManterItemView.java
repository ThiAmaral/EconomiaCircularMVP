package br.ufes.economiacircularmvp.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public interface IManterItemView {
    void setVisible(boolean visible);
    JTextField getIdcField();
    JComboBox<String> getTipoPecaCombo();
    JTextField getSubcategoriaField();
    JTextField getTamanhoField();
    JTextField getCorField();
    JTextField getComposicaoField();
    JTextField getMassaField();
    JComboBox<String> getEstadoConservacaoCombo();
    List<JCheckBox> getDefeitosCheckBoxes();
    JTextField getPrecoBaseField();
    JLabel getPrecoFinalLabel();
    JLabel getGwpLabel();
    JLabel getMciLabel();
    JButton getSalvarButton();
    JButton getExcluirButton();
    JButton getCancelarButton();
    void adicionarAcaoSalvar(ActionListener listener);
    void adicionarAcaoExcluir(ActionListener listener);
    void adicionarAcaoCancelar(ActionListener listener);
    void fechar();
    void exibirMensagem(String mensagem, String titulo, int tipo);
}