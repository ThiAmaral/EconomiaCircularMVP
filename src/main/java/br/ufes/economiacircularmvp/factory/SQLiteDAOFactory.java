package br.ufes.economiacircularmvp.factory;

import br.ufes.economiacircularmvp.strategy.IGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.strategy.SQLiteGerenciarConexaoStrategy;
import br.ufes.economiacircularmvp.adapter.ConfiguracaoAdapter;
import br.ufes.economiacircularmvp.dao.IPerfilCompradorDAO;
import br.ufes.economiacircularmvp.dao.IPerfilVendedorDAO;
import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.dao.sqlite.PerfilCompradorDAOSQLite;
import br.ufes.economiacircularmvp.dao.sqlite.PerfilVendedorDAOSQLite;
import br.ufes.economiacircularmvp.dao.sqlite.UsuarioDAOSQLite;

public class SQLiteDAOFactory implements IDAOFactory{
    private static IGerenciarConexaoStrategy gerenciarConexao;
    private static String SQLITE_DB_URL = "jdbc:sqlite:file:~/sqliteEconomiaCircularMVP";
    private static String SQLITE_DB_PATH = "./sqliteEconomiaCircularMVP.db";

    // Inicializa o gerenciador de conexões com base na configuração da aplicação
    static {
        gerenciarConexao = new SQLiteGerenciarConexaoStrategy(ConfiguracaoAdapter.getValor("SGBD"), SQLITE_DB_PATH);
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOSQLite(gerenciarConexao);
    }

    @Override
    public IPerfilVendedorDAO getPerfilVendedorDAO() {
        return new PerfilVendedorDAOSQLite(gerenciarConexao);
    }

    @Override
    public IPerfilCompradorDAO getPerfilCompradorDAO() {
        return new PerfilCompradorDAOSQLite(gerenciarConexao);
    }

    @Override
    public IGerenciarConexaoStrategy getgerenciarConexao() {
        return gerenciarConexao;
    }
}
