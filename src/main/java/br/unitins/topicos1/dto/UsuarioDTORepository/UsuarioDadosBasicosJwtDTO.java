package br.unitins.topicos1.dto.UsuarioDTORepository;

import br.unitins.topicos1.model.Usuario;

public record UsuarioDadosBasicosJwtDTO(
    Long id,
    String login,
    String nome,
    String endereco,
    String telefone
) {
    public static UsuarioDadosBasicosJwtDTO valueOf(Usuario usuario){
        return new UsuarioDadosBasicosJwtDTO(
            usuario.getId(),
            usuario.getLogin(), 
            usuario.getNome(),
            usuario.getEndereco(),
            usuario.getTelefone()
        );
    }
}
