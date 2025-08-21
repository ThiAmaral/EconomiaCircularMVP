package br.ufes.economiacircularmvp.dto;

import java.time.LocalDateTime;

public class ItemDTO {

    private int id;
    private String idCircular;
    private int idVendedor;
    private String tipoPeca;
    private String subcategoria;
    private String tamanho;
    private String corPredominante;
    private String composicaoPrincipal;
    private double massaEstimada;
    private String estadoConservacao;
    private String defeitos; // Armazenado como uma string (ex: JSON ou CSV)
    private double precoBase;
    private double precoFinal;
    private double gwpAvoided;
    private double mciItem;
    private LocalDateTime dataPublicacao;
    private boolean vendido;

    // Construtor completo
    public ItemDTO(int id, String idCircular, int idVendedor, String tipoPeca, String subcategoria, String tamanho, String corPredominante, String composicaoPrincipal, double massaEstimada, String estadoConservacao, String defeitos, double precoBase, double precoFinal, double gwpAvoided, double mciItem, LocalDateTime dataPublicacao, boolean vendido) {
        this.id = id;
        this.idCircular = idCircular;
        this.idVendedor = idVendedor;
        this.tipoPeca = tipoPeca;
        this.subcategoria = subcategoria;
        this.tamanho = tamanho;
        this.corPredominante = corPredominante;
        this.composicaoPrincipal = composicaoPrincipal;
        this.massaEstimada = massaEstimada;
        this.estadoConservacao = estadoConservacao;
        this.defeitos = defeitos;
        this.precoBase = precoBase;
        this.precoFinal = precoFinal;
        this.gwpAvoided = gwpAvoided;
        this.mciItem = mciItem;
        this.dataPublicacao = dataPublicacao;
        this.vendido = vendido;
    }
    
    // Construtor para inserção
    public ItemDTO(String idCircular, int idVendedor, String tipoPeca, String subcategoria, String tamanho, String corPredominante, String composicaoPrincipal, double massaEstimada, String estadoConservacao, String defeitos, double precoBase) {
        this.idCircular = idCircular;
        this.idVendedor = idVendedor;
        this.tipoPeca = tipoPeca;
        this.subcategoria = subcategoria;
        this.tamanho = tamanho;
        this.corPredominante = corPredominante;
        this.composicaoPrincipal = composicaoPrincipal;
        this.massaEstimada = massaEstimada;
        this.estadoConservacao = estadoConservacao;
        this.defeitos = defeitos;
        this.precoBase = precoBase;
        this.dataPublicacao = LocalDateTime.now();
        this.vendido = false;
    }


    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIdCircular() { return idCircular; }
    public void setIdCircular(String idCircular) { this.idCircular = idCircular; }
    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }
    public String getTipoPeca() { return tipoPeca; }
    public void setTipoPeca(String tipoPeca) { this.tipoPeca = tipoPeca; }
    public String getSubcategoria() { return subcategoria; }
    public void setSubcategoria(String subcategoria) { this.subcategoria = subcategoria; }
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public String getCorPredominante() { return corPredominante; }
    public void setCorPredominante(String corPredominante) { this.corPredominante = corPredominante; }
    public String getComposicaoPrincipal() { return composicaoPrincipal; }
    public void setComposicaoPrincipal(String composicaoPrincipal) { this.composicaoPrincipal = composicaoPrincipal; }
    public double getMassaEstimada() { return massaEstimada; }
    public void setMassaEstimada(double massaEstimada) { this.massaEstimada = massaEstimada; }
    public String getEstadoConservacao() { return estadoConservacao; }
    public void setEstadoConservacao(String estadoConservacao) { this.estadoConservacao = estadoConservacao; }
    public String getDefeitos() { return defeitos; }
    public void setDefeitos(String defeitos) { this.defeitos = defeitos; }
    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }
    public double getPrecoFinal() { return precoFinal; }
    public void setPrecoFinal(double precoFinal) { this.precoFinal = precoFinal; }
    public double getGwpAvoided() { return gwpAvoided; }
    public void setGwpAvoided(double gwpAvoided) { this.gwpAvoided = gwpAvoided; }
    public double getMciItem() { return mciItem; }
    public void setMciItem(double mciItem) { this.mciItem = mciItem; }
    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDateTime dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    public boolean isVendido() { return vendido; }
    public void setVendido(boolean vendido) { this.vendido = vendido; }
}