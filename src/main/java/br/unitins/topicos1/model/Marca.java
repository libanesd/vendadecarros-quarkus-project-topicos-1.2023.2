package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca extends DefaultEntity{
    @Column(length = 60)
    private String nomeMarca;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<Carro> carros;

    Marca(){}
    public String getNomeMarca() {
        return nomeMarca;
    }
    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
    public List<Carro> getCarros() {
        return carros;
    }
    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
    
}
