package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Categoria;

public record CategoriaInsertDTO(
    String nome,
    List<CarroIdDTO> carros
) {
    public static CategoriaInsertDTO valueOf(Categoria categoria){
        return new CategoriaInsertDTO(
            categoria.getNome(),    
            categoria.getCarros()
                .stream()
                .map(t -> CarroIdDTO.valueOf(t)).toList()
        );
    }
}
