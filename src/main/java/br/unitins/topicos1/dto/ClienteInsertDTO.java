package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.TipoDeUsuario;

public record ClienteInsertDTO(
    String cpf,
    String nome,
    String login,
    String endereco,
    String telefone,
    String email,
    String senha,
    TipoDeUsuario tipodeusuario
) {
        public static ClienteInsertDTO valueOf(Cliente cliente){
        return new ClienteInsertDTO(
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getLogin(),
            cliente.getEndereco(),
            cliente.getTelefone(),
            cliente.getEmail(),
            cliente.getSenha(),
            cliente.getTipodeusuario()
        );
    }
}
