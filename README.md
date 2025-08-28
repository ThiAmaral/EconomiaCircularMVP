# Relatório Trabalho Final Projeto de Sistemas de Software

## Grupo:
#### Paulo Rizzi
#### Thiago Machado

___________________________________________________________________________________________________________________________________________________________________________________________________________________

### Requisitos Funcionais (RF)

#### Gerenciamento de Perfis e Usuários:

**RF01: O sistema deve permitir o cadastro de usuários com informações como nome completo ou razão social, e-mail, telefone (opcional), e credenciais de acesso - FEITO**

**RF02: O primeiro usuário cadastrado no sistema deve ser automaticamente designado como administrador, com permissões para monitorar o ambiente. - FEITO**

<sup>RF03: Contas de administrador não podem criar ou receber perfis comerciais (vendedor/comprador).</sup>

**RF04: Usuários regulares (não administradores) devem poder criar um perfil de vendedor, de comprador, ou ambos, após a autenticação.- FEITO**

<sup>RF05: Quando um usuário possuir ambos os perfis (vendedor e comprador), o sistema deve solicitar a escolha de um contexto de uso (venda ou compra) após o login.</sup>

**RF06: O perfil de vendedor deve armazenar nível de reputação (Bronze, Prata, Ouro), total de estrelas, contagem de vendas, insígnias, selos de temporada, "Benefício climático contribuído" (em kg de CO2​e), número de denúncias recebidas e taxonomias de curadoria. - FEITO**

<sup>RF07: O perfil de comprador deve armazenar nível e estrelas de reputação, contagem de compras, insígnias, selo "Verificador Confiável", total de CO2​e evitado, e estatísticas de denúncias procedentes.</sup>

<sup>RF08: O sistema deve permitir que o administrador gerencie uma tabela de defeitos e seus respectivos percentuais de abatimento de preço.</sup>

<sup>RF09: O sistema deve permitir que o administrador gerencie uma tabela com os fatores de emissão de CO2​e por tipo de material.</sup>

___________________________________________________________________________________________________________________________________________________________________________________________________________________

#### Gerenciamento de Catálogo e Itens:

<sup>RF10: Vendedores podem ter no máximo trinta anúncios ativos simultaneamente.</sup>

<sup>RF11: O cadastro de um item deve conter os seguintes campos obrigatórios: ID-C, tipo de peça, subcategoria, tamanho, cor predominante, composição principal, massa estimada, estado de conservação, defeitos e preço-base.</sup>

<sup>RF12: O sistema deve gerar um Identificador Circular (ID-C) alfanumérico de 12 caracteres para um item que é anunciado pela primeira vez.</sup>

<sup>RF13: Para itens de revenda, o vendedor deve informar o ID-C existente, e o sistema deve validar sua existência e o estado "encerrado" antes de permitir a republicação.</sup>

<sup>RF14: O sistema deve aplicar abatimentos percentuais cumulativos ao preço-base do item, com base nos defeitos informados pelo vendedor.</sup>

<sup>RF15: O preço final de um item, após os descontos, não pode ser inferior a 5% do valor inicial.</sup>

<sup>RF16: O sistema deve calcular e exibir os indicadores ambientais Material Circularity Indicator (MCI) e Global Warming Potential (GWP_avoided) para cada item no catálogo.</sup>

<sup>RF17: O sistema deve permitir ao vendedor editar as informações de um anúncio, recalculando automaticamente o preço final e o MCI_item caso os defeitos sejam alterados. Transações (Oferta e Venda):</sup>

<sup>RF18: Compradores devem poder fazer ofertas com valor de 1% a 20% abaixo do preço final do item já com os descontos aplicados.</sup>

<sup>RF19: O vendedor pode aceitar apenas uma oferta por item, encerrando a venda.</sup>

<sup>RF20: Após o aceite da oferta, o sistema deve registrar a transação como "venda finalizada", armazenando o ID-C, o comprador e o vendedor.</sup>

<sup>RF21: Após a conclusão da venda, o valor do GWP_avoided do item deve ser somado ao total acumulado nos perfis do comprador e do vendedor.</sup>
Reputação e Gamificação:

<sup>RF22: O sistema deve possuir um sistema de reputação segmentado para vendedores e compradores, com níveis (Bronze, Prata, Ouro) e pontuação em estrelas.</sup>

<sup>RF23: O sistema deve atribuir pontuação (frações de estrela) para ações específicas, como:
Vendedor: Cadastro completo de item (+0,05) e resposta a ofertas em até 24 horas (+0,05).
Comprador: Envio de oferta dentro da faixa permitida (+0,05).
Ambos: Avaliação textual após a venda (+0,05) e conclusão de transação (+0,5).
Denunciante: Denúncia procedente (+0,1).</sup>

<sup>RF24: O sistema deve conceder insígnias permanentes e pontos (+0,2 estrela) por ações inéditas, como "Primeiro Anúncio", "Primeira Oferta", "Cinco Vendas", "Dez Compras" e "Guardião da Qualidade".</sup>

<sup>RF25: O administrador do sistema deve poder gerenciar desafios semanais rotativos.</sup>

<sup>RF26: O sistema deve conceder pontos (+0,15 estrela) aos usuários que cumprirem os desafios semanais.</sup>

<sup>RF27: O sistema deve gerar e exibir um ranking semanal com os dez usuários que mais acumularam estrelas.</sup>

<sup>RF28: O administrador deve poder gerenciar selos visuais de temporada, que são atribuídos a usuários que atingem metas trimestrais, sem impacto na pontuação.</sup>

<sup>RF29: O sistema deve aplicar penalidades por conduta inadequada, como a perda de 0,5 estrela por descrição enganosa.</sup>

<sup>RF30: O sistema deve bloquear novas publicações e ofertas de um usuário após o registro de três infrações.</sup>

<sup>RF31: Usuários com 80% ou mais de denúncias validadas devem receber o selo "Verificador Confiável" e um bônus mensal de +0,2 estrela.</sup>

<sup>RF32: Vendedores no nível Ouro devem poder aplicar um selo "Peça Curada" em seus anúncios.</sup>
___________________________________________________________________________________________________________________________________________________________________________________________________________________

#### Rastreabilidade e Monitoramento:

<sup>RF33: O sistema deve manter uma linha do tempo para cada item (via ID-C), registrando eventos como publicação, ofertas, aceite, avaliação, revenda, reparo ou reciclagem.

<sup>RF34: O sistema deve registrar o número do ciclo de vida de um item (ciclo_n), incrementando-o a cada revenda.

<sup>RF35: O sistema deve disponibilizar uma tela de "Dashboard" para consulta, com gráficos (usando JFreeChart) sobre reputação, GWP_avoided, materiais em circulação e o ranking de crescimento.

<sup>RF36: O administrador deve poder exportar um arquivo CSV com os dados de itens vendidos, contendo ID-C, data da venda, massa, GWP_base, GWP_avoided e MCI_item.

<sup>RF37: O sistema deve realizar uma auditoria semanal automática para consolidar métricas, verificar a integridade dos dados (como o intervalo do MCI_item) e aplicar bônus.</sup>

___________________________________________________________________________________________________________________________________________________________________________________________________________________

#### Registro de Logs:

**RF38: O sistema deve registrar em um arquivo de log as operações de criação, alteração ou exclusão de itens, conclusão de transações e exportação de dados- FEITO.**

**RF39: As mensagens de log devem seguir um formato específico, incluindo ID-C (quando aplicável), tipo de operação, nome do contato, data, hora e usuário.- FEITO**

**RF40: As mensagens de log para operações com falha devem seguir um formato específico, incluindo a mensagem da falha.- FEITO**

<sup>RF41: O sistema deve ter uma tela de "Configuração" que permita ao usuário escolher o formato do arquivo de log (CSV ou JSON) em tempo de execução.</sup>

<sup>RF42: A escolha do formato de log deve ser persistida pelo sistema.</sup>

<sup>RF43: O rodapé da interface do sistema deve exibir o nome do usuário logado.</sup>

___________________________________________________________________________________________________________________________________________________________________________________________________________________

#### Requisitos Não Funcionais (RNF)

**RNF01 (Tecnologia): O sistema deve ser desenvolvido em linguagem Java 17 e estruturado como um projeto Maven.- FEITO**

**RNF02 (Padrão de Arquitetura): A arquitetura do sistema deve seguir o padrão MVP (Passive View).- FEITO**

**RNF03 (Interface Gráfica): A interface gráfica deve ser desenvolvida utilizando o kit de componentes Java Swing.- FEITO**

**RNF04 (Interface Gráfica): O sistema deve utilizar o padrão MDI (Multiple Document Interface), permitindo que o usuário navegue entre múltiplas janelas abertas.- FEITO**

<sup>RNF05 (Padrão de Projeto): As operações de CRUD devem seguir um padrão de navegação específico e utilizar o padrão State.</sup>

<sup>RNF06 (Padrão de Projeto): O padrão Command deve ser utilizado em conjunto com o padrão State.</sup>

<sup>RNF07 (Padrão de Projeto): O padrão Observer deve ser implementado para que, ao alterar um dado em uma janela, outras janelas abertas que exibem o mesmo dado sejam atualizadas.

**RNF08 (Persistência): Deve ser utilizado um banco de dados que não exija instalação ou configuração adicional, preferencialmente SQLite ou H2.- FEITO**

**RNF09 (Persistência): O banco de dados deve ter uma carga inicial de dados (seeder).- FEITO**

**RNF10 (Persistência): Não é permitido o uso de Docker para o banco de dados.- FEITO**

**RNF11 (Padrão de Projeto): O padrão de projeto Repository deve ser utilizado para a camada de persistência.- FEITO**

**RNF12 (Persistência): Não é permitido o uso de frameworks de persistência como Hibernate.- FEITO**

**RNF13 (Tratamento de Exceções): O tratamento de exceções deve ser implementado em todo o projeto.- FEITO**

**RNF14 (Controle de Versão): A entrega do projeto deve ser feita através de um repositório no GitHub.- FEITO**

**RNF15 (Módulo de Log): O módulo de log deve ser um projeto Maven independente e utilizar o padrão Adapter.- FEITO**

**RNF16 (Dependências): A dependência do módulo de log deve ser incluída no projeto principal como um JAR via JitPack.- FEITO**

**RNF17 (Validação de Senhas): Para a criação de senhas, o sistema deve utilizar o componente "Validador de senhas" disponível no GitHub, incluído como um JAR via JitPack.- FEITO**

**RNF18 (Bônus de Nota): Equipes que utilizarem SQLite e H2, com a troca entre eles feita por arquivo de configuração, receberão um acréscimo de 15% na nota do trabalho.- FEITO**


Observações
Não foi utilizado o padrão Commad pois dashboard não está finalizado 




Padrões de Projeto Utilizados
MVP
DAO
DTO
Adapter
Repository
Strategy
Factory
Singleton


