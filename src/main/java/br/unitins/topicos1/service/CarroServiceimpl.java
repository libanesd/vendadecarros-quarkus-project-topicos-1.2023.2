package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.CarroResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.repository.CarroRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CarroServiceimpl implements CarroService{

    @Inject
    CarroRepository repository;

    @Override    
    @Transactional
    public CarroResponseDTO insert(CarroDTO dto) {
        Carro novoCarro = Carro.valueOfCarroDTO(dto);
        repository.persist(novoCarro);
        return CarroResponseDTO.valueOf(novoCarro);
    }

    @Override
    @Transactional
    public CarroResponseDTO update(CarroDTO dto, Long id) {
        Carro novoCarro = repository.findById(id);
        novoCarro.copiarCampos(Carro.valueOfCarroDTO(dto));
        repository.persist(novoCarro);
        return CarroResponseDTO.valueOf(novoCarro);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @RolesAllowed({"Cliente", "Administrador"})
    public CarroResponseDTO findById(Long id) {
        Carro carro = repository.findById(id);
        return CarroResponseDTO.valueOf(carro);
    }

    @Override
    public List<CarroResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> CarroResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CarroResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> CarroResponseDTO.valueOf(e)).toList();
    }
    
}
