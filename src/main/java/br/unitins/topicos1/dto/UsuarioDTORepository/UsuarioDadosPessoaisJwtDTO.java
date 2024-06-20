package br.unitins.topicos1.dto.UsuarioDTORepository;

import br.unitins.topicos1.model.Usuario;

public record UsuarioDadosPessoaisJwtDTO (
    Long id,
    String login,
    String email,
    String cpf,
    String senha
){
    public static UsuarioDadosPessoaisJwtDTO valueOf(Usuario usuario){
        return new UsuarioDadosPessoaisJwtDTO(
            usuario.getId(),
            usuario.getLogin(), 
            usuario.getEmail(),
            usuario.getCpf(),
            usuario.getSenha()
        );
    }
}