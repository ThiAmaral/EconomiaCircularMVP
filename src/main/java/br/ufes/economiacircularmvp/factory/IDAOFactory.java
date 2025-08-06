package br.ufes.economiacircularmvp.factory;

import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.dao.IPerfilCompradorDAO;
import br.ufes.economiacircularmvp.dao.IPerfilVendedorDAO;
import br.ufes.economiacircularmvp.dao.IUsuarioDAO;

public interface IDAOFactory{

    IUsuarioDAO getUsuarioDAO();

    IPerfilVendedorDAO getPerfilVendedorDAO();

    IPerfilCompradorDAO getPerfilCompradorDAO();

    IGerenciarConexaoStrategy getgerenciarConexao();
}

