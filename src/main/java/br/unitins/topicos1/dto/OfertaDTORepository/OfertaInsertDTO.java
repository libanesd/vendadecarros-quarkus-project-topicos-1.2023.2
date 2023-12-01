package br.unitins.topicos1.dto.OfertaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaIdDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioIdDTO;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.StatusOferta;
import jakarta.validation.constraints.NotEmpty;

public record OfertaInsertDTO(
    @NotEmpty(message = "O campo nome não pode ser nulo!")
    String nome,
    List<CarroIdDTO> carros,
    List<CategoriaIdDTO> categorias,
    List<UsuarioIdDTO> usuarios,
    StatusOferta statusOferta,
    Float porcentagemDeDesconto
) {
    public static OfertaInsertDTO valueOf(Oferta oferta){
        return new OfertaInsertDTO(
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroIdDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaIdDTO.valueOf(t)).toList(),
            oferta.getUsuarios()
                .stream()   
                .map(t -> UsuarioIdDTO.valueOf(t)).toList(),
            oferta.getStatusOferta(),
            oferta.getPorcentagemDeDesconto()
        );
    }
}
