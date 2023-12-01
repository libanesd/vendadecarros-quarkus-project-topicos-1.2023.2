package br.unitins.topicos1.dto.UsuarioDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaUsuarioDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaUsuarioDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.TipoDeUsuario;

public record UsuarioDTO(
    String cpf,
    String nome,
    String login,
    String endereco,
    String telefone,
    String email,
    String senha,
    TipoDeUsuario tipodeusuario,
    List<OfertaUsuarioDTO> ofertas,
    List<VendaUsuarioDTO> vendas
) {
    public static UsuarioDTO valueOf(Usuario usuario){
        return new UsuarioDTO(
            usuario.getCpf(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getEndereco(),
            usuario.getTelefone(),
            usuario.getEmail(),
            usuario.getSenha(),
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
