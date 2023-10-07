package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteInsertDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    @Override
    @Transactional
    public ClienteResponseDTO insert(ClienteInsertDTO dto) {
        Cliente novoCliente = Cliente.valueOfClienteInsertDTO(dto);
        List<Oferta> oferta = new ArrayList<Oferta>();
        List<Venda> vendas = new ArrayList<Venda>();
        novoCliente.setOfertas(oferta);
        novoCliente.setVendas(vendas);
        repository.persist(novoCliente);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {
        Cliente novoCliente = repository.findById(id);
        repository.persist(novoCliente);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public ClienteResponseDTO findById(Long id) {
        Cliente novoCliente = repository.findById(id);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public List<ClienteResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }
    
}
