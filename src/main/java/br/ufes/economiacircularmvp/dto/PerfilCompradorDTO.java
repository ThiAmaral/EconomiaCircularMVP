package br.ufes.economiacircularmvp.dto;

public class PerfilCompradorDTO {
    private int id; // ID gerado pelo banco de dados
    private int usuarioId; // Chave estrangeira para Usuario [11]
    private double reputacaoEstrelas; // Estrelas de reputação do comprador [11]
    private String nivelReputacao; // Nível: Bronze, Prata, Ouro [5, 11]
    private String insigniasPermanentes; // Insígnias, pode ser JSON ou CSV string [8, 11]
    private String seloVerificadorConfiavel; // Selo "Verificador Confiável", pode ser boolean ou string [11, 12]
    private String coletaveisTemporada; // Coletáveis de temporada, pode ser JSON ou CSV string [9, 11]
    private double totalGwpEvitadoPorCompras; // Total de GWP evitado por compras [11, 13]
    private String estatisticasDenuncias; // Estatísticas de denúncias, pode ser JSON ou CSV string [11]

    // Construtor completo
    public PerfilCompradorDTO(int id, int usuarioId, double reputacaoEstrelas, String nivelReputacao,
                              String insigniasPermanentes, String seloVerificadorConfiavel,
                              String coletaveisTemporada, double totalGwpEvitadoPorCompras,
                              String estatisticasDenuncias) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.reputacaoEstrelas = reputacaoEstrelas;
        this.nivelReputacao = nivelReputacao;
        this.insigniasPermanentes = insigniasPermanentes;
        this.seloVerificadorConfiavel = seloVerificadorConfiavel;
        this.coletaveisTemporada = coletaveisTemporada;
        this.totalGwpEvitadoPorCompras = totalGwpEvitadoPorCompras;
        this.estatisticasDenuncias = estatisticasDenuncias;
    }

    // Construtor para inserção (ID será auto-gerado, valores iniciais)
    public PerfilCompradorDTO(int usuarioId) {
        this.usuarioId = usuarioId;
        this.reputacaoEstrelas = 0.0; // Inicia com 0 estrelas [10]
        this.nivelReputacao = "Bronze"; // Inicia no nível Bronze [10]
        this.insigniasPermanentes = "[]";
        this.seloVerificadorConfiavel = "false"; // Por padrão, não tem o selo
        this.coletaveisTemporada = "[]";
        this.totalGwpEvitadoPorCompras = 0.0;
        this.estatisticasDenuncias = "";
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getReputacaoEstrelas() {
        return reputacaoEstrelas;
    }

    public void setReputacaoEstrelas(double reputacaoEstrelas) {
        this.reputacaoEstrelas = reputacaoEstrelas;
    }

    public String getNivelReputacao() {
        return nivelReputacao;
    }

    public void setNivelReputacao(String nivelReputacao) {
        this.nivelReputacao = nivelReputacao;
    }

    public String getInsigniasPermanentes() {
        return insigniasPermanentes;
    }

    public void setInsigniasPermanentes(String insigniasPermanentes) {
        this.insigniasPermanentes = insigniasPermanentes;
    }

    public String getSeloVerificadorConfiavel() {
        return seloVerificadorConfiavel;
    }

    public void setSeloVerificadorConfiavel(String seloVerificadorConfiavel) {
        this.seloVerificadorConfiavel = seloVerificadorConfiavel;
    }

    public String getColetaveisTemporada() {
        return coletaveisTemporada;
    }

    public void setColetaveisTemporada(String coletaveisTemporada) {
        this.coletaveisTemporada = coletaveisTemporada;
    }

    public double getTotalGwpEvitadoPorCompras() {
        return totalGwpEvitadoPorCompras;
    }

    public void setTotalGwpEvitadoPorCompras(double totalGwpEvitadoPorCompras) {
        this.totalGwpEvitadoPorCompras = totalGwpEvitadoPorCompras;
    }

    public String getEstatisticasDenuncias() {
        return estatisticasDenuncias;
    }

    public void setEstatisticasDenuncias(String estatisticasDenuncias) {
        this.estatisticasDenuncias = estatisticasDenuncias;
    }

    @Override
    public String toString() {
        return "PerfilCompradorDTO{" +
               "id=" + id +
               ", usuarioId=" + usuarioId +
               ", reputacaoEstrelas=" + reputacaoEstrelas +
               ", nivelReputacao='" + nivelReputacao + '\'' +
               ", totalGwpEvitadoPorCompras=" + totalGwpEvitadoPorCompras +
               '}';
    }
}
