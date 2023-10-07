package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    @Override
    public ClienteResponseDTO insert(ClienteDTO dto) {
        Cliente novoCliente = Cliente.valueOfClienteDTO(dto);
        repository.persist(novoCliente);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {
        Cliente novoCliente = repository.findById(id);
        repository.persist(novoCliente);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente novoCliente = repository.findById(id);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }
    
}
