package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Oferta;

public record MarcaResponseDTO(
    Long id,
    String nome,
    List<CarroDTO> carros
) {
    public static MarcaResponseDTO valueOf(Oferta oferta){
        return new MarcaResponseDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroDTO.valueOf(t)).toList()
        );
    }
}
