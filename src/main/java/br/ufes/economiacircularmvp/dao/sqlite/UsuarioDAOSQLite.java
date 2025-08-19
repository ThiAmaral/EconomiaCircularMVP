package br.ufes.economiacircularmvp.dao.sqlite;

import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp; // NOVO: Import para data e hora
import java.time.LocalDateTime; // NOVO: Import para data e hora
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOSQLite implements IUsuarioDAO {

    private IGerenciarConexaoStrategy gerenciarConexao;

    public UsuarioDAOSQLite(IGerenciarConexaoStrategy gerenciarConexao) {
        this.gerenciarConexao = gerenciarConexao;
    }

    @Override
    public void criarTabela() throws SQLException {
        // ALTERADO: Adicionado email e data_criacao
        String sql = "CREATE TABLE IF NOT EXISTS USUARIOS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "username TEXT UNIQUE NOT NULL," +
                "email TEXT UNIQUE NOT NULL," + // NOVO
                "senha_hash TEXT NOT NULL," +
                "telefone TEXT," +
                "is_admin INTEGER NOT NULL DEFAULT 0," +
                "data_criacao DATETIME NOT NULL" + // NOVO
                ");";
        try (Connection conn = gerenciarConexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela USUARIOS criada ou já existente (SQLite).");
            // TODO: Adicionar log de sistema [14]
        }
    }

    @Override
    public void apagarTabela() throws SQLException {
        String sql = "DROP TABLE IF EXISTS USUARIOS;";
        try (Connection conn = gerenciarConexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela USUARIOS apagada (SQLite).");
            // TODO: Adicionar log de sistema [14]
        }
    }

    @Override
    public void inserir(UsuarioDTO usuario) throws SQLException {
        // ALTERADO: Adicionado email e data_criacao
        String sql = "INSERT INTO USUARIOS (nome, username, senha_hash, telefone, is_admin, email, data_criacao) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getUsername());
            pstmt.setString(3, usuario.getSenhaHash());
            pstmt.setString(4, usuario.getTelefone());
            pstmt.setInt(5, usuario.isAdmin() ? 1 : 0);
            pstmt.setString(6, usuario.getEmail()); // NOVO
            pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); // NOVO: Define a data/hora atual

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Usuário " + usuario.getUsername() + " inserido com sucesso (SQLite). ID: " + usuario.getId());
                // TODO: Implementar lógica de logging para criação [14]
            } else {
                throw new SQLException("Falha ao inserir usuário, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }

    @Override
    public List<UsuarioDTO> buscarTodos() throws SQLException {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        // ALTERADO: Adicionado email e data_criacao na consulta
        String sql = "SELECT id, nome, username, senha_hash, telefone, is_admin, email, data_criacao FROM USUARIOS;";
        try (Connection conn = gerenciarConexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // ALTERADO: Atualizado construtor do DTO
                usuarios.add(new UsuarioDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("username"),
                        rs.getString("senha_hash"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getInt("is_admin") == 1,
                        rs.getTimestamp("data_criacao").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os usuários (SQLite): " + e.getMessage());
            throw e;
        }
        return usuarios;
    }

    @Override
    public Optional<UsuarioDTO> buscarPorLogin(String username) throws SQLException {
        // ALTERADO: Adicionado email e data_criacao na consulta
        String sql = "SELECT id, nome, username, senha_hash, telefone, is_admin, email, data_criacao FROM USUARIOS WHERE username = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // ALTERADO: Atualizado construtor do DTO
                    return Optional.of(new UsuarioDTO(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("username"),
                            rs.getString("senha_hash"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getInt("is_admin") == 1,
                            rs.getTimestamp("data_criacao").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por username (SQLite): " + e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public void atualizar(UsuarioDTO usuarioAntigo, UsuarioDTO usuarioNovo) throws SQLException {
        if (usuarioAntigo.getId() == 0) {
            throw new IllegalArgumentException("ID do usuário antigo não pode ser zero para atualização.");
        }
        // ALTERADO: Adicionado email ao UPDATE. A data de criação não deve ser alterada.
        String sql = "UPDATE USUARIOS SET nome = ?, username = ?, senha_hash = ?, telefone = ?, is_admin = ?, email = ? WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuarioNovo.getNome());
            pstmt.setString(2, usuarioNovo.getUsername());
            pstmt.setString(3, usuarioNovo.getSenhaHash());
            pstmt.setString(4, usuarioNovo.getTelefone());
            pstmt.setInt(5, usuarioNovo.isAdmin() ? 1 : 0);
            pstmt.setString(6, usuarioNovo.getEmail()); // NOVO
            pstmt.setInt(7, usuarioAntigo.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum usuário encontrado com ID " + usuarioAntigo.getId() + " para atualização (SQLite).");
                // TODO: Implementar lógica de logging para falha [15]
            } else {
                System.out.println("Usuário com ID " + usuarioAntigo.getId() + " atualizado com sucesso (SQLite).");
                // TODO: Implementar lógica de logging para alteração [14]
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }

    @Override
    public void deletar(UsuarioDTO usuario) throws SQLException {
        if (usuario.getId() == 0) {
            throw new IllegalArgumentException("ID do usuário não pode ser zero para exclusão.");
        }
        String sql = "DELETE FROM USUARIOS WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuario.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum usuário encontrado com ID " + usuario.getId() + " para exclusão (SQLite).");
                // TODO: Implementar lógica de logging para falha [15]
            } else {
                System.out.println("Usuário com ID " + usuario.getId() + " excluído com sucesso (SQLite).");
                // TODO: Implementar lógica de logging para exclusão [14]
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }
}