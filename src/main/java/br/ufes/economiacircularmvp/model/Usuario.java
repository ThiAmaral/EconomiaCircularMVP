package br.ufes.economiacircularmvp.model;

import br.ufes.economiacircularmvp.perfil.PerfilComprador;
import br.ufes.economiacircularmvp.perfil.PerfilVendedor;

public class Usuario {
    private String nome;
    private String usuario;
    private String senha;
    private String contato;
    private PerfilVendedor perfilVendedor;
    private PerfilComprador perfilComprador;
    private boolean isAdmin;
    private boolean autenticado;
    
    public Usuario(String nome, String usuario, String senha, String contato){
        this.nome = nome;
        this.senha = senha;
        this.contato = contato;
        this.isAdmin = false;
        this.perfilVendedor = new PerfilVendedor();
        this.perfilComprador = new PerfilComprador();
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getContato() {
        return contato;
    }
    
    public PerfilVendedor getPerfilVendedor(){
        return this.perfilVendedor;
    }

    public PerfilComprador getPerfilComprador() {
        return perfilComprador;
    }
    
    public boolean isIsAdmin() {
        return isAdmin;
    }

    public boolean isAutenticado() {
        return autenticado;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setPerfilVendedor(PerfilVendedor perfilVendedor) {
        this.perfilVendedor = perfilVendedor;
    }

    public void setPerfilComprador(PerfilComprador perfilComprador) {
        this.perfilComprador = perfilComprador;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setAutenticado(boolean autententicado) {
        this.autenticado = autententicado;
    }
}
