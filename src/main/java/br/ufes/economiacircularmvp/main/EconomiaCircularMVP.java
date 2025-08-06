package br.ufes.economiacircularmvp.main;

import br.ufes.economiacircularmvp.dao.IUsuarioDAO;
import br.ufes.economiacircularmvp.factory.IDAOFactory;
import br.ufes.economiacircularmvp.factory.RepositoryFactory;

public class EconomiaCircularMVP {

    IDAOFactory fabrica = RepositoryFactory.getDAOFactory();
    
    IUsuarioDAO usuarioRepository = fabrica.getUsuarioDAO();
    
    
}
