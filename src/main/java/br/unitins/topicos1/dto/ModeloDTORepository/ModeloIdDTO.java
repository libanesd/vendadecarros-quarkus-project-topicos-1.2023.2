package br.unitins.topicos1.dto.ModeloDTORepository;

import br.unitins.topicos1.model.Carro;

public record ModeloIdDTO (
    Long id
){
   public static ModeloIdDTO valueOf(Carro carro){
        return new ModeloIdDTO(
            carro.getId() 
        );
    }
}
