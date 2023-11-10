package br.unitins.topicos1.model;

import java.util.Date;

import br.unitins.topicos1.dto.VendaDTORepository.VendaClienteDTO;
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
@Table(name = "venda")
public class Venda extends DefaultEntity{

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
    private StatusVenda statusVenda;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    public static Venda valueOfVendaDTO(VendaDTO venda){
        Venda vendaCast = new Venda();
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        vendaCast.setCliente(venda.cliente());
        return vendaCast;
    }
    public static Venda valueOfVendaResponseDTO(VendaResponseDTO venda){
        Venda vendaCast = new Venda();
        vendaCast.setId(venda.id());
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroIdDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        vendaCast.setCliente(Cliente.valueOfClienteIdDTO(venda.cliente()));
        return vendaCast;
    }

    public static Venda valueOfVendaClienteDTO(VendaClienteDTO venda){
        Venda vendaCast = new Venda();
        vendaCast.setId(venda.id());
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setCarro(Carro.valueOfCarroIdDTO(venda.carro()));
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        return vendaCast;
    }

    public static Venda valueOfVendaInsertDTO(VendaInsertDTO venda){
        Venda vendaCast = new Venda();
        vendaCast.setDataDeCompra(venda.dataDeCompra());
        vendaCast.setPrecoDaCompra(venda.precoDaCompra());
        vendaCast.setDescricao(venda.descricao());
        vendaCast.setTipoDePagamento(venda.tipoDePagamento());
        vendaCast.setStatusVenda(venda.statusVenda());
        return vendaCast;
    }

    public static Venda valueOfVendaIdDTO(VendaIdDTO venda){
        Venda vendaCast = new Venda();
        vendaCast.setId(venda.id());
        return vendaCast;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

}
