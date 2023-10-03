package br.unitins.topicos1.dto;

import java.util.List;

public record CategoriaDTO(
    String nome,
    List<CarroDTO> carros
) {
    
}
