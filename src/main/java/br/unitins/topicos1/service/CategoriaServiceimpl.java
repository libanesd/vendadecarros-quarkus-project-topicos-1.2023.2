package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaInsertDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CategoriaServiceimpl implements CategoriaService{
    
    @Inject
    CategoriaRepository repository;
    @Inject
    CarroRepository carroRepository;

    @Override
    @Transactional
    public CategoriaResponseDTO insert(@Valid CategoriaInsertDTO dto) {
        Categoria novaCategoria = Categoria.valueOfCategoriaInsertDTO(dto);
        if(novaCategoria.getCarros()!= null && !novaCategoria.getCarros().isEmpty()){
            List<Carro> carroList = new ArrayList<Carro>();
            for (Carro car : novaCategoria.getCarros()) {
                Carro carroDb = carroRepository.findById(car.getId());
                carroDb.getCategoria().add(novaCategoria);
                carroRepository.persist(carroDb);
                carroList.add(carroDb);
            }
            novaCategoria.setCarros(carroList);
        }else{
            List<Carro> carroList = new ArrayList<Carro>();
            novaCategoria.setCarros(carroList);
        }
        novaCategoria.setDeletado(false);
        novaCategoria.setDesativado(false);
        repository.persist(novaCategoria);
        return CategoriaResponseDTO.valueOf(novaCategoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(CategoriaInsertDTO dto, Long id) {
        Categoria categoria = repository.findById(id);
        
        if(dto.nome() != null){
             categoria.setNome(dto.nome());
        }
        if (dto.carros() != null && !dto.carros().isEmpty()){
            if(categoria.getCarros() == null || categoria.getCarros().isEmpty()){
                categoria.setCarros(new ArrayList<Carro>());
            }
            for (CarroIdDTO car : dto.carros()) {
                Carro carroDb = carroRepository.findById(car.id());
                if(carroDb.getCategoria() == null || carroDb.getCategoria().isEmpty()){
                    carroDb.setCategoria(new ArrayList<Categoria>());
                }
                carroDb.getCategoria().add(categoria);
                carroRepository.persist(carroDb);
                categoria.getCarros().add(carroDb);
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
        return repository.findAllEnable().stream()
            .map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }
    
    @Override
    public void marcarComoDeletado(Long id){
        Categoria categoria = repository.findById(id);
        categoria.setDeletado(true);
        repository.persist(categoria);
    }
    @Override
    public void marcarComoDesativado(Long id){
        Categoria categoria = repository.findById(id);
        categoria.setDesativado(true);
        repository.persist(categoria);
    }
}
