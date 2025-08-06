package br.ufes.economiacircularmvp.perfil;

import java.util.ArrayList;
import java.util.List;

public class PerfilVendedor implements IPerfil {
    private String nivel;             // Nível de reputação (Bronze, Prata, Ouro) [26]
    private double estrelasReputacao; // Estrelas acumuladas como vendedor [23]
    private List<String> insigniasPermanentes; // Lista de insígnias (Primeiro Anúncio, Cinco Vendas) [27, 68]
    private List<String> selosTemporada;       // Selos visuais de temporada [29, 68]
    private String rankingSemanalVigente;      // Posição no ranking de crescimento semanal [29, 68]
    private double beneficioClimaticoAcumulado; // Soma de GWP_avoided de vendas [40, 42, 53, 68]
    // private Map<String, Object> indicadoresCuradoria; // Se houver indicadores específicos de curadoria [68]
    // ... outros atributos específicos do vendedor

    public PerfilVendedor() {
        this.nivel = "Bronze"; // Nível inicial [22]
        this.estrelasReputacao = 0.0; // Estrelas iniciais [22]
        this.insigniasPermanentes = new ArrayList<>();
        this.selosTemporada = new ArrayList<>();
        this.beneficioClimaticoAcumulado = 0.0;
        // this.indicadoresCuradoria = new HashMap<>();
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
        // Lógica de promoção de nível [26]
        if (this.nivel.equals("Bronze") && this.estrelasReputacao >= 5.0) {
            this.nivel = "Prata";
            this.estrelasReputacao = 5.0; // Reinicia a contagem de estrelas para o próximo nível ou mantém em 5 e acumula para visualização
            // (A regra de progresso "por blocos de cinco estrelas" e "atingir 5 estrelas" pode ser interpretada de diferentes formas para acumulo vs visualização.
            // Para simplicidade, aqui reinicia ao mudar de nível).
        } else if (this.nivel.equals("Prata") && this.estrelasReputacao >= 10.0) { // Exemplo: 5 Bronze + 5 Prata
            this.nivel = "Ouro";
            this.estrelasReputacao = 10.0;
        }
        // A regra diz "O mesmo vale para transições de Prata para Ouro" e "o progresso continua até o limite de 5 estrelas Ouro" [26].
        // Isso sugere que as estrelas continuam a acumular mas o nível não regride [26].
        // A lógica acima é um exemplo simplificado. O sistema de reputação é detalhado nas fontes [22-31].
    }

    // Getters e Setters específicos do vendedor
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

    public double getBeneficioClimaticoAcumulado() {
        return beneficioClimaticoAcumulado;
    }

    public void adicionarBeneficioClimatico(double gwpAvoided) {
        this.beneficioClimaticoAcumulado += gwpAvoided;
    }

    // ... e outros getters/setters/métodos para os demais atributos do vendedor.
}
