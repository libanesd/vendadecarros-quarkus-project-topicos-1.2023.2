package br.unitins.topicos1.dto.UsuarioDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaIdDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.TipoDeUsuario;

public record UsuarioUpdateDTO(
    String cpf,
    String nome,
    String login,
    String senha,
    String endereco,
    String telefone,
    String email,
    TipoDeUsuario tipodeusuario,
    List<OfertaIdDTO> ofertas,
    List<VendaIdDTO> vendas
) {
    public static UsuarioUpdateDTO valueOf(Usuario usuario){
        return new UsuarioUpdateDTO(
            usuario.getCpf(),
            usuario.getNome(),
            usuario.getSenha(),
            usuario.getLogin(),
            usuario.getEndereco(),
            usuario.getTelefone(),
            usuario.getEmail(),
            usuario.getTipodeusuario(),
            usuario.getOfertas()
                .stream()
                .map(t -> OfertaIdDTO.valueOf(t)).toList(),
            usuario.getVendas()
                .stream()
                .map(t -> VendaIdDTO.valueOf(t)).toList()
        );
    }
}
