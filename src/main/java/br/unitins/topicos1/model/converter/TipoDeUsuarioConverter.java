package br.unitins.topicos1.model.converter;

import br.unitins.topicos1.model.TipoDeUsuario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDeUsuarioConverter implements AttributeConverter<TipoDeUsuario, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoDeUsuario attribute) {
        return ( attribute == null ? null : attribute.getId());
    }

    @Override
    public TipoDeUsuario convertToEntityAttribute(Integer dbData) {
        return TipoDeUsuario.valueOf(dbData);
    }
    
}
