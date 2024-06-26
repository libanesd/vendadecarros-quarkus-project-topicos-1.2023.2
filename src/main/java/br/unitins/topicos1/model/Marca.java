package br.unitins.topicos1.model;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTORepository.MarcaCarroDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaInsertDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca extends DefaultEntity{
    @Column(length = 60)
    private String nomeMarca;
    @OneToOne(mappedBy = "marca", cascade = CascadeType.ALL)
    private Modelo modelo;

    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<Carro> carros;


    public static Marca valueOfMarcaCarroDTO(MarcaCarroDTO marca){
        Marca marcaCast = new Marca();
        marcaCast.setId(marca.id());
        return marcaCast;
    }

    public static Marca valueOfMarcaDTO(MarcaDTO marca){
        Marca marcaCast = new Marca();
        marcaCast.setNomeMarca(marca.nome());
        marcaCast.setCarros(marca.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        return marcaCast;
    }

    public static Marca valueOfMarcaResponseDTO(MarcaResponseDTO marca){
        Marca marcaCast = new Marca();
        marcaCast.setId(marca.id());
        marcaCast.setNomeMarca(marca.nome());
        marcaCast.setCarros(marca.carros()
                .stream()
                .map(t -> Carro.valueOfCarroResponseDTO(t)).toList());
        return marcaCast;
    }

    public static Marca valueOfMarcaInsertDTO(MarcaInsertDTO marca){
        Marca marcaCast = new Marca();
        marcaCast.setNomeMarca(marca.nome());
        if(marca.carros() != null && !marca.carros().isEmpty()){
            marcaCast.setCarros(marca.carros()
            .stream()
            .map(t -> Carro.valueOfCarroIdDTO(t)).toList());
        }
        return marcaCast;
    }

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
    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
