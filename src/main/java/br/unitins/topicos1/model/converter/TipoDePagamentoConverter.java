package br.unitins.topicos1.model.converter;

import br.unitins.topicos1.model.TipoDePagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDePagamentoConverter implements AttributeConverter<TipoDePagamento, Integer>{
    
    @Override
    public TipoDePagamento convertToEntityAttribute(Integer dbData) {
        return TipoDePagamento.valueOf(dbData);
    }

    @Override
    public Integer convertToDatabaseColumn(TipoDePagamento attribute) {
        return ( attribute == null ? null : attribute.getId());
    }
}
