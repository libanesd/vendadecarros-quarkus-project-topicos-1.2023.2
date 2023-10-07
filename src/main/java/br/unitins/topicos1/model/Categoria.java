package br.unitins.topicos1.model;

import java.util.List;

import br.unitins.topicos1.dto.CategoriaDTO;
import br.unitins.topicos1.dto.CategoriaIdDTO;
import br.unitins.topicos1.dto.CategoriaInsertDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria extends DefaultEntity {
    @Column(length = 60)
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<Carro> carros;

    @ManyToMany
    @JoinTable(name = "categoria_oferta",
               joinColumns = @JoinColumn(name = "categoria_id"),
               inverseJoinColumns = @JoinColumn(name = "oferta_id"))
    private  List<Oferta> ofertas;

    public List<Oferta> getOfertas() {
        return ofertas;
    }
    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
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

    public static Categoria valueOfCategoriaResponseDTO(CategoriaResponseDTO categoria){
        Categoria categoriaCast = new Categoria();
        categoriaCast.setId(categoria.id());
        categoriaCast.setNome(categoria.nome());
        categoriaCast.setCarros(categoria.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        return categoriaCast;
    }
    public static Categoria valueOfCategoriaDTO(CategoriaDTO categoria){
        Categoria categoriaCast = new Categoria();
        categoriaCast.setNome(categoria.nome());
        categoriaCast.setCarros(categoria.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        return categoriaCast;
    }
    public static Categoria valueOfCategoriaInsertDTO(CategoriaInsertDTO categoria){
        Categoria categoriaCast = new Categoria();
        categoriaCast.setNome(categoria.nome());
        categoriaCast.setCarros(categoria.carros()
                .stream()
                .map(t -> Carro.valueOfCarroIdDTO(t)).toList());
        return categoriaCast;
    }
    public static Categoria valueOfCategoriaIdDTO(CategoriaIdDTO categoria){
        Categoria categoriaCast = new Categoria();
        categoriaCast.setId(categoria.id());
        return categoriaCast;
    }
}
