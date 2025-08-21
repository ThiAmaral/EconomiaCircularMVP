package br.ufes.economiacircularmvp.presenter.geral;

import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.view.ICadastroPerfilView;
import javax.swing.JOptionPane;

public class CadastroPerfilPresenter {

    private ICadastroPerfilView view;

    public CadastroPerfilPresenter(ICadastroPerfilView view) {
        this.view = view;
        inicializarListeners();
    }

    private void inicializarListeners() {
        //view.adicionarAcaoSalvar(e -> salvarPerfil());
        view.adicionarAcaoCancelar(e -> cancelarCadastro());
    }
/*
    private void salvarPerfil() {
        // 1. Obter dados da View
        String nome = view.getNome();
        String contato = view.getContato();
        boolean isComprador = view.isCompradorSelecionado();
        boolean isVendedor = view.isVendedorSelecionado();

        // 2. Validar os dados (lógica de apresentação)
        if (nome.trim().isEmpty() || contato.trim().isEmpty()) {
            view.exibirMensagem("Nome e Contato são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isComprador && !isVendedor) {
            view.exibirMensagem("Selecione ao menos um perfil (Comprador ou Vendedor).", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Criar o objeto Model
        Usuario novoUsuario = new Usuario(nome, contato, isComprador, isVendedor);

        // 4. "Salvar" o usuário (aqui você chamaria um serviço ou repositório)
        // Por enquanto, vamos apenas simular e exibir no console.
        System.out.println("Salvando novo usuário: " + novoUsuario);

        // 5. Dar feedback para o usuário e fechar a tela
        view.exibirMensagem("Usuário '" + nome + "' cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        view.fechar();
    }*/

    private void cancelarCadastro() {
        view.fechar();
    }
}