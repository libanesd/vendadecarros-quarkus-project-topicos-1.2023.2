package br.unitins.topicos1.dto.OfertaDTORepository;

import br.unitins.topicos1.model.Oferta;

public record OfertaIdDTO(
    Long id
) {
    public static OfertaIdDTO valueOf(Oferta oferta){
        return new OfertaIdDTO(
            oferta.getId() 
        );
    }
}
