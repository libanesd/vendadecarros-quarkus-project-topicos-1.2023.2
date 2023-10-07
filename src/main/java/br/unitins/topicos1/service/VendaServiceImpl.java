package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    @Inject
    VendaRepository repository;
    @Inject
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public VendaResponseDTO insert(VendaInsertDTO dto) {
        // TODO Auto-generated method stub
        Venda novaVenda = Venda.valueOfVendaInsertDTO(dto);
        Cliente cliente = clienteRepository.findById(novaVenda.getCliente().getId());
        novaVenda.setCliente(cliente);
        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    @Transactional
    public VendaResponseDTO update(VendaDTO dto, Long id) {
        Venda novaVenda = repository.findById(id);
        novaVenda = Venda.valueOfVendaDTO(dto);
        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public VendaResponseDTO findById(Long id) {
       Venda novaVenda = repository.findById(id);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    @Transactional
    public List<VendaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public List<VendaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }
    
}
