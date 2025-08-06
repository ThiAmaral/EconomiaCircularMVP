package br.ufes.economiacircularmvp.strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteGerenciarConexaoStrategy implements IGerenciarConexaoStrategy{

    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String H2_DRIVER = "org.h2.Driver";

    private String dbPathOrUrl;
    private String dbType; // "sqlite" ou "h2"

    public SQLiteGerenciarConexaoStrategy(String dbType, String dbPathOrUrl) {
        this.dbType = dbType;
        this.dbPathOrUrl = dbPathOrUrl;
        try {
            // Carrega o driver apropriado
            if ("sqlite".equalsIgnoreCase(dbType)) {
                Class.forName(SQLITE_DRIVER);
            } else if ("h2".equalsIgnoreCase(dbType)) {
                Class.forName(H2_DRIVER);
            } else {
                throw new IllegalArgumentException("Tipo de banco de dados não suportado: " + dbType);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC para " + dbType + ": " + e.getMessage());
            // Em um sistema real, você registraria isso no log [14] e talvez lançaria uma exceção Runtime
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if ("sqlite".equalsIgnoreCase(dbType)) {
            return DriverManager.getConnection("jdbc:sqlite:" + dbPathOrUrl);
        } else if ("h2".equalsIgnoreCase(dbType)) {
            // Para H2, a URL já inclui o caminho/nome do banco de dados (ex: jdbc:h2:./data/ecocircular_h2)
            return DriverManager.getConnection(dbPathOrUrl);
        }
        throw new SQLException("Tipo de banco de dados desconhecido ou não configurado.");
    }

    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                // TODO: Implementar lógica de logging [14]
            }
        }
    }

    @Override
    public String getDbType() {
        return dbType;
    }
}