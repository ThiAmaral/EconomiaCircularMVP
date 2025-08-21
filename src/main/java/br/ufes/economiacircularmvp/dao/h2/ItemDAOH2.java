package br.ufes.economiacircularmvp.dao.h2;

import br.ufes.economiacircularmvp.dao.IItemDAO;
import br.ufes.economiacircularmvp.dto.ItemDTO;
import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOH2 implements IItemDAO {

    private IGerenciarConexaoStrategy gerenciarConexao;

    public ItemDAOH2(IGerenciarConexaoStrategy gerenciarConexao) {
        this.gerenciarConexao = gerenciarConexao;
    }

    @Override
    public void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS ITENS (" +
                "id IDENTITY PRIMARY KEY," +
                "id_circular VARCHAR(12) UNIQUE NOT NULL," +
                "id_vendedor BIGINT NOT NULL," +
                "tipo_peca VARCHAR(255) NOT NULL," +
                "subcategoria VARCHAR(255)," +
                "tamanho VARCHAR(50)," +
                "cor_predominante VARCHAR(100)," +
                "composicao_principal VARCHAR(255)," +
                "massa_estimada DOUBLE NOT NULL," +
                "estado_conservacao VARCHAR(100)," +
                "defeitos VARCHAR(MAX)," +
                "preco_base DOUBLE NOT NULL," +
                "preco_final DOUBLE," +
                "gwp_avoided DOUBLE," +
                "mci_item DOUBLE," +
                "data_publicacao TIMESTAMP NOT NULL," +
                "vendido BOOLEAN NOT NULL DEFAULT FALSE," +
                "FOREIGN KEY (id_vendedor) REFERENCES USUARIOS(id) ON DELETE CASCADE" +
                ");";
        try (Connection conn = gerenciarConexao.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    @Override
    public void inserir(ItemDTO item) throws SQLException {
        String sql = "INSERT INTO ITENS (id_circular, id_vendedor, tipo_peca, subcategoria, tamanho, cor_predominante, composicao_principal, massa_estimada, estado_conservacao, defeitos, preco_base, preco_final, gwp_avoided, mci_item, data_publicacao, vendido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = gerenciarConexao.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setStatementFromDTO(pstmt, item);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Demais métodos (buscarTodos, buscarPorId, etc.) a serem implementados
    @Override public List<ItemDTO> buscarTodos() throws SQLException { return new ArrayList<>(); }
    @Override public Optional<ItemDTO> buscarPorId(int id) throws SQLException { return Optional.empty(); }
    @Override public Optional<ItemDTO> buscarPorIdCircular(String idCircular) throws SQLException { return Optional.empty(); }
    @Override public List<ItemDTO> buscarPorVendedor(int idVendedor) throws SQLException { return new ArrayList<>(); }
    @Override public void atualizar(ItemDTO item) throws SQLException {}
    @Override public void deletar(int id) throws SQLException {}
    @Override public void apagarTabela() throws SQLException {
        String sql = "DROP TABLE IF EXISTS ITENS;";
        try (Connection conn = gerenciarConexao.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private void setStatementFromDTO(PreparedStatement pstmt, ItemDTO item) throws SQLException {
        pstmt.setString(1, item.getIdCircular());
        pstmt.setInt(2, item.getIdVendedor());
        pstmt.setString(3, item.getTipoPeca());
        pstmt.setString(4, item.getSubcategoria());
        pstmt.setString(5, item.getTamanho());
        pstmt.setString(6, item.getCorPredominante());
        pstmt.setString(7, item.getComposicaoPrincipal());
        pstmt.setDouble(8, item.getMassaEstimada());
        pstmt.setString(9, item.getEstadoConservacao());
        pstmt.setString(10, item.getDefeitos());
        pstmt.setDouble(11, item.getPrecoBase());
        pstmt.setDouble(12, item.getPrecoFinal());
        pstmt.setDouble(13, item.getGwpAvoided());
        pstmt.setDouble(14, item.getMciItem());
        pstmt.setTimestamp(15, Timestamp.valueOf(item.getDataPublicacao()));
        pstmt.setBoolean(16, item.isVendido());
    }
}