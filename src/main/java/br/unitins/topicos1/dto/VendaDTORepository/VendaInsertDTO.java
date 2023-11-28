package br.unitins.topicos1.dto.VendaDTORepository;

import java.util.Date;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.ClienteDTORepository.ClienteIdDTO;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.Venda;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VendaInsertDTO(
    @PastOrPresent(message = "A data de compra deve ser no passado ou no presente")
    Date dataDeCompra,

    @Positive(message = "O preço da compra deve ser um número positivo")
    float precoDaCompra,

    @NotBlank(message = "A descrição não pode estar em branco")
    String descricao,

    @NotNull(message = "O DTO do carro não pode ser nulo")
    @Valid
    CarroIdDTO carro,

    @NotNull(message = "O tipo de pagamento não pode ser nulo")
    TipoDePagamento tipoDePagamento,

    @NotNull(message = "O status da venda não pode ser nulo")
    StatusVenda statusVenda,

    @NotNull(message = "O DTO do cliente não pode ser nulo")
    @Valid
    ClienteIdDTO cliente
) {
    public static VendaInsertDTO valueOf(Venda venda){
        return new VendaInsertDTO(
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroIdDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getStatusVenda(),
            ClienteIdDTO.valueOf(venda.getCliente())
        );
    }
}
