package br.unitins.topicos1.dto.VendaDTORepository;

import java.util.Date;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.Venda;

public record VendaUsuarioDTO(
    Long id,
    Date dataDeCompra,
    float precoDaCompra,
    String descricao,
    CarroIdDTO carro,
    TipoDePagamento tipoDePagamento,
    StatusVenda statusVenda
) {
    public static VendaUsuarioDTO valueOf(Venda venda){
        return new VendaUsuarioDTO(
            venda.getId(),
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroIdDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getStatusVenda()
        );
    }
}
