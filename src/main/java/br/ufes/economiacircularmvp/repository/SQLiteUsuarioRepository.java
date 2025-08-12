package br.ufes.economiacircularmvp.repository;

import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import br.ufes.economiacircularmvp.observer.usuario.IUsuarioObserver;
import java.sql.SQLException;
import java.util.ArrayList; // Importe ArrayList
import java.util.List;
import java.util.Optional;

// Remova "implements IUsuarioRepository" do topo se já estiver definido na interface
public class SQLiteUsuarioRepository implements IUsuarioRepository {

    private IUsuarioDAO dao;
    private List<UsuarioDTO> listaUsuarios;
    // Lista para manter os observadores
    private final List<IUsuarioObserver> observers = new ArrayList<>();

    public SQLiteUsuarioRepository(IUsuarioDAO dao) throws SQLException {
        this.dao = dao;
        this.dao.criarTabela();
    }

    @Override
    public void adicionarUsuario(UsuarioDTO usuario) throws SQLException {
        // Primeiro, insere o usuário no banco de dados
        dao.inserir(usuario);
        // Se a inserção for bem-sucedida, notifica os observadores
        notificarUsuarioAdicionado(usuario);
    }

    // ... (demais métodos como buscarTodosUsuarios, buscarUsuarioPorLogin, etc. permanecem iguais)

    @Override
    public List<UsuarioDTO> buscarTodosUsuarios() throws SQLException {
        return dao.buscarTodos();
    }

    @Override
    public Optional<UsuarioDTO> buscarUsuarioPorLogin(String login) throws SQLException {
        return dao.buscarPorLogin(login);
    }

    @Override
    public void atualizarUsuario(UsuarioDTO usuarioAntigo, UsuarioDTO usuarioNovo) throws SQLException {
        dao.atualizar(usuarioAntigo, usuarioNovo);
        // Você também pode notificar sobre atualizações se desejar
    }

    @Override
    public void removerUsuario(UsuarioDTO usuario) throws SQLException {
        dao.deletar(usuario);
        // E sobre remoções
    }

    @Override
    public void limparRepositorio() throws SQLException {
        this.dao.apagarTabela();
        this.dao.criarTabela();
    }

    // --- Implementação dos métodos do Observer ---

    /**
     * Registra um novo observador para ser notificado sobre a adição de usuários.
     * @param observer O observador a ser adicionado.
     */
    @Override
    public void addObserver(IUsuarioObserver observer) {
        if (observer != null && !this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    /**
     * Remove um observador da lista de notificações.
     * @param observer O observador a ser removido.
     */
    @Override
    public void removeObserver(IUsuarioObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifica todos os observadores registrados que um novo usuário foi adicionado.
     * @param usuario O DTO do novo usuário.
     */
    private void notificarUsuarioAdicionado(UsuarioDTO usuario) {
        // Cria uma cópia da lista para evitar ConcurrentModificationException
        // caso um observador tente se remover durante a notificação.
        for (IUsuarioObserver observer : new ArrayList<>(this.observers)) {
            observer.onUsuariosUpdated(usuario);
        }
    }
}