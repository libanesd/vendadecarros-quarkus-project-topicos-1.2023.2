package br.unitins.topicos1.dto.OfertaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertDTO;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.StatusOferta;

public record OfertaResponseDTO(
    Long id,
    String nome,
    List<CarroResponseDTO> carros,
    List<CategoriaResponseDTO> categorias,
    List<UsuarioInsertDTO> usuarios,
    StatusOferta statusOferta,
    Float porcentagemDeDesconto
) {
    public static OfertaResponseDTO valueOf(Oferta oferta){
        return new OfertaResponseDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaResponseDTO.valueOf(t)).toList(),
            oferta.getUsuarios()
                .stream()   
                .map(t -> UsuarioInsertDTO.valueOf(t)).toList(),
            oferta.getStatusOferta(),
            oferta.getPorcentagemDeDesconto()
        );
    }
}
