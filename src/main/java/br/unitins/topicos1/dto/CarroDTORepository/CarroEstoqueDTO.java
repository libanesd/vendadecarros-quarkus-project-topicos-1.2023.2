package br.unitins.topicos1.dto.CarroDTORepository;

import br.unitins.topicos1.dto.MarcaDTORepository.MarcaCarroDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;

public record CarroEstoqueDTO(
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
    MarcaCarroDTO marca,
    TipoCombustivel tipoCombustivel,
    TipoCambio tipoCambio,
    TipoCarroceria tipoCarroceria,
    Integer estoque
) {
    public static CarroEstoqueDTO valueOf(Carro carro){
        return new CarroEstoqueDTO(
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
            MarcaCarroDTO.valueOf(carro.getMarca()),
            carro.getTipoCombustivel(),
            carro.getTipoCambio(),
            carro.getTipoCarroceria(),
            carro.getEstoqueado()
        );
    }
}
