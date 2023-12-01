package br.unitins.topicos1.dto.UsuarioDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaUsuarioDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaUsuarioDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.TipoDeUsuario;

public record UsuarioResponseDTO (
    Long id,
    String cpf,
    String nome,
    String login,
    String senha,
    String endereco,
    String telefone,
    String email,
    TipoDeUsuario tipodeusuario,
    List<OfertaUsuarioDTO> ofertas,
    List<VendaUsuarioDTO> vendas
)
{
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getCpf(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getSenha(),
            usuario.getEndereco(),
            usuario.getTelefone(),
            usuario.getEmail(),
            usuario.getTipodeusuario(),
            usuario.getOfertas()
                .stream()
                .map(t -> OfertaUsuarioDTO.valueOf(t)).toList(),
            usuario.getVendas()
                .stream()
                .map(t -> VendaUsuarioDTO.valueOf(t)).toList()  
        );
    }
}
