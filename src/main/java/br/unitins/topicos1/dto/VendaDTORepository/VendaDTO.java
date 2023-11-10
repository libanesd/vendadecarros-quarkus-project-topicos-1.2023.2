package br.unitins.topicos1.dto.VendaDTORepository;

import java.util.Date;

import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.Venda;

public record VendaDTO(
    Date dataDeCompra,
    float precoDaCompra,
    String descricao,
    CarroDTO carro,
    TipoDePagamento tipoDePagamento,
    StatusVenda statusVenda,
    Cliente cliente
) {
    public static VendaDTO valueOf(Venda venda){
        return new VendaDTO(
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getStatusVenda(),
            venda.getCliente()
        );
    }
}
