package br.unitins.topicos1.dto.MarcaDTORepository;

import br.unitins.topicos1.model.Marca;

public record MarcaCarroDTO (
    Long id
) {
    public static MarcaCarroDTO valueOf(Marca marca){
        return new MarcaCarroDTO(
            marca.getId()
        );
    }
    
}
