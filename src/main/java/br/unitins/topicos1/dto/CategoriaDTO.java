package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Categoria;

public record CategoriaDTO(
    String nome,
    List<CarroResponseDTO> carros
) {
     public static CategoriaDTO valueOf(Categoria categoria){
        return new CategoriaDTO(
            categoria.getNome(),    
            categoria.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList()
        );
    }
}
