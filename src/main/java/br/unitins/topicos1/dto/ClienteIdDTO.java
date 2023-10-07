package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cliente;

public record ClienteIdDTO(
    Long id
) {
    public static ClienteIdDTO valueOf(Cliente cliente){
        return new ClienteIdDTO(
            cliente.getId() 
        );
    }
}
