package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.view.CadastroPerfilView;
import br.ufes.economiacircularmvp.view.ICadastroPerfilView;
import br.ufes.economiacircularmvp.view.IPrincipalView;

public class PrincipalPresenter {
    private IPrincipalView mainView;

    public PrincipalPresenter(IPrincipalView mainView) {
        this.mainView = mainView;
        this.mainView.adicionarAcaoMenuCadastro(e -> abrirTelaCadastroPerfil());
    }

    private void abrirTelaCadastroPerfil() {
        // Cria a View de cadastro
        ICadastroPerfilView cadastroView = new CadastroPerfilView();
        
        // Cria o Presenter para essa View
        new CadastroPerfilPresenter(cadastroView);
        
        // Adiciona a View (como JInternalFrame) à tela principal
        mainView.adicionarJanelaInterna((javax.swing.JInternalFrame) cadastroView);
    }
    
    public void iniciar() {
        mainView.exibir();
    }
}
