package br.unitins.topicos1.dto.CarroDTORepository;

import br.unitins.topicos1.dto.MarcaDTORepository.MarcaCarroDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CarroDTO (
    @NotBlank(message = "O campo Nome não pode ser nulo.")
    String nomeCarro,
    @NotBlank(message = "O campo Spec não pode ser nulo.")
    String carroSpec,
    @NotBlank(message = "O campo Versão não pode ser nulo.")
    String versao,
    @NotBlank(message = "O campo Ano não pode ser nulo.")
    String ano,
    @NotBlank(message = "O campo Cor não pode ser nulo.")
    String cor,
    @NotBlank(message = "O campo Caracteristicas não pode ser nulo.")
    String caracteristicas,
    @NotBlank(message = "O campo Cidade não pode ser nulo.")
    String cidade,
    @Positive(message = "O valor deve ser positivo")
    Float preco,
    @Positive(message = "O valor deve ser positivo")
    Float kilometragem,
    MarcaCarroDTO marca,
    TipoCombustivel tipoCombustivel,
    TipoCambio tipoCambio,
    TipoCarroceria tipoCarroceria,
    Integer estoque
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
            carro.getTipoCarroceria(),
            carro.getEstoqueado()
        );
    }
}
