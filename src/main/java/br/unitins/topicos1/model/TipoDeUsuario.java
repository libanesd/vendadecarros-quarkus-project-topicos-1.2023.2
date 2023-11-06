package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoDeUsuario {
    CLIENTE(1, "Cliente"), 
    ADMINISTRADOR(2, "Administrador");

    private final Integer id;
    private final String label;

    
    TipoDeUsuario(Integer id, String label) {
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
