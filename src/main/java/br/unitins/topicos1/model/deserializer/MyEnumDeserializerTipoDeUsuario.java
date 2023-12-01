package br.unitins.topicos1.model.deserializer;
import java.util.stream.Stream;
import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.unitins.topicos1.model.TipoDeUsuario;

public class MyEnumDeserializerTipoDeUsuario extends JsonDeserializer<TipoDeUsuario>{

    @Override
    public TipoDeUsuario deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO Auto-generated method stub
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("label").asText();
        return Stream.of(TipoDeUsuario.values())
        .filter(enumValue -> enumValue.getLabel().equals(type))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("type "+type+" is not recognized"));
    }
    
}
