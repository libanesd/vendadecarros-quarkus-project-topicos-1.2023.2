package br.unitins.topicos1.dto.UsuarioDTORepository;

import br.unitins.topicos1.model.TipoDeUsuario;
import br.unitins.topicos1.model.Usuario;

public record UsuarioDTO (
    String nome,
    String login,
    String senha,
    TipoDeUsuario tipodeusuario
) {
    public static UsuarioDTO valueOf(Usuario usuario){
        return new UsuarioDTO(
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getSenha(),
            usuario.getTipodeusuario()
        );
    }

}
