package br.ufes.economiacircularmvp.view;

import java.awt.event.ActionListener;

public interface ICadastroPerfilView {
    // Métodos para obter dados da tela
    String getNome();
    String getContato();
    boolean isCompradorSelecionado();
    boolean isVendedorSelecionado();

    // Métodos para manipular a tela
    void setVisible(boolean visible);
    void fechar();
    void limparCampos();
    void exibirMensagem(String mensagem, String titulo, int tipo);

    // Métodos para adicionar listeners aos botões
    void adicionarAcaoSalvar(ActionListener listener);
    void adicionarAcaoCancelar(ActionListener listener);
}
