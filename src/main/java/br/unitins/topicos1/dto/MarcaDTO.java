package br.unitins.topicos1.dto;

import java.util.List;

public record MarcaDTO(
    String nome,
    List<CarroDTO> carros
) {
    
}