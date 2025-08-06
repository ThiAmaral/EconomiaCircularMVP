package br.ufes.economiacircularmvp.repository;

import br.ufes.economiacircularmvp.dao.IPerfilVendedorDAO;
import br.ufes.economiacircularmvp.dto.PerfilVendedorDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PerfilVendedorRepository {
    
    private IPerfilVendedorDAO dao;

    // * Construtor que recebe a implementação do DAO para Perfil de Vendedor.
    // * Garante que a tabela de perfis de vendedor exista ao inicializar o repositório.
    // * @param dao A implementação do IPerfilVendedorDAO.
    // * @throws SQLException Se ocorrer um erro ao criar a tabela.

    public PerfilVendedorRepository(IPerfilVendedorDAO dao) throws SQLException {
        this.dao = dao;
        this.dao.criarTabela();
    }

    //* Adiciona um novo perfil de vendedor ao repositório.
    //* @param perfil O DTO do perfil de vendedor a ser adicionado.
    //* @throws SQLException Se ocorrer um erro na persistência dos dados.
     
    public void adicionarPerfilVendedor(PerfilVendedorDTO perfil) throws SQLException {
        dao.inserir(perfil);
    }
      
    // * Busca todos os perfis de vendedor cadastrados.
    // * @return Uma lista contendo todos os DTOs de perfil de vendedor.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    
    public List<PerfilVendedorDTO> buscarTodosPerfisVendedor() throws SQLException {
        return dao.buscarTodos();
    }

    // * Busca um perfil de vendedor pelo seu ID único.
    // * @param id O ID do perfil de vendedor a ser buscado.
    // * @return Um Optional contendo o DTO do perfil de vendedor se encontrado, ou um Optional vazio.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    
    public Optional<PerfilVendedorDTO> buscarPerfilVendedorPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    // * Busca um perfil de vendedor pelo ID do usuário base associado.
    // * @param usuarioId O ID do usuário associado ao perfil de vendedor.
    // * @return Um Optional contendo o DTO do perfil de vendedor se encontrado, ou um Optional vazio.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    
    public Optional<PerfilVendedorDTO> buscarPerfilVendedorPorUsuarioId(int usuarioId) throws SQLException {
        return dao.buscarPorUsuarioId(usuarioId);
    }

    // * Atualiza os dados de um perfil de vendedor existente.
    // * @param perfilAntigo O DTO do perfil de vendedor a ser identificado para atualização.
    // * @param perfilNovo O DTO com os novos dados para o perfil de vendedor.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    
    public void atualizarPerfilVendedor(PerfilVendedorDTO perfilAntigo, PerfilVendedorDTO perfilNovo) throws SQLException {
        dao.atualizar(perfilAntigo, perfilNovo);
    }

    
    // * Remove um perfil de vendedor do repositório.
    // * @param perfil O DTO do perfil de vendedor a ser removido.
    // * @throws SQLException Se ocorrer um erro na persistência dos dados.
    
    public void removerPerfilVendedor(PerfilVendedorDTO perfil) throws SQLException {
        dao.deletar(perfil);
    }

    // * Limpa o repositório de perfis de vendedor, apagando e recriando a tabela.
    // * @throws SQLException Se ocorrer um erro ao apagar ou criar a tabela.
    
    public void limparRepositorio() throws SQLException {
        this.dao.apagarTabela();
        this.dao.criarTabela();
    }
}
