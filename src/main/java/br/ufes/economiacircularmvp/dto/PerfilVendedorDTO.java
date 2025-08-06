package br.ufes.economiacircularmvp.dto;

public class PerfilVendedorDTO {
    private int id; // ID gerado pelo banco de dados
    private int usuarioId; // Chave estrangeira para Usuario [4]
    private double reputacaoEstrelas; // Estrelas de reputação do vendedor [4]
    private String nivelReputacao; // Nível: Bronze, Prata, Ouro [4, 5]
    private double beneficioClimaticoAcumulado; // GWP_avoided acumulado [4, 6, 7]
    private String insigniasPermanentes; // Lista de insígnias, pode ser JSON ou CSV string [4, 8]
    private String selosTemporada; // Selos de temporada, pode ser JSON ou CSV string [4, 9]
    private String rankingSemanalVigente; // Pode ser um ID de ranking ou descrição [4, 9]
    private String indicadoresCuradoria; // Outros indicadores de curadoria, pode ser JSON ou CSV string [4]

    // Construtor completo
    public PerfilVendedorDTO(int id, int usuarioId, double reputacaoEstrelas, String nivelReputacao,
                             double beneficioClimaticoAcumulado, String insigniasPermanentes,
                             String selosTemporada, String rankingSemanalVigente, String indicadoresCuradoria) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.reputacaoEstrelas = reputacaoEstrelas;
        this.nivelReputacao = nivelReputacao;
        this.beneficioClimaticoAcumulado = beneficioClimaticoAcumulado;
        this.insigniasPermanentes = insigniasPermanentes;
        this.selosTemporada = selosTemporada;
        this.rankingSemanalVigente = rankingSemanalVigente;
        this.indicadoresCuradoria = indicadoresCuradoria;
    }

    // Construtor para inserção (ID será auto-gerado, valores iniciais)
    public PerfilVendedorDTO(int usuarioId) {
        this.usuarioId = usuarioId;
        this.reputacaoEstrelas = 0.0; // Inicia com 0 estrelas [10]
        this.nivelReputacao = "Bronze"; // Inicia no nível Bronze [10]
        this.beneficioClimaticoAcumulado = 0.0;
        this.insigniasPermanentes = "[]"; // Representa uma lista vazia em JSON
        this.selosTemporada = "[]";
        this.rankingSemanalVigente = "";
        this.indicadoresCuradoria = "";
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

    public double getBeneficioClimaticoAcumulado() {
        return beneficioClimaticoAcumulado;
    }

    public void setBeneficioClimaticoAcumulado(double beneficioClimaticoAcumulado) {
        this.beneficioClimaticoAcumulado = beneficioClimaticoAcumulado;
    }

    public String getInsigniasPermanentes() {
        return insigniasPermanentes;
    }

    public void setInsigniasPermanentes(String insigniasPermanentes) {
        this.insigniasPermanentes = insigniasPermanentes;
    }

    public String getSelosTemporada() {
        return selosTemporada;
    }

    public void setSelosTemporada(String selosTemporada) {
        this.selosTemporada = selosTemporada;
    }

    public String getRankingSemanalVigente() {
        return rankingSemanalVigente;
    }

    public void setRankingSemanalVigente(String rankingSemanalVigente) {
        this.rankingSemanalVigente = rankingSemanalVigente;
    }

    public String getIndicadoresCuradoria() {
        return indicadoresCuradoria;
    }

    public void setIndicadoresCuradoria(String indicadoresCuradoria) {
        this.indicadoresCuradoria = indicadoresCuradoria;
    }

    @Override
    public String toString() {
        return "PerfilVendedorDTO{" +
               "id=" + id +
               ", usuarioId=" + usuarioId +
               ", reputacaoEstrelas=" + reputacaoEstrelas +
               ", nivelReputacao='" + nivelReputacao + '\'' +
               ", beneficioClimaticoAcumulado=" + beneficioClimaticoAcumulado +
               '}';
    }
}
