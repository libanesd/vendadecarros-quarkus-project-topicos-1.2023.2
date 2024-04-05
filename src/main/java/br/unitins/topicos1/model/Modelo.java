package br.unitins.topicos1.model;

import br.unitins.topicos1.dto.ModeloDTORepository.ModeloCarroDTO;
import br.unitins.topicos1.dto.ModeloDTORepository.ModeloIdDTO;
import br.unitins.topicos1.dto.ModeloDTORepository.ModeloInsertDTO;
import br.unitins.topicos1.dto.ModeloDTORepository.ModeloMarcaDTO;
import br.unitins.topicos1.dto.ModeloDTORepository.ModeloResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "modelo")
public class Modelo extends DefaultEntity{
    
    @Column(length = 400, nullable = true)
    private String especificacaoDoModelo;
    @Column(length = 200, nullable = true)
    private String descricao;
    @Column(length = 200, nullable = true)
    private String nomeModelo;
    @Column(length = 200, nullable = true)
    private String motorizacao;
    @Column(length = 200, nullable = true)
    private String numeroDePortas;
    @Column(length = 200, nullable = true)
    private String capacidadeDePassageiros;
    @Column(length = 200, nullable = true)
    private float precoBase;
    @Column(length = 200, nullable = true)
    private float opcoesDePacote;

    
    @OneToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;
    @OneToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    
    
    public static Modelo valueOfModeloInsertDTO(@Valid ModeloInsertDTO dto) {
        Modelo modeloCast = new Modelo();
        modeloCast.setEspecificacaoDoModelo(dto.especificacaoDoModelo());
        modeloCast.setDescricao(dto.descricao());
        modeloCast.setNomeModelo(dto.nomeModelo());
        modeloCast.setMotorizacao(dto.motorizacao());
        modeloCast.setNumeroDePortas(dto.numeroDePortas());
        modeloCast.setCapacidadeDePassageiros(dto.capacidadeDePassageiros());
        if(dto.carro() != null){
            modeloCast.setCarro(Carro.valueOfCarroIdDTO(dto.carro()));
        }
        if (dto.marca() != null){
            modeloCast.setMarca(Marca.valueOfMarcaCarroDTO(dto.marca()));
        }
        return modeloCast;
    }

    public static Modelo valueOfModeloResponseDTO(@Valid ModeloResponseDTO dto) {
        Modelo modeloCast = new Modelo();
        modeloCast.setId(dto.id());
        modeloCast.setEspecificacaoDoModelo(dto.especificacaoDoModelo());
        modeloCast.setDescricao(dto.descricao());
        modeloCast.setNomeModelo(dto.nomeModelo());
        modeloCast.setMotorizacao(dto.motorizacao());
        modeloCast.setNumeroDePortas(dto.numeroDePortas());
        modeloCast.setCapacidadeDePassageiros(dto.capacidadeDePassageiros());
        if(dto.carro() != null){
            modeloCast.setCarro(Carro.valueOfCarroIdDTO(dto.carro()));
        }
        if (dto.marca() != null){
            modeloCast.setMarca(Marca.valueOfMarcaCarroDTO(dto.marca()));
        }
        return modeloCast;
    }

    public static Modelo valueOfModeloCarroDTO(@Valid ModeloCarroDTO dto) {
        Modelo modeloCast = new Modelo();
        modeloCast.setEspecificacaoDoModelo(dto.especificacaoDoModelo());
        modeloCast.setDescricao(dto.descricao());
        modeloCast.setNomeModelo(dto.nomeModelo());
        modeloCast.setMotorizacao(dto.motorizacao());
        modeloCast.setNumeroDePortas(dto.numeroDePortas());
        modeloCast.setCapacidadeDePassageiros(dto.capacidadeDePassageiros());
        if(dto.carro() != null){
            modeloCast.setCarro(Carro.valueOfCarroIdDTO(dto.carro()));
        }
        return modeloCast;
    }

    public static Modelo valueOfModeloMarcaDTO(@Valid ModeloMarcaDTO dto) {
        Modelo modeloCast = new Modelo();
        modeloCast.setEspecificacaoDoModelo(dto.especificacaoDoModelo());
        modeloCast.setDescricao(dto.descricao());
        modeloCast.setNomeModelo(dto.nomeModelo());
        modeloCast.setMotorizacao(dto.motorizacao());
        modeloCast.setNumeroDePortas(dto.numeroDePortas());
        modeloCast.setCapacidadeDePassageiros(dto.capacidadeDePassageiros());
        if(dto.marca() != null){
            modeloCast.setMarca(Marca.valueOfMarcaCarroDTO(dto.marca()));
        }
        return modeloCast;
    }

    public static Modelo valueOfModeloIdDTO(@Valid ModeloIdDTO dto) {
        Modelo modeloCast = null;
        if(dto != null){
            modeloCast = new Modelo();
            modeloCast.setId(dto.id());
        }
        return modeloCast;
    }

    public String getEspecificacaoDoModelo() {
        return especificacaoDoModelo;
    }


    public void setEspecificacaoDoModelo(String especificacaoDoModelo) {
        this.especificacaoDoModelo = especificacaoDoModelo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getNomeModelo() {
        return nomeModelo;
    }
    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }
    public String getMotorizacao() {
        return motorizacao;
    }
    public void setMotorizacao(String motorizacao) {
        this.motorizacao = motorizacao;
    }
    public String getNumeroDePortas() {
        return numeroDePortas;
    }
    public void setNumeroDePortas(String numeroDePortas) {
        this.numeroDePortas = numeroDePortas;
    }
    public String getCapacidadeDePassageiros() {
        return capacidadeDePassageiros;
    }
    public void setCapacidadeDePassageiros(String capacidadeDePassageiros) {
        this.capacidadeDePassageiros = capacidadeDePassageiros;
    }
    public float getPrecoBase() {
        return precoBase;
    }
    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }
    public float getOpcoesDePacote() {
        return opcoesDePacote;
    }
    public void setOpcoesDePacote(float opcoesDePacote) {
        this.opcoesDePacote = opcoesDePacote;
    }
    public Carro getCarro() {
        return carro;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
