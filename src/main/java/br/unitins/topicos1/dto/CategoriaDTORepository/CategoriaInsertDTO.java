package br.unitins.topicos1.dto.CategoriaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaInsertDTO(
    @NotBlank(message = "O campo Nome não pode ser nulo.")
    String nome,
    List<CarroIdDTO> carros
) {
    public static CategoriaInsertDTO valueOf(Categoria categoria){
        return new CategoriaInsertDTO(
            categoria.getNome(),    
            categoria.getCarros()
                .stream()
                .map(t -> CarroIdDTO.valueOf(t)).toList()
        );
    }
}
