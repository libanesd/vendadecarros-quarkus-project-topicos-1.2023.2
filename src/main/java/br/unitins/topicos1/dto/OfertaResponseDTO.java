package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Oferta;

public record OfertaResponseDTO(
    Long id,
    String nome,
    List<CarroDTO> carros
) {
    public static OfertaResponseDTO valueOf(Oferta oferta){
        return new OfertaResponseDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroDTO.valueOf(t)).toList()
        );
    }
}
