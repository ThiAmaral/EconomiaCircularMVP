package br.ufes.economiacircularmvp.main;

import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.factory.IDAOFactory;
import br.ufes.economiacircularmvp.factory.RepositoryFactory;
import br.ufes.economiacircularmvp.presenter.LoginPresenter;
import br.ufes.economiacircularmvp.repository.H2UsuarioRepository;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.repository.SQLiteUsuarioRepository;
import br.ufes.economiacircularmvp.view.LoginView;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class EconomiaCircularMVP {

    public static void main(String[] args) {
        try {
            // 1. Obtém a fábrica de DAO com base no arquivo .env
            IDAOFactory fabrica = RepositoryFactory.getDAOFactory();
            
            // 2. Obtém o DAO de usuário específico (SQLite ou H2)
            IUsuarioDAO usuarioDAO = fabrica.getUsuarioDAO();
            
            // 3. Instancia o repositório de usuário com o DAO apropriado
            IUsuarioRepository usuarioRepository = RepositoryFactory.getUsuarioRepository(usuarioDAO);
            
            System.out.println("Repositório inicializado com sucesso para o SGBD: " + fabrica.getgerenciarConexao().getDbType());

            // 4. Cria a tela de autenticação (View)
            LoginView loginView = new LoginView();
            
            // 5. Inicia o Presenter, que conecta a View com o Repositório e a torna visível
            new LoginPresenter(usuarioRepository);

        } catch (RuntimeException | SQLException e) {
            // Exibe qualquer erro de inicialização para o usuário
            JOptionPane.showMessageDialog(
                null, 
                "Erro ao inicializar a aplicação:\n" + e.getMessage(), 
                "Erro Crítico", 
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}