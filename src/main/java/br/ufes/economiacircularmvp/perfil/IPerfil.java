package br.ufes.economiacircularmvp.perfil;

public interface IPerfil {
     public String getNivel();
     public double getEstrelasReputacao();
     public void adicionarEstrelas(double quantidade);
     public void verificarPromocaoDeNivel();
}
