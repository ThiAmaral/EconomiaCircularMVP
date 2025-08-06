package br.ufes.economiacircularmvp.perfil;

import br.ufes.economiacircularmvp.perfil.IPerfil;
import java.util.ArrayList;
import java.util.List;

public class PerfilComprador implements IPerfil{
    private String nivel;             // Nível de reputação (Bronze, Prata, Ouro) [26]
    private double estrelasReputacao; // Estrelas acumuladas como comprador [23]
    private List<String> insigniasPermanentes; // Insígnias (Primeira Oferta, Dez Compras) [28, 69]
    private boolean seloVerificadorConfiavel; // Selo "Verificador Confiável" [30, 69]
    private List<String> coletaveisTemporada;  // Coletáveis visuais de temporada [29, 69]
    private double totalGWPEvitadoPorCompras; // GWP_avoided acumulado por compras [40, 43, 58, 69]
    // private Map<String, Object> estatisticasDenuncias; // Estatísticas de denúncias [69]
    // ... outros atributos específicos do comprador

    public PerfilComprador() {
        this.nivel = "Bronze"; // Nível inicial [22]
        this.estrelasReputacao = 0.0; // Estrelas iniciais [22]
        this.insigniasPermanentes = new ArrayList<>();
        this.coletaveisTemporada = new ArrayList<>();
        this.seloVerificadorConfiavel = false;
        this.totalGWPEvitadoPorCompras = 0.0;
        // this.estatisticasDenuncias = new HashMap<>();
    }

    // Implementação dos métodos da interface IPerfil
    @Override
    public String getNivel() {
        return this.nivel;
    }

    @Override
    public double getEstrelasReputacao() {
        return this.estrelasReputacao;
    }

    @Override
    public void adicionarEstrelas(double quantidade) {
        this.estrelasReputacao += quantidade;
        verificarPromocaoDeNivel(); // Verifica se o nível deve ser atualizado [26]
    }

    @Override
    public void verificarPromocaoDeNivel() {
        // Lógica de promoção de nível, similar ao PerfilVendedor [26]
        if (this.nivel.equals("Bronze") && this.estrelasReputacao >= 5.0) {
            this.nivel = "Prata";
            this.estrelasReputacao = 5.0;
        } else if (this.nivel.equals("Prata") && this.estrelasReputacao >= 10.0) {
            this.nivel = "Ouro";
            this.estrelasReputacao = 10.0;
        }
    }

    // Getters e Setters específicos do comprador
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setEstrelasReputacao(double estrelasReputacao) {
        this.estrelasReputacao = estrelasReputacao;
    }

    public List<String> getInsigniasPermanentes() {
        return insigniasPermanentes;
    }

    public void adicionarInsigniaPermanente(String insignia) {
        if (!this.insigniasPermanentes.contains(insignia)) {
            this.insigniasPermanentes.add(insignia);
        }
    }

    public boolean isSeloVerificadorConfiavel() {
        return seloVerificadorConfiavel;
    }

    public void setSeloVerificadorConfiavel(boolean seloVerificadorConfiavel) {
        this.seloVerificadorConfiavel = seloVerificadorConfiavel;
    }

    public double getTotalGWPEvitadoPorCompras() {
        return totalGWPEvitadoPorCompras;
    }

    public void adicionarGWPEvitadoPorCompra(double gwpAvoided) {
        this.totalGWPEvitadoPorCompras += gwpAvoided;
    }

    // ... e outros getters/setters/métodos para os demais atributos do comprador.
}
