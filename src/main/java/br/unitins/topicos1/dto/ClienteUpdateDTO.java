package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.TipoDeUsuario;

public record ClienteUpdateDTO(
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
    public static ClienteUpdateDTO valueOf(Cliente cliente){
        return new ClienteUpdateDTO(
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
                .map(t -> OfertaIdDTO.valueOf(t)).toList(),
            cliente.getVendas()
                .stream()
                .map(t -> VendaIdDTO.valueOf(t)).toList()
        );
    }
}
