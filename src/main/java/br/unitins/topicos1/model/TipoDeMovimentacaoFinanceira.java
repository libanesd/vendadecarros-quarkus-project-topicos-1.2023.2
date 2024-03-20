package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.unitins.topicos1.model.deserializer.MyEnumDeserializerTipoDeUsuario;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = MyEnumDeserializerTipoDeUsuario.class)
public enum TipoDeMovimentacaoFinanceira {
    @JsonProperty("COMPRA")
    COMPRA(1, "Compra"), 
    @JsonProperty("VENDA")
    VENDA(2, "Venda");

    private final Integer id;
    private final String label;

    
    TipoDeMovimentacaoFinanceira(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoDeUsuario valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoDeUsuario perfil : TipoDeUsuario.values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}
