package br.ufes.economiacircularmvp.dao.sqlite;

import br.ufes.economiacircularmvp.dao.IPerfilVendedorDAO;
import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.dto.PerfilVendedorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PerfilVendedorDAOSQLite implements IPerfilVendedorDAO{
    private IGerenciarConexaoStrategy gerenciarConexao;

    public PerfilVendedorDAOSQLite(IGerenciarConexaoStrategy gerenciarConexao) {
        this.gerenciarConexao = gerenciarConexao;
    }

    @Override
    public void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS PERFIS_VENDEDOR (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "usuario_id INTEGER UNIQUE NOT NULL," + // Garante 1:1 e FK
                     "reputacao_estrelas REAL NOT NULL DEFAULT 0.0," +
                     "nivel_reputacao TEXT NOT NULL DEFAULT 'Bronze'," +
                     "beneficio_climatico_acumulado REAL NOT NULL DEFAULT 0.0," +
                     "insignias_permanentes TEXT," + // Armazenar como JSON string ou similar
                     "selos_temporada TEXT," +
                     "ranking_semanal_vigente TEXT," +
                     "indicadores_curadoria TEXT," +
                     "FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id) ON DELETE CASCADE" + // CASCADE para apagar perfil se usuário for deletado
                     ");";
        try (Connection conn = gerenciarConexao.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela PERFIS_VENDEDOR criada ou já existente (SQLite).");
            // TODO: Adicionar log de sistema [14]
        }
    }

    @Override
    public void apagarTabela() throws SQLException {
        String sql = "DROP TABLE IF EXISTS PERFIS_VENDEDOR;";
        try (Connection conn = gerenciarConexao.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela PERFIS_VENDEDOR apagada (SQLite).");
            // TODO: Adicionar log de sistema [14]
        }
    }

    @Override
    public void inserir(PerfilVendedorDTO perfil) throws SQLException {
        String sql = "INSERT INTO PERFIS_VENDEDOR (usuario_id, reputacao_estrelas, nivel_reputacao, beneficio_climatico_acumulado, insignias_permanentes, selos_temporada, ranking_semanal_vigente, indicadores_curadoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = gerenciarConexao.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, perfil.getUsuarioId());
            pstmt.setDouble(2, perfil.getReputacaoEstrelas());
            pstmt.setString(3, perfil.getNivelReputacao());
            pstmt.setDouble(4, perfil.getBeneficioClimaticoAcumulado());
            pstmt.setString(5, perfil.getInsigniasPermanentes());
            pstmt.setString(6, perfil.getSelosTemporada());
            pstmt.setString(7, perfil.getRankingSemanalVigente());
            pstmt.setString(8, perfil.getIndicadoresCuradoria());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        perfil.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Perfil de vendedor para usuário " + perfil.getUsuarioId() + " inserido com sucesso (SQLite). ID: " + perfil.getId());
                // TODO: Implementar lógica de logging [14]
            } else {
                throw new SQLException("Falha ao inserir perfil de vendedor, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir perfil de vendedor (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }

    @Override
    public List<PerfilVendedorDTO> buscarTodos() throws SQLException {
        List<PerfilVendedorDTO> perfis = new ArrayList<>();
        String sql = "SELECT * FROM PERFIS_VENDEDOR;";
        try (Connection conn = gerenciarConexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                perfis.add(new PerfilVendedorDTO(
                    rs.getInt("id"),
                    rs.getInt("usuario_id"),
                    rs.getDouble("reputacao_estrelas"),
                    rs.getString("nivel_reputacao"),
                    rs.getDouble("beneficio_climatico_acumulado"),
                    rs.getString("insignias_permanentes"),
                    rs.getString("selos_temporada"),
                    rs.getString("ranking_semanal_vigente"),
                    rs.getString("indicadores_curadoria")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os perfis de vendedor (SQLite): " + e.getMessage());
            throw e;
        }
        return perfis;
    }
    
    @Override
    public Optional<PerfilVendedorDTO> buscarPorUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM PERFIS_VENDEDOR WHERE usuario_id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new PerfilVendedorDTO(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getDouble("reputacao_estrelas"),
                        rs.getString("nivel_reputacao"),
                        rs.getDouble("beneficio_climatico_acumulado"),
                        rs.getString("insignias_permanentes"),
                        rs.getString("selos_temporada"),
                        rs.getString("ranking_semanal_vigente"),
                        rs.getString("indicadores_curadoria")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar perfil de vendedor por usuario_id (SQLite): " + e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public Optional<PerfilVendedorDTO> buscarPorId(int perfilId) throws SQLException {
        String sql = "SELECT * FROM PERFIS_VENDEDOR WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, perfilId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new PerfilVendedorDTO(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getDouble("reputacao_estrelas"),
                        rs.getString("nivel_reputacao"),
                        rs.getDouble("beneficio_climatico_acumulado"),
                        rs.getString("insignias_permanentes"),
                        rs.getString("selos_temporada"),
                        rs.getString("ranking_semanal_vigente"),
                        rs.getString("indicadores_curadoria")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar perfil de vendedor por id (SQLite): " + e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public void atualizar(PerfilVendedorDTO perfilAnterior,PerfilVendedorDTO perfilNovo) throws SQLException {
        if (perfilAnterior.getId() == 0) {
            throw new IllegalArgumentException("ID do perfil não pode ser zero para atualização.");
        }
        String sql = "UPDATE PERFIS_VENDEDOR SET reputacao_estrelas = ?, nivel_reputacao = ?, beneficio_climatico_acumulado = ?, insignias_permanentes = ?, selos_temporada = ?, ranking_semanal_vigente = ?, indicadores_curadoria = ? WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, perfilNovo.getReputacaoEstrelas());
            pstmt.setString(2, perfilNovo.getNivelReputacao());
            pstmt.setDouble(3, perfilNovo.getBeneficioClimaticoAcumulado());
            pstmt.setString(4, perfilNovo.getInsigniasPermanentes());
            pstmt.setString(5, perfilNovo.getSelosTemporada());
            pstmt.setString(6, perfilNovo.getRankingSemanalVigente());
            pstmt.setString(7, perfilNovo.getIndicadoresCuradoria());
            pstmt.setInt(8, perfilNovo.getId()); // Usar o ID do próprio objeto para WHERE

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum perfil de vendedor encontrado com ID " + perfilAnterior.getId() + " para atualização (SQLite).");
                // TODO: Implementar lógica de logging para falha [15]
            } else {
                System.out.println("Perfil de vendedor com ID " + perfilAnterior.getId() + " atualizado com sucesso (SQLite).");
                // TODO: Implementar lógica de logging para alteração [14]
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar perfil de vendedor (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }

    @Override
    public void deletar(PerfilVendedorDTO perfil) throws SQLException {
        if (perfil.getId() == 0) {
            throw new IllegalArgumentException("ID do perfil não pode ser zero para exclusão.");
        }
        String sql = "DELETE FROM PERFIS_VENDEDOR WHERE id = ?;";
        try (Connection conn = gerenciarConexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, perfil.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum perfil de vendedor encontrado com ID " + perfil.getId() + " para exclusão (SQLite).");
                // TODO: Implementar lógica de logging para falha [15]
            } else {
                System.out.println("Perfil de vendedor com ID " + perfil.getId() + " excluído com sucesso (SQLite).");
                // TODO: Implementar lógica de logging para exclusão [14]
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar perfil de vendedor (SQLite): " + e.getMessage());
            // TODO: Implementar lógica de logging para falha [15]
            throw e;
        }
    }
}
