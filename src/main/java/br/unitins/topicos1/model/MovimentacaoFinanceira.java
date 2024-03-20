package br.unitins.topicos1.model;

import java.util.Date;

import br.unitins.topicos1.dto.VendaDTORepository.VendaUsuarioDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaIdDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "movimentacao_financeira")
public class MovimentacaoFinanceira extends DefaultEntity{

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeCompra;

    @Column(nullable = true)
    private float precoDaCompra;

    @Column(length = 200, nullable = true)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "carroId", referencedColumnName = "id")
    private Carro carro;

    @Enumerated(EnumType.STRING)
    private TipoDePagamento tipoDePagamento;

    @Enumerated(EnumType.STRING)
    private TipoDeMovimentacaoFinanceira tipoDeMovimentacaoFinanceira;

    @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    public static MovimentacaoFinanceira valueOfVendaDTO(VendaDTO venda){
        MovimentacaoFinanceira vendaCast = new MovimentacaoFinanceira();
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setTipoDeMovimentacaoFinanceira(venda.tipoMovimentacaoFinanceira());
        vendaCast.setStatusVenda(venda.statusVenda());
        vendaCast.setUsuario(venda.usuario());
        return vendaCast;
    }
    public static MovimentacaoFinanceira valueOfVendaResponseDTO(VendaResponseDTO venda){
        MovimentacaoFinanceira vendaCast = new MovimentacaoFinanceira();
        vendaCast.setId(venda.id());
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroIdDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        vendaCast.setUsuario(Usuario.valueOfUsuarioIdDTO(venda.usuario()));
        vendaCast.setTipoDeMovimentacaoFinanceira(venda.tipoMovimentacaoFinanceira());
        return vendaCast;
    }

    public static MovimentacaoFinanceira valueOfVendaUsuarioDTO(VendaUsuarioDTO venda){
        MovimentacaoFinanceira vendaCast = new MovimentacaoFinanceira();
        vendaCast.setId(venda.id());
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroIdDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        vendaCast.setTipoDeMovimentacaoFinanceira(venda.tipoMovimentacaoFinanceira());
        return vendaCast;
    }

    public static MovimentacaoFinanceira valueOfVendaInsertDTO(VendaInsertDTO venda){
        MovimentacaoFinanceira vendaCast = new MovimentacaoFinanceira();
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroIdDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        vendaCast.setUsuario(Usuario.valueOfUsuarioIdDTO(venda.usuario()));
        vendaCast.setTipoDeMovimentacaoFinanceira(venda.tipoMovimentacaoFinanceira());
        return vendaCast;
    }

    public static MovimentacaoFinanceira valueOfVendaIdDTO(VendaIdDTO venda){
        MovimentacaoFinanceira vendaCast = new MovimentacaoFinanceira();
        vendaCast.setId(venda.id());
        return vendaCast;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataDeCompra() {
        return dataDeCompra;
    }

    public void setDataDeCompra(Date dataDeCompra) {
        this.dataDeCompra = dataDeCompra;
    }

    public float getPrecoDaCompra() {
        return precoDaCompra;
    }

    public void setPrecoDaCompra(float precoDaCompra) {
        this.precoDaCompra = precoDaCompra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public TipoDePagamento getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }
    public TipoDeMovimentacaoFinanceira getTipoDeMovimentacaoFinanceira() {
        return tipoDeMovimentacaoFinanceira;
    }
    public void setTipoDeMovimentacaoFinanceira(TipoDeMovimentacaoFinanceira tipoDeMovimentacaoFinanceira) {
        this.tipoDeMovimentacaoFinanceira = tipoDeMovimentacaoFinanceira;
    }

}
