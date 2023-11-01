package br.unitins.topicos1.dto;

import java.util.Date;

import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.Venda;

public record VendaResponseDTO(
    Long id,
    Date dataDeCompra,
    float precoDaCompra,
    String descricao,
    CarroDTO carro,
    TipoDePagamento tipoDePagamento,
    StatusVenda statusVenda,
    ClienteIdDTO cliente
) {
    public static VendaResponseDTO valueOf(Venda venda){
        return new VendaResponseDTO(
            venda.getId(),
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getStatusVenda(),
            ClienteIdDTO.valueOf(venda.getCliente())
        );
    }
}
