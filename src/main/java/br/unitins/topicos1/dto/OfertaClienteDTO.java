package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Oferta;

public record OfertaClienteDTO(
    Long id,
    String nome,
    List<CarroResponseDTO> carros,
    List<CategoriaResponseDTO> categorias
) {
    public static OfertaClienteDTO valueOf(Oferta oferta){
        return new OfertaClienteDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaResponseDTO.valueOf(t)).toList()
        );
    }
}
