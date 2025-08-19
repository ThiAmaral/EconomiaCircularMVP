package br.ufes.economiacircularmvp.dto;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private int id; // ID gerado pelo banco de dados
    private String nome;
    private String username; // Usado para login, deve ser único
    private String senhaHash; // Senha já hasheada para segurança
    private String telefone;
    private String email;
    private LocalDateTime dataCriacao;
    private boolean isAdmin; // Flag para administrador do sistema [3]

    // Construtor completo
    public UsuarioDTO(int id, String nome, String username, String senhaHash, String telefone, String email, boolean isAdmin, LocalDateTime dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.senhaHash = senhaHash;
        this.telefone = telefone;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.isAdmin = isAdmin;
    }

    // Construtor para inserção (ID será auto-gerado)
    public UsuarioDTO(String nome, String username, String senhaHash, String telefone, String email, boolean isAdmin) {
        this.nome = nome;
        this.username = username;
        this.senhaHash = senhaHash;
        this.telefone = telefone;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", username='" + username + '\'' +
               ", telefone='" + telefone + '\'' +
               ", email='" + email + '\'' +
               ", isAdmin=" + isAdmin +
               '}';
    }
}
