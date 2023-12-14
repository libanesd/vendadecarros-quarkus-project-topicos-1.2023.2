package br.unitins.topicos1.dto.VendaDTORepository;

import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.Venda;

public record CompraUserDTO(
    Long carro,
    TipoDePagamento tipoDePagamento
)
{
    public static CompraUserDTO valueOf(Venda venda){
        return new CompraUserDTO(
            venda.getCarro().getId(),
            venda.getTipoDePagamento()
        );
    }
}
