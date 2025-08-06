package br.ufes.economiacircularmvp.repository;

import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SQLiteUsuarioRepository implements IUsuarioRepository{
    
    private IUsuarioDAO dao;

    // * Construtor que recebe a implementação do DAO para Usuário.
    // * Garante que a tabela de usuários exista ao inicializar o repositório,
    // * conforme o exemplo fornecido.
    // * @param dao A implementação do IUsuarioDAO.
    // * @throws SQLException Se ocorrer um erro ao criar a tabela.
    
    public SQLiteUsuarioRepository(IUsuarioDAO dao) throws SQLException {
        this.dao = dao;
        // Chama criarTabela() no construtor, como no seu exemplo 'ExemploRepository' [código exemplo]
        this.dao.criarTabela(); 
    }

    // * Adiciona um novo usuário ao sistema.
    // * @param usuario O DTO do usuário a ser adicionado.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    @Override
    public void adicionarUsuario(UsuarioDTO usuario) throws SQLException {
        dao.inserir(usuario);
    }
    
    // * Busca todos os usuários cadastrados no sistema.
    // * @return Uma lista contendo todos os DTOs de usuário.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    @Override
    public List<UsuarioDTO> buscarTodosUsuarios() throws SQLException {
        return dao.buscarTodos();
    }

    // * Busca um usuário pelo seu login único.
    // * @param login O login do usuário a ser buscado.
    // * @return Um Optional contendo o DTO do usuário se encontrado, ou um Optional vazio caso contrário.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    @Override
    public Optional<UsuarioDTO> buscarUsuarioPorLogin(String login) throws SQLException {
        return dao.buscarPorLogin(login);
    }

    // * Atualiza os dados de um usuário existente.
    // * O método segue a assinatura do seu exemplo, recebendo o objeto antigo para identificação
    // * e o novo com os dados atualizados.
    // * @param usuarioAntigo O DTO do usuário existente a ser atualizado (usado para identificação).
    // * @param usuarioNovo O DTO com os novos dados do usuário.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    @Override
    public void atualizarUsuario(UsuarioDTO usuarioAntigo, UsuarioDTO usuarioNovo) throws SQLException {
        dao.atualizar(usuarioAntigo, usuarioNovo);
    }

    // * Remove um usuário do sistema.
    // * @param usuario O DTO do usuário a ser removido.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    @Override
    public void removerUsuario(UsuarioDTO usuario) throws SQLException {
        dao.deletar(usuario);
    }

    // * Limpa o repositório de usuários, apagando e recriando a tabela.
    // * Este método é útil para cenários de teste ou para redefinir o estado do banco de dados local.
    // * @throws SQLException Se ocorrer um erro ao apagar ou criar a tabela.
    @Override
    public void limparRepositorio() throws SQLException {
        this.dao.apagarTabela();
        this.dao.criarTabela();
    }
}
