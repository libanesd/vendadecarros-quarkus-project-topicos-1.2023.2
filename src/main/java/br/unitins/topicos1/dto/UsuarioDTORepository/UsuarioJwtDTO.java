package br.unitins.topicos1.dto.UsuarioDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.TipoDeUsuario;

public record UsuarioJwtDTO (
    Long id,
    String nome,
    String login,
    String endereco,
    String telefone,
    TipoDeUsuario tipodeusuario,
    List<OfertaIdDTO> ofertas
)
{
    public static UsuarioJwtDTO valueOf(Usuario usuario){
        return new UsuarioJwtDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getEndereco(),
            usuario.getTelefone(),
            usuario.getTipodeusuario(),
            usuario.getOfertas()
                .stream()
                .map(t -> OfertaIdDTO.valueOf(t)).toList()
        );
    }
}
