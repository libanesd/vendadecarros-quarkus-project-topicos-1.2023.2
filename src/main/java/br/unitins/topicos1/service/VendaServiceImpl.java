package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.OfertaRepository;
import br.unitins.topicos1.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    @Inject
    VendaRepository repository;
    @Inject
    ClienteRepository clienteRepository;
    @Inject
    CarroRepository carroRepository;
    @Inject
    OfertaRepository ofertaRepository;

    @Override
    @Transactional
    public VendaResponseDTO insert(@Valid VendaInsertDTO dto) {
        // TODO Auto-generated method stub
        Venda novaVenda = Venda.valueOfVendaInsertDTO(dto);
        Carro carro = carroRepository.findById(dto.carro().id());
        Cliente cliente = clienteRepository.findById(dto.cliente().id());
        novaVenda.setCarro(carro);
        novaVenda.setCliente(cliente);
        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    @Transactional
    public VendaResponseDTO update(VendaInsertDTO dto, Long id) {
        Venda novaVenda = repository.findById(id);
        novaVenda = Venda.valueOfVendaInsertDTO(dto);
        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public VendaResponseDTO findById(Long id) {
       Venda novaVenda = repository.findById(id);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    public List<VendaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<VendaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }
    
}
