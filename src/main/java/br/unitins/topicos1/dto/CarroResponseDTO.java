package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;

public record CarroResponseDTO (
    Long id,
    String nomeCarro,
    String carroSpec,
    String versao,
    String ano,
    String cor,
    String caracteristicas,
    String cidade,
    Float preco,
    Float kilometragem,
    TipoCombustivel tipoCombustivel,
    TipoCambio tipoCambio,
    TipoCarroceria tipoCarroceria
) { 
    public static CarroResponseDTO valueOf(Carro carro){
        return new CarroResponseDTO(
            carro.getId(), 
            carro.getNomeCarro(),
            carro.getCarroSpec(),
            carro.getVersao(),
            carro.getAno(),
            carro.getCor(),
            carro.getCaracteristicas(),
            carro.getCidade(),
            carro.getPreco(),
            carro.getKilometragem(),
            carro.getTipoCombustivel(),
            carro.getTipoCambio(),
            carro.getTipoCarroceria()
        );
    }
}
