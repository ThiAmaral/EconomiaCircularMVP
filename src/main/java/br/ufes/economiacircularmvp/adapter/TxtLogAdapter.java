package br.ufes.economiacircularmvp.adapter;

import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import br.ufes.economiacircularmvp.model.Usuario; // Supondo que você tenha um service ou DAO para buscar o usuário
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.sistemadelog.singleton.Logger;
import java.sql.SQLException;
import java.util.Optional;

public class TxtLogAdapter implements ILogAdapter{
    
    private final Logger logger;
    private final IUsuarioRepository usuarioRepository; // Exemplo de como buscar o usuário

    public TxtLogAdapter(IUsuarioRepository repository) {
        this.logger = Logger.getInstance();
        // Nota: A estratégia (TXT, JSON) deveria ser configurada na inicialização do sistema,
        // não em cada adapter. Mas, por enquanto, podemos deixar aqui.
        this.logger.setStrategy("TXT"); 
        this.usuarioRepository = repository; // Inicialize seu serviço/DAO aqui
    }

    @Override
    public void logSucesso(String operacao, String usuario, String status) throws SQLException {
        // Busca o usuário diretamente pelo repositório
        Optional<UsuarioDTO> usuarioOptional = usuarioRepository.buscarUsuarioPorLogin(usuario);
        
        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuarioEncontrado = usuarioOptional.get();
            // Chama o logger com o nome do usuário encontrado
            logger.logSucesso("Teste" ,operacao, usuarioEncontrado.getUsername(), status); 
        } else {
            // Lida com o caso de usuário não encontrado
            logger.logFalha("Teste", operacao, "Teste", "Usuario: " + usuario, "Usuário não encontrado.");
        }
    }

    @Override
    public void logFalha(String operacao, String usuario, String mensagemFalha) throws SQLException {
        Optional<UsuarioDTO> usuarioOptional = usuarioRepository.buscarUsuarioPorLogin(usuario);
        
        String nomeUsuario = usuarioOptional.map(UsuarioDTO::getUsername) // Forma elegante de pegar o nome se existir
                                            .orElse(usuario);
        
        logger.logFalha("", operacao, mensagemFalha, nomeUsuario, "Registro");
    }
}