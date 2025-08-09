package br.ufes.economiacircularmvp.service;

import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import java.sql.SQLException;
import java.util.Optional;

public class AutenticacaoService {
    private IUsuarioRepository usuarioRepository;

    public AutenticacaoService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void autenticar(Usuario usuario) throws SQLException {
        Optional<UsuarioDTO> optUsuario = usuarioRepository.buscarUsuarioPorLogin(usuario.getUsuario());
        if (optUsuario.isPresent()) {
            UsuarioDTO usuarioEncontrado = optUsuario.get();
            if (usuarioEncontrado.getSenhaHash().equalsIgnoreCase(usuario.getSenha())) {
                usuario.setAutenticado(true);
            } else {
                throw new RuntimeException("Usuário " + usuario.getNome()+ " não autenticado");
            }
        }
    }
}
