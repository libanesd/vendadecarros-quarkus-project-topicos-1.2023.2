package br.unitins.topicos1.model;

import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteIdDTO;
import br.unitins.topicos1.dto.ClienteInsertDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario{

    @Column(length = 12, nullable = true)
    private String cpf;
    @Column(length = 200, nullable = true)
    private String endereco;
    @Column(length = 200, nullable = true)
    private String telefone;
    @Column(length = 200, nullable = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "cliente_oferta",
               joinColumns = @JoinColumn(name = "cliente_id"),
               inverseJoinColumns = @JoinColumn(name = "oferta_id"))
    private  List<Oferta> ofertas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public static Cliente valueOfClienteDTO(ClienteDTO cliente){
        Cliente clienteCast = new Cliente();
        clienteCast.setTipodeusuario(cliente.tipodeusuario());
        clienteCast.setEndereco(cliente.endereco());
        clienteCast.setCpf(cliente.cpf());
        clienteCast.setTelefone(cliente.telefone());
        clienteCast.setEmail(cliente.email());
        clienteCast.setVendas(cliente.vendas()
                .stream()
                .map(t -> Venda.valueOfVendaClienteDTO(t)).toList());
        clienteCast.setOfertas(cliente.ofertas()
                .stream()
                .map(t -> Oferta.valueOfOfertaClienteDTO(t)).toList());
        return clienteCast;
    }

    public static Cliente valueOfClienteResponseDTO(ClienteResponseDTO cliente){
        Cliente clienteCast = new Cliente();
        clienteCast.setId(cliente.id());
        clienteCast.setTipodeusuario(cliente.tipodeusuario());
        clienteCast.setEndereco(cliente.endereco());
        clienteCast.setCpf(cliente.cpf());
        clienteCast.setTelefone(cliente.telefone());
        clienteCast.setEmail(cliente.email());
        clienteCast.setVendas(cliente.vendas()
                .stream()
                .map(t -> Venda.valueOfVendaClienteDTO(t)).toList());
        clienteCast.setOfertas(cliente.ofertas()
                .stream()
                .map(t -> Oferta.valueOfOfertaClienteDTO(t)).toList());
        return clienteCast;
    }

    public static Cliente valueOfClienteInsertDTO(ClienteInsertDTO cliente){
        Cliente clienteCast = new Cliente();
        clienteCast.setTipodeusuario(cliente.tipodeusuario());
        clienteCast.setEndereco(cliente.endereco());
        clienteCast.setNome(cliente.nome());
        clienteCast.setCpf(cliente.cpf());
        clienteCast.setTelefone(cliente.telefone());
        clienteCast.setEmail(cliente.email());
        clienteCast.setSenha(cliente.senha());
        clienteCast.setLogin(cliente.login());
        return clienteCast;
    }
    
    public static Cliente valueOfClienteIdDTO(ClienteIdDTO cliente){
        Cliente clienteCast = new Cliente();
        clienteCast.setId(cliente.id());
        return clienteCast;
    }
    
    public List<Venda> getVendas() {
        return vendas;
    }
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    public List<Oferta> getOfertas() {
        return ofertas;
    }
    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
