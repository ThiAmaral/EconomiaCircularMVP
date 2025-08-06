package br.ufes.economiacircularmvp.factory;

import br.ufes.economiacircularmvp.strategy.H2GerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.adapter.ConfiguracaoAdapter;
import br.ufes.economiacircularmvp.dao.IPerfilCompradorDAO;
import br.ufes.economiacircularmvp.dao.IPerfilVendedorDAO;
import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.dao.h2.PerfilCompradorDAOH2;
import br.ufes.economiacircularmvp.dao.h2.PerfilVendedorDAOH2;
import br.ufes.economiacircularmvp.dao.h2.UsuarioDAOH2;

public class H2DAOFactory implements IDAOFactory{
    private static IGerenciarConexaoStrategy gerenciarConexao;
    private static String H2_DB_URL = "jdbc:h2:file:~/h2EconomiaCircularMVP.db";
    private static String H2_DB_PATH = "~/h2EconomiaCircularMVPb";

    // Inicializa o gerenciador de conexões com base na configuração da aplicação
    static { 
        gerenciarConexao = new H2GerenciarConexaoStrategy(ConfiguracaoAdapter.getValor("SGBD"), H2_DB_PATH);
    }
    
    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOH2(gerenciarConexao);
    }

    @Override
    public IPerfilVendedorDAO getPerfilVendedorDAO() {
        return new PerfilVendedorDAOH2(gerenciarConexao);
    }

    @Override
    public IPerfilCompradorDAO getPerfilCompradorDAO() {
        return new PerfilCompradorDAOH2(gerenciarConexao);
    }

    @Override
    public IGerenciarConexaoStrategy getgerenciarConexao() {
        return gerenciarConexao;
    }
}
