package br.unitins.topicos1.dto.MarcaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.model.Marca;

public record MarcaInsertDTO(
    String nome,
    List<CarroIdDTO> carros
) {
    public static MarcaInsertDTO valueOf(Marca marca){
        return new MarcaInsertDTO(
            marca.getNomeMarca(),
            marca.getCarros()
                .stream()
                .map(t -> CarroIdDTO.valueOf(t)).toList()
        );
    }
}
