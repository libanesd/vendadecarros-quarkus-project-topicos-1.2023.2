package br.unitins.topicos1.dto.MarcaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;
import br.unitins.topicos1.model.Marca;

public record MarcaDTO(
    String nome,
    List<CarroResponseDTO> carros
) {
    public static MarcaDTO valueOf(Marca marca){
        return new MarcaDTO(
            marca.getNomeMarca(),
            marca.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList()
        );
    }
}