package br.unitins.topicos1.dto.UsuarioDTORepository;

import br.unitins.topicos1.model.Usuario;

public record UsuarioIdDTO(
    Long id
) {
    public static UsuarioIdDTO valueOf(Usuario usuario){
        return new UsuarioIdDTO(
            usuario.getId() 
        );
    }
}
