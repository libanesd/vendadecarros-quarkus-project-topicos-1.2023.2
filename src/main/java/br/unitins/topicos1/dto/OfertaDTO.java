package br.unitins.topicos1.dto;

import java.util.List;

public record OfertaDTO(
    String nome,
    List<CarroDTO> carros
)
{

}