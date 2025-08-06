package br.ufes.economiacircularmvp.dao;

import br.ufes.economiacircularmvp.dto.PerfilVendedorDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IPerfilVendedorDAO {
    void inserir(PerfilVendedorDTO perfil) throws SQLException;
    List<PerfilVendedorDTO> buscarTodos() throws SQLException;
    Optional<PerfilVendedorDTO> buscarPorId(int id) throws SQLException; // Busca pelo ID único do perfil
    Optional<PerfilVendedorDTO> buscarPorUsuarioId(int usuarioId) throws SQLException; // Busca pelo ID do usuário associado
    void atualizar(PerfilVendedorDTO perfilAntigo, PerfilVendedorDTO perfilNovo) throws SQLException; // Atualiza o perfil de vendedor
    void deletar(PerfilVendedorDTO perfil) throws SQLException; // Deleta um perfil de vendedor
    void criarTabela() throws SQLException;
    void apagarTabela() throws SQLException;
}
