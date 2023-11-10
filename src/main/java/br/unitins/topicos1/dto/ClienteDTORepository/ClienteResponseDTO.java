package br.unitins.topicos1.dto.ClienteDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaClienteDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaClienteDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.TipoDeUsuario;

public record ClienteResponseDTO (
    Long id,
    String cpf,
    String nome,
    String login,
    String senha,
    String endereco,
    String telefone,
    String email,
    TipoDeUsuario tipodeusuario,
    List<OfertaClienteDTO> ofertas,
    List<VendaClienteDTO> vendas
)
{
    public static ClienteResponseDTO valueOf(Cliente cliente){
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getLogin(),
            cliente.getSenha(),
            cliente.getEndereco(),
            cliente.getTelefone(),
            cliente.getEmail(),
            cliente.getTipodeusuario(),
            cliente.getOfertas()
                .stream()
                .map(t -> OfertaClienteDTO.valueOf(t)).toList(),
            cliente.getVendas()
                .stream()
                .map(t -> VendaClienteDTO.valueOf(t)).toList()  
        );
    }
}
