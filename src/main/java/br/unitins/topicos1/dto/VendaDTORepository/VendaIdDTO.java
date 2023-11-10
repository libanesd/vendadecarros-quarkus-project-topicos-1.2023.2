package br.unitins.topicos1.dto.VendaDTORepository;

import br.unitins.topicos1.model.Venda;

public record VendaIdDTO(
    Long id
) {
    public static VendaIdDTO valueOf(Venda venda){
        return new VendaIdDTO(
            venda.getId() 
        );
    }
}
