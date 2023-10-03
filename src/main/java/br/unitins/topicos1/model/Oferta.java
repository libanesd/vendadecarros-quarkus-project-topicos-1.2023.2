package br.unitins.topicos1.model;

import java.util.List;

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
    //variavel de desconto 

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
