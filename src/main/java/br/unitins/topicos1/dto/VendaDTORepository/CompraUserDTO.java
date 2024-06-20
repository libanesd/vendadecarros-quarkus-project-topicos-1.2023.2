package br.unitins.topicos1.dto.VendaDTORepository;

import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;

public class CompraUserDTO{
    private Long carro;
    private String login;
    private TipoDePagamento tipoDePagamento;
    private TipoDeMovimentacaoFinanceira tipoMovimentacaoFinanceira;
    public Long getCarro() {
        return carro;
    }
    public void setCarro(Long carro) {
        this.carro = carro;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public TipoDePagamento getTipoDePagamento() {
        return tipoDePagamento;
    }
    public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }
    public TipoDeMovimentacaoFinanceira getTipoMovimentacaoFinanceira() {
        return tipoMovimentacaoFinanceira;
    }
    public void setTipoMovimentacaoFinanceira(TipoDeMovimentacaoFinanceira tipoMovimentacaoFinanceira) {
        this.tipoMovimentacaoFinanceira = tipoMovimentacaoFinanceira;
    }
}
