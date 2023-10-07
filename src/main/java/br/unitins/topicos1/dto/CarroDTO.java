package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;

public record CarroDTO (
    String nomeCarro,
    String carroSpec,
    String versao,
    String ano,
    String cor,
    String caracteristicas,
    String cidade,
    Float preco,
    Float kilometragem,
    MarcaCarroDTO marca,
    TipoCombustivel tipoCombustivel,
    TipoCambio tipoCambio,
    TipoCarroceria tipoCarroceria
) { 
    public static CarroDTO valueOf(Carro carro){
        return new CarroDTO(
            carro.getNomeCarro(),
            carro.getCarroSpec(),
            carro.getVersao(),
            carro.getAno(),
            carro.getCor(),
            carro.getCaracteristicas(),
            carro.getCidade(),
            carro.getPreco(),
            carro.getKilometragem(),
            MarcaCarroDTO.valueOf(carro.getMarca()),
            carro.getTipoCombustivel(),
            carro.getTipoCambio(),
            carro.getTipoCarroceria()
        );
    }
}
