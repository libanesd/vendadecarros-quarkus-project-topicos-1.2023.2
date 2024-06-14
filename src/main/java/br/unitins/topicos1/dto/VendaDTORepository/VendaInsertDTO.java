package br.unitins.topicos1.dto.VendaDTORepository;

import java.util.Date;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioIdDTO;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.MovimentacaoFinanceira;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;

public record VendaInsertDTO(
    Date dataDeCompra,

    float precoDaCompra,

    String descricao,

    @Valid
    CarroIdDTO carro,

    TipoDePagamento tipoDePagamento,

    StatusVenda statusVenda,

    @Valid
    UsuarioIdDTO usuario,

    TipoDeMovimentacaoFinanceira tipoMovimentacaoFinanceira
) {
    public static VendaInsertDTO valueOf(MovimentacaoFinanceira venda){
        return new VendaInsertDTO(
            venda.getDataDeCompra(),
            venda.getPrecoDaCompra(),
            venda.getDescricao(),
            CarroIdDTO.valueOf(venda.getCarro()),
            venda.getTipoDePagamento(),
            venda.getStatusVenda(),
            UsuarioIdDTO.valueOf(venda.getUsuario()),
            venda.getTipoDeMovimentacaoFinanceira()
        );
    }
}
