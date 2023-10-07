package br.unitins.topicos1.model;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.CarroIdDTO;
import br.unitins.topicos1.dto.CarroResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro extends DefaultEntity{
    
    @Column(length = 200, nullable = true)
    private String nomeCarro;
    @Column(length = 200, nullable = true)
    private String carroSpec;
    @Column(length = 200, nullable = true)
    private String versao;
    @Column(length = 200, nullable = true)
    private String ano;
    @Column(length = 200, nullable = true)
    private String cor;
    @Column(length = 200, nullable = true)
    private String caracteristicas;
    @Column(length = 200, nullable = true)
    private String cidade;

    @Column(nullable = true)
    private Float preco;
    @Column(nullable = true)
    private Float kilometragem;

    @ManyToOne
    @JoinColumn(name = "marcaId")
    private Marca marca;

    @ManyToMany
    @JoinTable(name = "carro_categoria",
               joinColumns = @JoinColumn(name = "carro_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    
    @ManyToMany
    @JoinTable(name = "carro_oferta",
               joinColumns = @JoinColumn(name = "carro_id"),
               inverseJoinColumns = @JoinColumn(name = "oferta_id"))
    private  List<Oferta> ofertas;
    
    @OneToOne
    @JoinColumn(name = "vendaId")
    private Venda venda;
    
   
    @Enumerated(EnumType.STRING)
    private TipoCombustivel tipoCombustivel;

    @Enumerated(EnumType.STRING)
    private TipoCambio tipoCambio;

    @Enumerated(EnumType.STRING)
    private TipoCarroceria tipoCarroceria;


    public void copiarCampos(Carro outroObjeto) {
        this.nomeCarro = outroObjeto.nomeCarro;
        this.carroSpec = outroObjeto.carroSpec;
        this.versao = outroObjeto.versao;
        this.ano = outroObjeto.ano;
        this.cor = outroObjeto.cor;
        this.caracteristicas = outroObjeto.caracteristicas;
        this.cidade = outroObjeto.cidade;
        this.preco = outroObjeto.preco;
        this.kilometragem = outroObjeto.kilometragem;
        this.marca = outroObjeto.marca;
        this.tipoCombustivel = outroObjeto.tipoCombustivel;
        this.tipoCambio = outroObjeto.tipoCambio;
        this.tipoCarroceria = outroObjeto.tipoCarroceria;
    }

    public static Carro valueOfCarroResponseDTO(CarroResponseDTO carro){
        Carro carroCast = new Carro();
        carroCast.setId(carro.id());
        carroCast.setNomeCarro(carro.nomeCarro());
        carroCast.setCarroSpec(carro.carroSpec());
        carroCast.setVersao(carro.versao());
        carroCast.setAno(carro.ano());
        carroCast.setCor(carro.cor());
        carroCast.setCaracteristicas(carro.caracteristicas());
        carroCast.setCidade(carro.cidade());
        carroCast.setPreco(carro.preco());
        carroCast.setKilometragem(carro.kilometragem());
        carroCast.setMarca(Marca.valueOfMarcaCarroDTO(carro.marca()));
        carroCast.setTipoCambio(carro.tipoCambio());
        carroCast.setTipoCarroceria(carro.tipoCarroceria());
        carroCast.setTipoCombustivel(carro.tipoCombustivel());
        return carroCast;
    }

    public static Carro valueOfCarroDTO(CarroDTO carro){
        Carro carroCast = new Carro();
        carroCast.setNomeCarro(carro.nomeCarro());
        carroCast.setCarroSpec(carro.carroSpec());
        carroCast.setVersao(carro.versao());
        carroCast.setAno(carro.ano());
        carroCast.setCor(carro.cor());
        carroCast.setCaracteristicas(carro.caracteristicas());
        carroCast.setCidade(carro.cidade());
        carroCast.setPreco(carro.preco());
        carroCast.setKilometragem(carro.kilometragem());
        carroCast.setMarca(Marca.valueOfMarcaCarroDTO(carro.marca()));
        carroCast.setTipoCambio(carro.tipoCambio());
        carroCast.setTipoCarroceria(carro.tipoCarroceria());
        carroCast.setTipoCombustivel(carro.tipoCombustivel());
        return carroCast;
    }

    public static Carro valueOfCarroIdDTO(CarroIdDTO carro){
        Carro carroCast = new Carro();
        carroCast.setId(carro.id());
        return carroCast;
    }

     public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    public String getNomeCarro() {
        return nomeCarro;
    }
    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }
    public String getCarroSpec() {
        return carroSpec;
    }
    public void setCarroSpec(String carroSpec) {
        this.carroSpec = carroSpec;
    }
    public String getVersao() {
        return versao;
    }
    public void setVersao(String versao) {
        this.versao = versao;
    }
     public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    } 
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }
    public Float getKilometragem() {
        return kilometragem;
    }
    public void setKilometragem(Float kilometragem) {
        this.kilometragem = kilometragem;
    }
    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }
    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }
    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }
    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    } 
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public Float getPreco() {
        return preco;
    }
    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }
    public void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }
    
    public List<Categoria> getCategoria() {
        return categorias;
    }
    public void setCategoria(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public List<Oferta> getOfertas() {
        return ofertas;
    }
    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
}
