package br.unitins.topicos1.dto.ClienteDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.TipoDeUsuario;

public record ClienteJwtDTO (
    Long id,
    String nome,
    String login,
    TipoDeUsuario tipodeusuario,
    List<OfertaIdDTO> ofertas
)
{
    public static ClienteJwtDTO valueOf(Cliente cliente){
        return new ClienteJwtDTO(
            cliente.getId(), 
            cliente.getNome(),
            cliente.getLogin(),
            cliente.getTipodeusuario(),
            cliente.getOfertas()
                .stream()
                .map(t -> OfertaIdDTO.valueOf(t)).toList()
        );
    }
}
