package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImpl implements  MarcaService{
        
    @Inject
    MarcaRepository repository;

    @Override
    @Transactional
    public MarcaResponseDTO insert(MarcaDTO dto) {
        Marca novaOMarca = Marca.valueOfMarcaDTO(dto);
        repository.persist(novaOMarca);
        return MarcaResponseDTO.valueOf(novaOMarca);
    }

    @Override
    public MarcaResponseDTO update(MarcaDTO dto, Long id) {
        Marca marca = repository.findById(id);
        if(dto.nome() != null){
             marca.setNomeMarca(dto.nome());
        }
        if (dto.carros() != null && 
                    !dto.carros().isEmpty()){
            marca.setCarros(new ArrayList<Carro>());
            for (CarroResponseDTO car : dto.carros()) {
                Carro carro = Carro.valueOfCarroResponseDTO(car);
                marca.getCarros().add(carro);
            }
        }
        repository.persist(marca);
        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MarcaResponseDTO findById(Long id) {
        Marca marca = repository.findById(id);
        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    public List<MarcaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MarcaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }
}
