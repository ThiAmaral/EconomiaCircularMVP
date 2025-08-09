package br.ufes.economiacircularmvp.factory;

import br.ufes.economiacircularmvp.adapter.ConfiguracaoAdapter;
import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.repository.H2UsuarioRepository;
import br.ufes.economiacircularmvp.repository.IUsuarioRepository;
import br.ufes.economiacircularmvp.repository.SQLiteUsuarioRepository;
import java.sql.SQLException;

public class RepositoryFactory {
        
    public static IDAOFactory getDAOFactory(){
       // Não é mais necessário instanciar o adapter.
       String sgbd = ConfiguracaoAdapter.getValor("SGBD");
       
       if (sgbd == null) {
           throw new RuntimeException("A chave 'SGBD' não foi encontrada no arquivo de configuração .env.");
       } else if (sgbd.equalsIgnoreCase("sqlite")) {
           return new SQLiteDAOFactory();
       } else if (sgbd.equalsIgnoreCase("h2")) {
           return new H2DAOFactory();
       } else {
           throw new IllegalArgumentException("SGBD '" + sgbd + "' não suportado. Verifique o arquivo .env.");
       }
   }
    
    public static IUsuarioRepository getUsuarioRepository(IUsuarioDAO usuarioDAO) throws SQLException {
        // Não é mais necessário instanciar o adapter.
       String sgbd = ConfiguracaoAdapter.getValor("SGBD");
       if (sgbd == null) {
           throw new RuntimeException("A chave 'SGBD' não foi encontrada no arquivo de configuração .env.");
       } else if (sgbd.equalsIgnoreCase("sqlite")) {
           return new SQLiteUsuarioRepository(usuarioDAO);
       } else if (sgbd.equalsIgnoreCase("h2")) {
           return new H2UsuarioRepository(usuarioDAO);
       } else {
           throw new IllegalArgumentException("SGBD '" + sgbd + "' não suportado. Verifique o arquivo .env.");
       }
    }
}
