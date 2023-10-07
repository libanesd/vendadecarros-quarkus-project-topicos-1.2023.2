package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroResponseDTO;
import br.unitins.topicos1.dto.CategoriaDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoriaServiceimpl implements CategoriaService{
    
    @Inject
    CategoriaRepository repository;

    @Override
    public CategoriaResponseDTO insert(CategoriaDTO dto) {
        Categoria novaCategoria = Categoria.valueOfCategoriaDTO(dto);
        repository.persist(novaCategoria);
        return CategoriaResponseDTO.valueOf(novaCategoria);
    }

    @Override
    public CategoriaResponseDTO update(CategoriaDTO dto, Long id) {
        Categoria categoria = repository.findById(id);
        if(dto.nome() != null){
             categoria.setNome(dto.nome());
        }
        if (dto.carros() != null && 
                    !dto.carros().isEmpty()){
            categoria.setCarros(new ArrayList<Carro>());
            for (CarroResponseDTO car : dto.carros()) {
                Carro carro = Carro.valueOfCarroResponseDTO(car);
                categoria.getCarros().add(carro);
            }
        }
        repository.persist(categoria);
        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = repository.findById(id);
        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CategoriaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }
    
}
