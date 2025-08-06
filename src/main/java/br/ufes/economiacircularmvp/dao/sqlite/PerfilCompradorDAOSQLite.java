package br.ufes.economiacircularmvp.dao.sqlite;

import br.ufes.economiacircularmvp.dao.IPerfilCompradorDAO;
import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.dto.PerfilCompradorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PerfilCompradorDAOSQLite implements IPerfilCompradorDAO{
    private IGerenciarConexaoStrategy gerenciarConexao;

    public PerfilCompradorDAOSQLite(IGerenciarConexaoStrategy gerenciarConexao) {
        this.gerenciarConexao = gerenciarConexao;
    }

    @Override
    public void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS PERFIS_COMPRADOR (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "usuario_id INTEGER UNIQUE NOT NULL," + // Garante 1:1 e FK
                     "reputacao_estrelas REAL NOT NULL DEFAULT 0.0," +
                     "nivel_reputacao TEXT NOT NULL DEFAULT 'Bronze'," +
                     "insignias_permanentes TEXT," + // Armazenar como JSON string ou similar
                     "selo_verificador_confiavel TEXT," + // 'true' ou 'false'
                     "coletaveis_temporada TEXT," +
                     "total_gwp_evitado_por_compras REAL NOT NULL DEFAULT 0.0," +
                     "estatisticas_denuncias TEXT," +
                     "FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id) ON DELETE CASCADE" +
                     ");";
        try (Connection conn = gerenciarConexao.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela PERFIS_COMPRADOR criada ou já existente (SQLite).");
            // TODO: Adicionar log de sistema [14]
        }
    }

    @Override
    public void apagarTabela() throws SQLException {
        String sql = "DROP TABLE IF EXISTS PERFIS_COMPRADOR;";
        try (Connection conn = gerenciarConexao.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela PERFIS_COMPRADOR apagada (SQLite).");
            // TODO: Adicionar log de sistema [14]
        }
    }

    @Override
    public void inserir(PerfilCompradorDTO perfil) throws SQLException {
        String sql = "INSERT INTO PERFIS_COMPRADOR (usuario_id, reputacao_estrelas, nivel_reputacao, insignias_permanentes, selo_verificador_confiavel, coletaveis_temporada, total_gwp_evitado_por_compras, estatisticas_denuncias) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = gerenciarConexao.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, perfil.getUsuarioId());
            pstmt.setDouble(2, perfil.getReputacaoEstrelas());
            pstmt.setString(3, perfil.getNivelReputacao());
            pstmt.setString(4, perfil.getInsigniasPermanentes());
            pstmt.setString(5, perfil.getSeloVerificadorConfiavel());
            pstmt.setString(6, perfil.getColetaveisTemporada());
            pstmt.setDouble(7, perfil.getTotalGwpEvitadoPorCompras());
            pstmt.setString(8, perfil.getEstatisticasDenuncias());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        perfil.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Perfil de comprador para usuário " + perfil.getUsuarioId() + " inserido com sucesso (SQLite). ID: " + perfil.getId());
                // TODO: Implementar lógica de logging [14]
            } else {
                throw new SQLException("Falha ao inserir perfil de comprador, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir perfil de comprador (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }

    @Override
    public List<PerfilCompradorDTO> buscarTodos() throws SQLException {
        List<PerfilCompradorDTO> perfis = new ArrayList<>();
        String sql = "SELECT * FROM PERFIS_COMPRADOR;";
        try (Connection conn = gerenciarConexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                perfis.add(new PerfilCompradorDTO(
                    rs.getInt("id"),
                    rs.getInt("usuario_id"),
                    rs.getDouble("reputacao_estrelas"),
                    rs.getString("nivel_reputacao"),
                    rs.getString("insignias_permanentes"),
                    rs.getString("selo_verificador_confiavel"),
                    rs.getString("coletaveis_temporada"),
                    rs.getDouble("total_gwp_evitado_por_compras"),
                    rs.getString("estatisticas_denuncias")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os perfis de comprador (SQLite): " + e.getMessage());
            throw e;
        }
        return perfis;
    }

    @Override
    public Optional<PerfilCompradorDTO> buscarPorUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM PERFIS_COMPRADOR WHERE usuario_id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new PerfilCompradorDTO(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getDouble("reputacao_estrelas"),
                        rs.getString("nivel_reputacao"),
                        rs.getString("insignias_permanentes"),
                        rs.getString("selo_verificador_confiavel"),
                        rs.getString("coletaveis_temporada"),
                        rs.getDouble("total_gwp_evitado_por_compras"),
                        rs.getString("estatisticas_denuncias")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar perfil de comprador por usuario_id (SQLite): " + e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public void atualizar(PerfilCompradorDTO perfil) throws SQLException {
        if (perfil.getId() == 0) {
            throw new IllegalArgumentException("ID do perfil não pode ser zero para atualização.");
        }
        String sql = "UPDATE PERFIS_COMPRADOR SET reputacao_estrelas = ?, nivel_reputacao = ?, insignias_permanentes = ?, selo_verificador_confiavel = ?, coletaveis_temporada = ?, total_gwp_evitado_por_compras = ?, estatisticas_denuncias = ? WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, perfil.getReputacaoEstrelas());
            pstmt.setString(2, perfil.getNivelReputacao());
            pstmt.setString(3, perfil.getInsigniasPermanentes());
            pstmt.setString(4, perfil.getSeloVerificadorConfiavel());
            pstmt.setString(5, perfil.getColetaveisTemporada());
            pstmt.setDouble(6, perfil.getTotalGwpEvitadoPorCompras());
            pstmt.setString(7, perfil.getEstatisticasDenuncias());
            pstmt.setInt(8, perfil.getId()); // Usar o ID do próprio objeto para WHERE

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum perfil de comprador encontrado com ID " + perfil.getId() + " para atualização (SQLite).");
                // TODO: Implementar lógica de logging para falha [15]
            } else {
                System.out.println("Perfil de comprador com ID " + perfil.getId() + " atualizado com sucesso (SQLite).");
                // TODO: Implementar lógica de logging para alteração [14]
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar perfil de comprador (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }

    @Override
    public void deletar(PerfilCompradorDTO perfil) throws SQLException {
        if (perfil.getId() == 0) {
            throw new IllegalArgumentException("ID do perfil não pode ser zero para exclusão.");
        }
        String sql = "DELETE FROM PERFIS_COMPRADOR WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, perfil.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum perfil de comprador encontrado com ID " + perfil.getId() + " para exclusão (SQLite).");
                // TODO: Implementar lógica de logging para falha [15]
            } else {
                System.out.println("Perfil de comprador com ID " + perfil.getId() + " excluído com sucesso (SQLite).");
                // TODO: Implementar lógica de logging para exclusão [14]
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar perfil de comprador (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }
}
