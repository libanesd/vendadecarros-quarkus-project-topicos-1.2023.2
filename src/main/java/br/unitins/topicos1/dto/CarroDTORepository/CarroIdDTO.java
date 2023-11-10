package br.unitins.topicos1.dto.CarroDTORepository;

import br.unitins.topicos1.model.Carro;

public record CarroIdDTO(
    Long id
){
     public static CarroIdDTO valueOf(Carro carro){
        return new CarroIdDTO(
            carro.getId() 
        );
    }
}
