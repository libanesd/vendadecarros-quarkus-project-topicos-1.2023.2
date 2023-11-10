package br.unitins.topicos1.dto.CategoriaDTORepository;

import br.unitins.topicos1.model.Categoria;

public record CategoriaIdDTO(
    Long id
) {
    public static CategoriaIdDTO valueOf(Categoria categoria){
        return new CategoriaIdDTO(
            categoria.getId()
        );
    }
}
