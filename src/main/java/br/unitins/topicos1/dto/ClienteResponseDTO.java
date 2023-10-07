package br.unitins.topicos1.dto;

import java.util.List;

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
            cliente.getSenha(),
            cliente.getLogin(),
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
