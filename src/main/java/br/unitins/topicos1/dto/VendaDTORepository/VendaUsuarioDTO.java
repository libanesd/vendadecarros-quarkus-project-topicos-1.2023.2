package br.unitins.topicos1.dto.VendaDTORepository;

import java.util.Date;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.MovimentacaoFinanceira;

public record VendaUsuarioDTO(
    Long id,
    Date dataDeCompra,
    float precoDaCompra,
    String descricao,
    CarroIdDTO carro,
    TipoDePagamento tipoDePagamento,
    StatusVenda statusVenda,
    TipoDeMovimentacaoFinanceira tipoMovimentacaoFinanceira
) {
    public static VendaUsuarioDTO valueOf(MovimentacaoFinanceira venda){
        return new VendaUsuarioDTO(
            venda.getId(),
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroIdDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getStatusVenda(),
            venda.getTipoDeMovimentacaoFinanceira()
        );
    }
}
