package br.ufes.economiacircularmvp.strategy;

import java.sql.Connection;
import java.sql.SQLException;

public interface IGerenciarConexaoStrategy {
    
    public Connection getConnection() throws SQLException;

    public void closeConnection(Connection conn);

    public String getDbType();
}
