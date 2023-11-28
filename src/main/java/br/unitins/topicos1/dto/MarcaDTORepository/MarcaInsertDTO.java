package br.unitins.topicos1.dto.MarcaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.model.Marca;
import jakarta.validation.constraints.NotBlank;

public record MarcaInsertDTO(
    @NotBlank(message = "O campo Nome não pode ser nulo.")
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
