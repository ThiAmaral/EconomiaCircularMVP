package br.ufes.economiacircularmvp.repository;

import br.ufes.economiacircularmvp.dto.ItemDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IItemRepository {
    void inserir(ItemDTO item) throws SQLException;
    List<ItemDTO> buscarTodos() throws SQLException;
    Optional<ItemDTO> buscarPorId(int id) throws SQLException;
    void atualizar(ItemDTO item) throws SQLException;
    void deletar(int id) throws SQLException;
}