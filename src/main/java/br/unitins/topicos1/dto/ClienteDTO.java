package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.TipoDeUsuario;

public record ClienteDTO(
    String cpf,
    String nome,
    String login,
    String endereco,
    String telefone,
    String email,
    String senha,
    TipoDeUsuario tipodeusuario,
    List<OfertaResponseDTO> ofertas,
    List<VendaResponseDTO> vendas
) {
    public static ClienteDTO valueOf(Cliente cliente){
        return new ClienteDTO(
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getLogin(),
            cliente.getEndereco(),
            cliente.getTelefone(),
            cliente.getEmail(),
            cliente.getSenha(),
            cliente.getTipodeusuario(),
            cliente.getOfertas()
                .stream()
                .map(t -> OfertaResponseDTO.valueOf(t)).toList(),
            cliente.getVendas()
                .stream()
                .map(t -> VendaResponseDTO.valueOf(t)).toList()  
        );
    }
}
