package br.unitins.topicos1.dto.OfertaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaIdDTO;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.StatusOferta;

public record OfertaUsuarioDTO(
    Long id,
    String nome,
    List<CarroIdDTO> carros,
    List<CategoriaIdDTO> categorias,
    StatusOferta statusOferta
) {
    public static OfertaUsuarioDTO valueOf(Oferta oferta){
        return new OfertaUsuarioDTO(
            oferta.getId(), 
            oferta.getNome(),
            oferta.getCarros()
                .stream()
                .map(t -> CarroIdDTO.valueOf(t)).toList(),
            oferta.getCategorias()
                .stream()
                .map(t -> CategoriaIdDTO.valueOf(t)).toList(),
            oferta.getStatusOferta()
        );
    }
}
