package br.ufes.economiacircularmvp.factory;

import br.ufes.economiacircularmvp.adapter.ConfiguracaoAdapter;

public class RepositoryFactory {
        
    public static IDAOFactory getDAOFactory() {
       ConfiguracaoAdapter configuracaoAdapter = new ConfiguracaoAdapter();
       String sgbd = configuracaoAdapter.getValor("SGBD");
       
       if (sgbd == null) {
           throw new RuntimeException("A chave 'SGBD' não foi encontrada no arquivo de configuração.");
       }
       if (sgbd.equalsIgnoreCase("sqlite")) {
           return new SQLiteDAOFactory();
       } else if (sgbd.equalsIgnoreCase("h2")) {
           return new H2DAOFactory();
       } else {
           throw new IllegalArgumentException("SGBD não suportado.");
       }
   }
}
