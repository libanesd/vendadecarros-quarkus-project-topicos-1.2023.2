package br.unitins.topicos1.dto.VendaDTORepository;

import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.MovimentacaoFinanceira;
import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;

public record CompraUserDTO(
    Long carro,
    TipoDePagamento tipoDePagamento,
    TipoDeMovimentacaoFinanceira tipoMovimentacaoFinanceira
)
{
    public static CompraUserDTO valueOf(MovimentacaoFinanceira venda){
        return new CompraUserDTO(
            venda.getCarro().getId(),
            venda.getTipoDePagamento(),
            venda.getTipoDeMovimentacaoFinanceira()
        );
    }
}
