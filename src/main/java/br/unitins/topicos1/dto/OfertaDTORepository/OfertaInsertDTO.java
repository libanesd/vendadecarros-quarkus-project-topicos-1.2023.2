package br.unitins.topicos1.dto.OfertaDTORepository;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaIdDTO;
import br.unitins.topicos1.dto.ClienteDTORepository.ClienteIdDTO;
import br.unitins.topicos1.model.Oferta;

public record OfertaInsertDTO(
    String nome,
    List<CarroIdDTO> carros,
    List<CategoriaIdDTO> categorias,
    List<ClienteIdDTO> clientes
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
            oferta.getClientes()
                .stream()   
                .map(t -> ClienteIdDTO.valueOf(t)).toList()
        );
    }
}
