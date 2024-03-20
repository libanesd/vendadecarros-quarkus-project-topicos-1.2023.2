package br.unitins.topicos1.dto.VendaDTORepository;

import java.util.Date;

import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.MovimentacaoFinanceira;

import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;

public record VendaDTO(
    Date dataDeCompra,
    float precoDaCompra,
    String descricao,
    CarroDTO carro,
    TipoDePagamento tipoDePagamento,
    TipoDeMovimentacaoFinanceira tipoMovimentacaoFinanceira,
    StatusVenda statusVenda,
    Usuario usuario
) {
    public static VendaDTO valueOf(MovimentacaoFinanceira venda){
        return new VendaDTO(
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getTipoDeMovimentacaoFinanceira(),
            venda.getStatusVenda(),
            venda.getUsuario()
        );
    }
}
