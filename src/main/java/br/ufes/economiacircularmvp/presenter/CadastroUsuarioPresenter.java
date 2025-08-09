package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.view.CadastroUsuarioView;
import br.ufes.economiacircularmvp.command.IProjetoCommand;
import java.sql.SQLException;

public final class CadastroUsuarioPresenter {
    
    private final CadastroUsuarioView view;
    private final IUsuarioRepository repository; // Repositório que acessa o banco de dados

    public CadastroUsuarioPresenter(IUsuarioRepository repository) throws SQLException{
        this.view = new CadastroUsuarioView();
        view.setVisible(false);
        this.repository = repository;
        configurar();
        // Cria o comando que chama o método 'salvarUsuario' do próprio Presenter.
        IProjetoCommand comandoSalvar = this::salvarUsuario; 

        // Injeta o comando na View.
        view.setSalvarCommand(comandoSalvar);
    }

    public void configurar() throws SQLException {
        // 1. Verificar se existem usuários no sistema
        boolean naoExistelUsuarios = repository.buscarTodosUsuarios().isEmpty() ;

        // 2. Se for o primeiro usuário (total == 0)
        if (naoExistelUsuarios) {
            // 3. O Presenter manda a View se ajustar:
            //    marca a caixa "isAdmin" e a desabilita para o usuário não poder mudar.
            view.setAdminCheckbox(true, false); 
            view.exibirMensagem("Info: O primeiro usuário cadastrado será um Administrador.");
        } else {
            // 4. Caso contrário, a caixa fica habilitada para escolha manual.
            view.setAdminCheckbox(false, true);
        }

        view.setVisible(true);
    }
    
    private void salvarUsuario() {
        // 1. O Presenter busca os dados da View
        String nome = view.getNome();
        String usuario = view.getUsuario();
        // ... buscar todos os outros campos

        // 2. O Presenter executa a lógica de negócio (validações, etc.)
        // ...

        // 3. Monta o objeto de modelo (ex: Usuario) e manda salvar no repositório.
        // ...

        // 4. Dá feedback ao usuário através da View
        view.exibirMensagem("Usuário cadastrado com sucesso!");
        view.limparCampos();
    }
}
