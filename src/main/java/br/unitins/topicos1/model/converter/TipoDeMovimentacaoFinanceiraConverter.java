package br.unitins.topicos1.model.converter;
import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDeMovimentacaoFinanceiraConverter implements AttributeConverter<TipoDeMovimentacaoFinanceira, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoDeMovimentacaoFinanceira attribute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToDatabaseColumn'");
    }

    @Override
    public TipoDeMovimentacaoFinanceira convertToEntityAttribute(Integer dbData) {
        return TipoDeMovimentacaoFinanceira.valueOf(dbData);
    }
    
}
