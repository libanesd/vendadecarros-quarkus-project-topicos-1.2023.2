package br.unitins.topicos1.service;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.ModeloDTORepository.ModeloInsertDTO;
import br.unitins.topicos1.dto.ModeloDTORepository.ModeloResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Modelo;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.ModeloRepository;
import br.unitins.topicos1.resource.AuthResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService{

    @Inject
    ModeloRepository repository;

    @Inject
    MarcaRepository marcaRepository;
    @Inject
    CarroRepository carroRepository;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);


    @Override
    @Transactional
    public ModeloResponseDTO insert(@Valid ModeloInsertDTO dto) {
        Modelo novoModelo = Modelo.valueOfModeloInsertDTO(dto);
        LOG.infof("com a lista de carros: %s", novoModelo.getCarro());
        repository.persist(novoModelo);
        return ModeloResponseDTO.valueOf(novoModelo);
    }

    @Override
    @Transactional
    public ModeloResponseDTO update(ModeloInsertDTO dto, Long id) {
        Modelo modelo = repository.findById(id);
        Carro carro = carroRepository.findById(dto.carro().id());
        Marca marca = marcaRepository.findById(dto.marca().id());
        if(dto.nomeModelo() != null){
            modelo.setNomeModelo(dto.nomeModelo());
       }
       if (carro != null){
            modelo.setCarro(carro);
       }
       if (marca != null){
        modelo.setMarca(marca);
        }
       repository.persist(modelo);
        return ModeloResponseDTO.valueOf(modelo);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ModeloResponseDTO findById(Long id) {
        Modelo modelo = repository.findById(id);
        return ModeloResponseDTO.valueOf(modelo);
    }

    @Override
    public List<ModeloResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> ModeloResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ModeloResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> ModeloResponseDTO.valueOf(e)).toList();
    }
}
