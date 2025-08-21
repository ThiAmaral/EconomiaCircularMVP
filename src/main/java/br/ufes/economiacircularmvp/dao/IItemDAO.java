package br.ufes.economiacircularmvp.dao;

import br.ufes.economiacircularmvp.dto.ItemDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IItemDAO {
    void inserir(ItemDTO item) throws SQLException;
    List<ItemDTO> buscarTodos() throws SQLException;
    Optional<ItemDTO> buscarPorId(int id) throws SQLException;
    Optional<ItemDTO> buscarPorIdCircular(String idCircular) throws SQLException;
    List<ItemDTO> buscarPorVendedor(int idVendedor) throws SQLException;
    void atualizar(ItemDTO item) throws SQLException;
    void deletar(int id) throws SQLException;
    void criarTabela() throws SQLException;
    void apagarTabela() throws SQLException;
}