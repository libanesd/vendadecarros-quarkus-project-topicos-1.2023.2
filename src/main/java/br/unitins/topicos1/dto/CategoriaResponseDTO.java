package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Categoria;

public record CategoriaResponseDTO(
    Long id,
    String nome,
    List<CarroResponseDTO> carros
) {
    public static CategoriaResponseDTO valueOf(Categoria categoria){
        return new CategoriaResponseDTO(
            categoria.getId(), 
            categoria.getNome(),
            categoria.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList()
        );
    }
}
