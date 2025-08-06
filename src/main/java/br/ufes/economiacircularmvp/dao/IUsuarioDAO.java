package br.ufes.economiacircularmvp.dao;

import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUsuarioDAO {
    void inserir(UsuarioDTO usuario) throws SQLException;
    List<UsuarioDTO> buscarTodos() throws SQLException;
    Optional<UsuarioDTO> buscarPorLogin(String usuario) throws SQLException; // Assumido buscar por username
    void atualizar(UsuarioDTO usuarioAntigo, UsuarioDTO usuarioNovo) throws SQLException;
    void deletar(UsuarioDTO usuario) throws SQLException;
    void criarTabela() throws SQLException; // Método para garantir que a tabela existe
    void apagarTabela() throws SQLException; // Método para limpar a tabela (útil para testes)
}
