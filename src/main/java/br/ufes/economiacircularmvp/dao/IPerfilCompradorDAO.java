package br.ufes.economiacircularmvp.dao;

import br.ufes.economiacircularmvp.dto.PerfilCompradorDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IPerfilCompradorDAO {
    void inserir(PerfilCompradorDTO perfil) throws SQLException;
    List<PerfilCompradorDTO> buscarTodos() throws SQLException;
    Optional<PerfilCompradorDTO> buscarPorUsuarioId(int usuarioId) throws SQLException; // Busca pelo ID do usuário associado
    void atualizar(PerfilCompradorDTO perfil) throws SQLException; // Atualiza o perfil de comprador
    void deletar(PerfilCompradorDTO perfil) throws SQLException; // Deleta um perfil de comprador
    void criarTabela() throws SQLException;
    void apagarTabela() throws SQLException;
}
