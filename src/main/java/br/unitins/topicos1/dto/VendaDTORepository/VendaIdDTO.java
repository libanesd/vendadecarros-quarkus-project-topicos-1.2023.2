package br.unitins.topicos1.dto.VendaDTORepository;

import br.unitins.topicos1.model.MovimentacaoFinanceira;

public record VendaIdDTO(
    Long id
) {
    public static VendaIdDTO valueOf(MovimentacaoFinanceira venda){
        return new VendaIdDTO(
            venda.getId() 
        );
    }
}
