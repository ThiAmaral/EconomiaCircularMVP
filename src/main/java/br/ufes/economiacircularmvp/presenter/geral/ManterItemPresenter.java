package br.ufes.economiacircularmvp.presenter;

import br.ufes.economiacircularmvp.dto.ItemDTO;
import br.ufes.economiacircularmvp.dto.UsuarioDTO;
import br.ufes.economiacircularmvp.repository.IItemRepository;
import br.ufes.economiacircularmvp.view.IManterItemView;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class ManterItemPresenter {
    private IManterItemView view;
    private UsuarioDTO usuarioLogado;
    private IItemRepository itemRepository;

    public ManterItemPresenter(IManterItemView view, UsuarioDTO usuarioLogado, IItemRepository itemRepository) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
        this.itemRepository = itemRepository;

        view.adicionarAcaoSalvar(e -> salvarItem());
        view.adicionarAcaoCancelar(e -> view.fechar());
        // Ação de excluir seria mais complexa, envolvendo confirmação e estado
    }

    private void salvarItem() {
        try {
            // 1. Coletar dados da View
            String idCircular = view.getIdcField().getText();
            if (idCircular.isBlank()) {
                // Lógica para gerar um novo ID-C (ex: UUID ou um gerador customizado)
                idCircular = java.util.UUID.randomUUID().toString().substring(0, 12).toUpperCase();
            }
            
            String tipoPeca = (String) view.getTipoPecaCombo().getSelectedItem();
            String subcategoria = view.getSubcategoriaField().getText();
            String tamanho = view.getTamanhoField().getText();
            String cor = view.getCorField().getText();
            String composicao = view.getComposicaoField().getText();
            double massa = Double.parseDouble(view.getMassaField().getText());
            String estado = (String) view.getEstadoConservacaoCombo().getSelectedItem();
            double precoBase = Double.parseDouble(view.getPrecoBaseField().getText());

            String defeitos = view.getDefeitosCheckBoxes().stream()
                                  .filter(JCheckBox::isSelected)
                                  .map(JCheckBox::getText)
                                  .collect(Collectors.joining(","));

            // 2. Criar o DTO
            ItemDTO item = new ItemDTO(
                idCircular,
                usuarioLogado.getId(),
                tipoPeca,
                subcategoria,
                tamanho,
                cor,
                composicao,
                massa,
                estado,
                defeitos,
                precoBase
            );

            // 3. (FUTURO) Chamar um serviço para calcular preço final e indicadores
            // Por enquanto, valores placeholder:
            item.setPrecoFinal(precoBase * 0.8); // Simula desconto
            item.setGwpAvoided(massa * 5.0); // Simula cálculo
            item.setMciItem(0.9); // Simula cálculo

            // 4. Salvar no repositório
            itemRepository.inserir(item);
            
            view.exibirMensagem("Item salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            view.fechar();

        } catch (NumberFormatException nfe) {
            view.exibirMensagem("Erro de formato: Massa e Preço Base devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sqle) {
            view.exibirMensagem("Erro ao salvar no banco de dados: " + sqle.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            view.exibirMensagem("Ocorreu um erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}