package br.ufes.economiacircularmvp.adapter;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfiguracaoAdapter {
    
    public static final String H2_DB_URL = "jdbc:h2:file:~/h2EconomiaCircularMVP.db";
    public static final String SQLITE_DB_URL = "jdbc:sqlite:file:~/sqliteEconomiaCircularMVPb";
    
    // Ou para um banco de dados em memória:
    // public static final String H2_DB_URL = "jdbc:h2:mem:testdb";
    
    private static final Dotenv dotenv;
    
    static {
        dotenv = Dotenv.configure()
                .directory("./") // Procura o .env na raiz do projeto
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }
    
    public static String getValor(String chave) {
        return dotenv.get(chave);
    }
}