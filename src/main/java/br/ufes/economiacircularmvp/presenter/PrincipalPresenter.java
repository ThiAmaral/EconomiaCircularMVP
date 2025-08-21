package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import br.ufes.economiacircularmvp.view.IPrincipalView;
import br.ufes.economiacircularmvp.view.ManterPerfilView;
// Importe outras views e presenters necessários
// import br.ufes.economiacircularmvp.view.ManterItemView;
// import br.ufes.economiacircularmvp.view.CatalogoView;
// import br.ufes.economiacircularmvp.view.ConfiguracoesView;

public class PrincipalPresenter {
    private IPrincipalView mainView;
    private UsuarioDTO usuarioLogado;

    public PrincipalPresenter(UsuarioDTO usuarioLogado) {
        this.mainView = new br.ufes.economiacircularmvp.view.PrincipalView();
        this.usuarioLogado = usuarioLogado;
        
        mainView.getRodapeLabel().setText("Usuário: " + usuarioLogado.getNome());

        // Adiciona os listeners para os itens de menu
        this.mainView.adicionarAcaoMenuCadastroPerfil(e -> abrirTelaManterPerfil());
        // this.mainView.adicionarAcaoMenuNovoItem(e -> abrirTelaNovoItem());
        // this.mainView.adicionarAcaoMenuCatalogo(e -> abrirTelaCatalogo());
        // this.mainView.adicionarAcaoMenuConfiguracoes(e -> abrirTelaConfiguracoes());
        
        mainView.exibir();
    }

    private void abrirTelaManterPerfil() {
        ManterPerfilView perfilView = new ManterPerfilView();
        // new ManterPerfilPresenter(perfilView, usuarioLogado); // Futura implementação
        mainView.adicionarJanelaInterna(perfilView);
    }

    // Implemente os métodos para abrir as outras telas
    // private void abrirTelaNovoItem() { ... }
    // private void abrirTelaCatalogo() { ... }
    // private void abrirTelaConfiguracoes() { ... }
}