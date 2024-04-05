package br.unitins.topicos1.model;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaUsuarioDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaInsertDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "oferta")
public class Oferta extends DefaultEntity{

    @Column(length = 60)
    private String nome;

    @ManyToMany(mappedBy = "ofertas",cascade = CascadeType.ALL)
    private List<Carro> carros;

    @ManyToMany(mappedBy = "ofertas",cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "ofertas",cascade = CascadeType.ALL)
    private List<Categoria> categorias;
    
    @Enumerated(EnumType.STRING)
    private StatusOferta statusOferta;

    @Column(nullable = true)
    private Float porcentagemDeDesconto;

    public void copiarCampos(Oferta outraOferta) {
        this.nome = outraOferta.getNome();
        this.carros = new ArrayList<>(outraOferta.getCarros());
        this.usuarios = new ArrayList<>(outraOferta.getUsuarios());
        this.categorias = new ArrayList<>(outraOferta.getCategorias());
        this.statusOferta = outraOferta.getStatusOferta();
        this.porcentagemDeDesconto = outraOferta.getPorcentagemDeDesconto();

    }

    public static Oferta valueOfOfertaDTO(OfertaDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        ofertaCast.setUsuarios(oferta.usuarios()
                .stream()
                .map(t -> Usuario.valueOfUsuarioResponseDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaResponseDTO(t)).toList());
        ofertaCast.setStatusOferta(oferta.statusOferta());
        ofertaCast.setPorcentagemDeDesconto(oferta.porcentagemDeDesconto());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaResponseDTO(OfertaResponseDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setId(oferta.id());
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        ofertaCast.setUsuarios(oferta.usuarios()
                .stream()
                .map(t -> Usuario.valueOfUsuarioInsertDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaResponseDTO(t)).toList());
        ofertaCast.setStatusOferta(oferta.statusOferta());
        ofertaCast.setPorcentagemDeDesconto(oferta.porcentagemDeDesconto());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaUsuarioDTO(OfertaUsuarioDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setId(oferta.id());
        ofertaCast.setNome(oferta.nome());
        ofertaCast.setCarros(oferta.carros()
                .stream()
                .map(t -> Carro.valueOfCarroIdDTO(t)).toList());
        ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaIdDTO(t)).toList());
        ofertaCast.setStatusOferta(oferta.statusOferta());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaInsertDTO(OfertaInsertDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setNome(oferta.nome());
        if(oferta.carros() != null){
            ofertaCast.setCarros(oferta.carros()
            .stream()
            .map(t -> Carro.valueOfCarroIdDTO(t)).toList());
        }
        if(oferta.categorias() != null){
            ofertaCast.setCategorias(oferta.categorias()
                .stream()
                .map(t -> Categoria.valueOfCategoriaIdDTO(t)).toList());
        }
        if(oferta.usuarios() != null){
            ofertaCast.setUsuarios(oferta.usuarios()
            .stream()
            .map(t -> Usuario.valueOfUsuarioIdDTO(t)).toList());
        }
        
        ofertaCast.setStatusOferta(oferta.statusOferta());
        ofertaCast.setPorcentagemDeDesconto(oferta.porcentagemDeDesconto());
        return ofertaCast;
    }

    public static Oferta valueOfOfertaIdDTO(OfertaIdDTO oferta){
        Oferta ofertaCast = new Oferta();
        ofertaCast.setId(oferta.id());
        return ofertaCast;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
    public StatusOferta getStatusOferta() {
        return statusOferta;
    }

    public void setStatusOferta(StatusOferta statusOferta) {
        this.statusOferta = statusOferta;
    }
    public Float getPorcentagemDeDesconto() {
        return porcentagemDeDesconto;
    }

    public void setPorcentagemDeDesconto(Float porcentagemDeDesconto) {
        this.porcentagemDeDesconto = porcentagemDeDesconto;
    }
}
