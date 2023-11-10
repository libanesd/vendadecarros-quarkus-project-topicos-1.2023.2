package br.unitins.topicos1.model;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaClienteDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaInsertDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "oferta")
public class Oferta extends DefaultEntity{

    @Column(length = 60)
    private String nome;

    @ManyToMany(mappedBy = "ofertas")
    private List<Carro> carros;

    @ManyToMany(mappedBy = "ofertas")
    private List<Cliente> clientes;

    @ManyToMany(mappedBy = "ofertas")
    private List<Categoria> categorias;
    //variavel de desconto 

    public void copiarCampos(Oferta outraOferta) {
        this.nome = outraOferta.getNome();
        this.carros = new ArrayList<>(outraOferta.getCarros());
        this.clientes = new ArrayList<>(outraOferta.getClientes());
        this.categorias = new ArrayList<>(outraOferta.getCategorias());
    }

    public static Oferta valueOfOfertaDTO(OfertaDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        ofertaCast.setClientes(oferta.clientes()
                .stream()
                .map(t -> Cliente.valueOfClienteResponseDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaResponseDTO(t)).toList());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaResponseDTO(OfertaResponseDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setId(oferta.id());
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        ofertaCast.setClientes(oferta.clientes()
                .stream()
                .map(t -> Cliente.valueOfClienteInsertDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaResponseDTO(t)).toList());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaClienteDTO(OfertaClienteDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setId(oferta.id());
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroIdDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaIdDTO(t)).toList());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaInsertDTO(OfertaInsertDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroIdDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaIdDTO(t)).toList());
        ofertaCast.setClientes(oferta.clientes()
                .stream()
                .map(t -> Cliente.valueOfClienteIdDTO(t)).toList());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaIdDTO(OfertaIdDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setId(oferta.id());
        return ofertaCast;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Carro> getCarros() {
        return carros;
    }
    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
}
