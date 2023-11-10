package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaInsertDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImpl implements  MarcaService{
        
    @Inject
    MarcaRepository repository;
    @Inject
    CarroRepository carroRepository;

    @Override
    @Transactional
    public MarcaResponseDTO insert(MarcaInsertDTO dto) {
        Marca novaOMarca = Marca.valueOfMarcaInsertDTO(dto);
        if(novaOMarca.getCarros()!= null && !novaOMarca.getCarros().isEmpty()){
            List<Carro> carroList = new ArrayList<Carro>();
            for (Carro car : novaOMarca.getCarros()) {
                Carro carroDb = carroRepository.findById(car.getId());
                carroList.add(carroDb);
            }
            novaOMarca.getCarros().clear();
            novaOMarca.setCarros(carroList);
        }else{
            List<Carro> carros = new ArrayList<Carro>();
            novaOMarca.setCarros(carros);
        }
        
        repository.persist(novaOMarca);
        return MarcaResponseDTO.valueOf(novaOMarca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO update(MarcaInsertDTO dto, Long id) {
        Marca marca = repository.findById(id);
        if(dto.nome() != null){
             marca.setNomeMarca(dto.nome());
        }
        if (dto.carros() != null && !dto.carros().isEmpty()){

            marca.setCarros(new ArrayList<Carro>());

            for (Carro carr : marca.getCarros()) {
                for (CarroIdDTO car : dto.carros()) {
                    if(carr.getId() != car.id()){
                        Carro carroDb = carroRepository.findById(car.id());
                        marca.getCarros().add(carroDb);
                    }
                }
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
