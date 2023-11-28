package br.unitins.topicos1.dto.CarroDTORepository;

import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CarroTestDTO {

    @NotBlank(message = "O campo Nome não pode ser nulo.")
    private String nomeCarro;
    @NotBlank(message = "O campo Spec não pode ser nulo.")
    private String carroSpec;
    @NotBlank(message = "O campo Versão não pode ser nulo.")
    private String versao;
    @NotBlank(message = "O campo Ano não pode ser nulo.")
    private String ano;
    @NotBlank(message = "O campo Cor não pode ser nulo.")
    private String cor;
    @NotBlank(message = "O campo Caracteristicas não pode ser nulo.")
    private String caracteristicas;
    @NotBlank(message = "O campo Cidade não pode ser nulo.")
    private String cidade;
    @Positive(message = "O valor deve ser positivo")
    private Float preco;
    @Positive(message = "O valor deve ser positivo")
    private Float kilometragem;
    private Marca marca;
    private TipoCombustivel tipoCombustivel;
    private TipoCarroceria tipoCarroceria;
    private TipoCambio tipoCambio;

    public CarroTestDTO() {
    }

    // Constructor with all fields
    public CarroTestDTO(String nomeCarro, String carroSpec, String versao, String ano, String cor,
                        String caracteristicas, String cidade, Float preco, Float kilometragem,
                        TipoCombustivel tipoCombustivel, TipoCarroceria tipoCarroceria, TipoCambio tipoCambio) {
        this.nomeCarro = nomeCarro;
        this.carroSpec = carroSpec;
        this.versao = versao;
        this.ano = ano;
        this.cor = cor;
        this.caracteristicas = caracteristicas;
        this.cidade = cidade;
        this.preco = preco;
        this.kilometragem = kilometragem;
        this.tipoCombustivel = tipoCombustivel;
        this.tipoCarroceria = tipoCarroceria;
        this.tipoCambio = tipoCambio;
    }

    public CarroTestDTO(String string, String string2, String string3, String string4, String string5, String string6,
            String string7, float i, float d,Marca marca, TipoCombustivel gasolina, TipoCarroceria cupe, TipoCambio automatica) {
    }

    // Getters and setters for all fields
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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Float getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Float kilometragem) {
        this.kilometragem = kilometragem;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
