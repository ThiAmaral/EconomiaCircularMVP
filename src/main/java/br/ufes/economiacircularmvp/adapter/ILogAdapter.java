package br.ufes.economiacircularmvp.adapter;

import java.sql.SQLException;

public interface ILogAdapter {
    
    public void logSucesso(String operacao, String usuario) throws SQLException;
    public void logFalha(String operacao, String usuario, String mensagemFalha) throws SQLException ;
}
