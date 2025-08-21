package br.ufes.economiacircularmvp.service;

import br.ufes.economiacircularmvp.model.Usuario;
import br.ufes.economiacircularmvp.perfil.PerfilVendedor;
import br.ufes.economiacircularmvp.repository.IPerfilVendedorRepository;

public class ManterPerfilVendedorService {

    // Dependência do repositório para persistência de dados (RNF03 [81])
    private IPerfilVendedorRepository perfilVendedorRepository; // Exemplo de injeção de dependência

    // Construtor, recebendo a dependência do repositório
    
    public ManterPerfilVendedorService(IPerfilVendedorRepository perfilVendedorRepository) {
        this.perfilVendedorRepository = perfilVendedorRepository;
    }
    

    
     //Cria um novo perfil de vendedor e o associa a um usuário existente.
     //@param usuario O objeto Usuario ao qual o perfil de vendedor será associado.
     //@return O PerfilVendedor recém-criado.
     // Esta é uma função de exemplo para ilustração.
     
    public PerfilVendedor criarPerfilVendedor(Usuario usuario) {
        // 1. Cria uma nova instância de PerfilVendedor
        PerfilVendedor novoPerfil = new PerfilVendedor();
        // Os atributos iniciais (Bronze, 0 estrelas) já são definidos no construtor do PerfilVendedor [22].

        // 2. Associa o perfil ao usuário
        usuario.setPerfilVendedor(novoPerfil);

        // 3. Persiste o novo perfil (usando o padrão Repository - RNF03 [81])
        // perfilVendedorRepository.salvar(novoPerfil); // Exemplo de persistência

        // 4. Retorna o perfil criado
        System.out.println("Perfil de Vendedor criado para o usuário: " + usuario.getNome());
        return novoPerfil;
    }

    
    // * Atualiza os dados de um perfil de vendedor, incluindo a pontuação de reputação.
    // * Este é um exemplo simplificado; a lógica de reputação é complexa [23-26].
    // * @param perfilVendedor O perfil a ser atualizado.
    // * @param acao A ação que gerou pontos (ex: "Cadastro de item completo").
    
    public void atualizarReputacaoVendedor(PerfilVendedor perfilVendedor, String acao) {
        double pontos = 0.0;
        String insignia = null;

        switch (acao) {
            case "Cadastro de item completo":
                pontos = 0.05; // [23]
                insignia = "Primeiro Anúncio"; // [27]
                break;
            case "Resposta à oferta em até 24 horas":
                pontos = 0.05; // [24]
                break;
            case "Transação concluída":
                pontos = 0.5; // [25]
                // Verificar "Cinco Vendas" [28]
                break;
            case "Avaliação textual após venda":
                pontos = 0.05; // [25]
                break;
            // ... outros casos conforme as regras de pontuação [23-26]
        }

        if (pontos > 0) {
            perfilVendedor.adicionarEstrelas(pontos);
            System.out.println("Reputação do vendedor atualizada. Estrelas: " + perfilVendedor.getEstrelasReputacao() + ", Nível: " + perfilVendedor.getNivel());
        }
        if (insignia != null) {
            perfilVendedor.adicionarInsigniaPermanente(insignia);
            System.out.println("Nova insígnia concedida: " + insignia);
        }

        // perfilVendedorRepository.atualizar(perfilVendedor); // Exemplo de persistência
    }

    // * Adiciona o benefício climático de uma venda ao perfil do vendedor.
    // * @param perfilVendedor O perfil do vendedor.
    // * @param gwpAvoided O valor de GWP_avoided da transação [8, 40, 42].
    
    public void adicionarBeneficioClimatico(PerfilVendedor perfilVendedor, double gwpAvoided) {
        perfilVendedor.adicionarBeneficioClimatico(gwpAvoided);
        System.out.println("Benefício climático do vendedor atualizado: " + perfilVendedor.getBeneficioClimaticoAcumulado() + " kg CO₂e");
        // perfilVendedorRepository.atualizar(perfilVendedor);
    }

    // Outros métodos CRUD ou de lógica de negócio para o vendedor (consultar, editar, etc.) [67]
}
