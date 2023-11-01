package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Oferta;

public record OfertaResponseDTO(
    Long id,
    String nome,
    List<CarroResponseDTO> carros,
    List<CategoriaResponseDTO> categorias,
    List<ClienteInsertDTO> clientes
) {
    public static OfertaResponseDTO valueOf(Oferta oferta){
        return new OfertaResponseDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaResponseDTO.valueOf(t)).toList(),
            oferta.getClientes()
                .stream()   
                .map(t -> ClienteInsertDTO.valueOf(t)).toList()
        );
    }
}
