package br.ufes.economiacircularmvp.observer.usuario;

import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import java.util.List;

public interface IUsuarioObserver {
    void onUsuariosUpdated(UsuarioDTO novoUsuario);
}
