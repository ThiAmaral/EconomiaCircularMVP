package br.ufes.economiacircularmvp.repository;

import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Thiago
 */
public interface IUsuarioRepository {
    
    public void adicionarUsuario(UsuarioDTO usuario) throws SQLException;
    public List<UsuarioDTO> buscarTodosUsuarios() throws SQLException;
    public Optional<UsuarioDTO> buscarUsuarioPorLogin(String login) throws SQLException;
    public void atualizarUsuario(UsuarioDTO usuarioAntigo, UsuarioDTO usuarioNovo) throws SQLException;
    public void removerUsuario(UsuarioDTO usuario) throws SQLException;
    public void limparRepositorio() throws SQLException;
    
}
