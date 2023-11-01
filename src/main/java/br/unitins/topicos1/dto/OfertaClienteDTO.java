package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Oferta;

public record OfertaClienteDTO(
    Long id,
    String nome,
    List<CarroIdDTO> carros,
    List<CategoriaIdDTO> categorias
) {
    public static OfertaClienteDTO valueOf(Oferta oferta){
        return new OfertaClienteDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroIdDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaIdDTO.valueOf(t)).toList()
        );
    }
}
