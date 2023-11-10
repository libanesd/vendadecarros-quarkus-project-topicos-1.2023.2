package br.unitins.topicos1.dto.MarcaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;
import br.unitins.topicos1.model.Marca;

public record MarcaResponseDTO(
    Long id,
    String nome,
    List<CarroResponseDTO> carros
) {
    public static MarcaResponseDTO valueOf(Marca marca){
        return new MarcaResponseDTO(
            marca.getId(), 
            marca.getNomeMarca(),
            marca.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList()
        );
    }
}
