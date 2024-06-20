package br.unitins.topicos1.service;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroEstoqueDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.resource.AuthResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CarroServiceimpl implements CarroService{

    @Inject
    CarroRepository repository;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @Override    
    @Transactional
    public CarroResponseDTO insert(@Valid CarroDTO dto) {
        Carro novoCarro = Carro.valueOfCarroDTO(dto);
        LOG.infof("Iniciando o evento de inserir carro %s");
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

    @Override
    public List<CarroEstoqueDTO> findByAllEstoque() {
        // TODO Auto-generated method stub
        return repository.listAll().stream()
            .map(e -> CarroEstoqueDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public CarroResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Carro carro = repository.findById(id);
        carro.setNomeImagem(nomeImagem);
        return CarroResponseDTO.valueOf(carro);
    }

    @Override
    public List<CarroResponseDTO> findCarrosAVenda() {
        // TODO Auto-generated method stub
        return repository.findCarrosAVenda().stream()
            .map(e -> CarroResponseDTO.valueOf(e)).toList();
    }
    
}
