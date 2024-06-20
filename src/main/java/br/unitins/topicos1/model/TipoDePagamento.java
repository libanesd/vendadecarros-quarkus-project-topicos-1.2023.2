package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.unitins.topicos1.model.deserializer.MyEnumDeserializerTipoDePagamento;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = MyEnumDeserializerTipoDePagamento.class)
public enum TipoDePagamento {

    @JsonProperty("DEBITO")
    DEBITO(1, "Debito"), 
    @JsonProperty("CREDITO")
    CREDITO(2, "Credito"),
    @JsonProperty("BOLETO")
    BOLETO(3, "Boleto"),
    @JsonProperty("PIX")
    PIX(4, "Pix");

    private final Integer id;
    private final String label;

    
    TipoDePagamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoDePagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoDePagamento perfil : TipoDePagamento.values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}
