package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Oferta;

public record OfertaDTO(
    String nome,
    List<CarroResponseDTO> carros,
    List<CategoriaResponseDTO> categorias,
    List<ClienteResponseDTO> clientes
)
{
    public static OfertaDTO valueOf(Oferta oferta){
        return new OfertaDTO(
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaResponseDTO.valueOf(t)).toList(),
            oferta.getClientes()
                .stream()
                .map(t -> ClienteResponseDTO.valueOf(t)).toList()
        );
    }

}