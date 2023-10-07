package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroResponseDTO;
import br.unitins.topicos1.dto.OfertaDTO;
import br.unitins.topicos1.dto.OfertaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.repository.OfertaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OfertaServiceImpl implements  OfertaService{

    @Inject
    OfertaRepository repository;

    @Override
    @Transactional
    public OfertaResponseDTO insert(OfertaDTO dto) {
        Oferta novaOferta = Oferta.valueOfOfertaDTO(dto);
        repository.persist(novaOferta);
        return OfertaResponseDTO.valueOf(novaOferta);
    }

    @Override
    public OfertaResponseDTO update(OfertaDTO dto, Long id) {
        Oferta oferta = repository.findById(id);
        if(dto.nome() != null){
             oferta.setNome(dto.nome());
        }
        if (dto.carros() != null && 
                    !dto.carros().isEmpty()){
            oferta.setCarros(new ArrayList<Carro>());
            for (CarroResponseDTO car : dto.carros()) {
                Carro carro = Carro.valueOfCarroResponseDTO(car);
                oferta.getCarros().add(carro);
            }
        }
        repository.persist(oferta);
        return OfertaResponseDTO.valueOf(oferta);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public OfertaResponseDTO findById(Long id) {
        Oferta oferta = repository.findById(id);
        return OfertaResponseDTO.valueOf(oferta);
    }

    @Override
    public List<OfertaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<OfertaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }
}
