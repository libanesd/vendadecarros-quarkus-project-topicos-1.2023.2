package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroResponseDTO;
import br.unitins.topicos1.dto.OfertaDTO;
import br.unitins.topicos1.dto.OfertaInsertDTO;
import br.unitins.topicos1.dto.OfertaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.CategoriaRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.OfertaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OfertaServiceImpl implements  OfertaService{

    @Inject
    OfertaRepository repository;
    @Inject
    CarroRepository carroRepository;
    @Inject
    CategoriaRepository categoriaRepository;
    @Inject
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public OfertaResponseDTO insert(OfertaInsertDTO dto) {
        Oferta novaOferta = Oferta.valueOfOfertaInsertDTO(dto);

        List<Carro> carroList = new ArrayList<Carro>();
        for (Carro car : novaOferta.getCarros()) {
            Carro carroDb = carroRepository.findById(car.getId());
            carroList.add(carroDb);
        }
        List<Categoria> categoriaList = new ArrayList<Categoria>();
        for (Categoria cat : novaOferta.getCategorias()) {
            Categoria categoriaDb = categoriaRepository.findById(cat.getId());
            categoriaList.add(categoriaDb);
        }

        List<Cliente> clients = new ArrayList<Cliente>();
        for (Cliente cli : novaOferta.getClientes()) {
            Cliente clienteDb = clienteRepository.findById(cli.getId());
            clients.add(clienteDb);
        }

        novaOferta.getClientes().clear();
        novaOferta.setClientes(clients);

        novaOferta.getCategorias().clear();
        novaOferta.setCategorias(categoriaList);

        novaOferta.getCarros().clear();
        novaOferta.setCarros(carroList);
        
        repository.persist(novaOferta);
        return OfertaResponseDTO.valueOf(novaOferta);
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public OfertaResponseDTO findById(Long id) {
        Oferta oferta = repository.findById(id);
        return OfertaResponseDTO.valueOf(oferta);
    }

    @Override
    @Transactional
    public List<OfertaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public List<OfertaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }
}
