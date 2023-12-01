package br.unitins.topicos1.dto.OfertaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioResponseDTO;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.StatusOferta;

public record OfertaDTO(
    String nome,
    List<CarroResponseDTO> carros,
    List<CategoriaResponseDTO> categorias,
    List<UsuarioResponseDTO> usuarios,
    StatusOferta statusOferta,
    Float porcentagemDeDesconto
)
{
    public static OfertaDTO valueOf(Oferta oferta){
        return new OfertaDTO(
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroResponseDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaResponseDTO.valueOf(t)).toList(),
            oferta.getUsuarios()
                .stream()
                .map(t -> UsuarioResponseDTO.valueOf(t)).toList(),
            oferta.getStatusOferta(),
            oferta.getPorcentagemDeDesconto()
        );
    }

}