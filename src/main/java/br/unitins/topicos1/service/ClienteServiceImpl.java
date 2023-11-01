package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.ClienteInsertDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.ClienteUpdateDTO;
import br.unitins.topicos1.dto.OfertaIdDTO;
import br.unitins.topicos1.dto.VendaIdDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.OfertaRepository;
import br.unitins.topicos1.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    @Inject
    OfertaRepository ofertaRepository;

    @Inject
    VendaRepository vendaRepository;

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
    public ClienteResponseDTO update(ClienteUpdateDTO dto, Long id) {
        Cliente novoCliente = repository.findById(id);
        if(dto.nome() != null){
             novoCliente.setNome(dto.nome());
        }
        if (dto.ofertas() != null && !dto.ofertas().isEmpty()){
            if(novoCliente.getOfertas() == null || novoCliente.getOfertas().isEmpty()){
                novoCliente.setOfertas(new ArrayList<Oferta>());
            }
            for (OfertaIdDTO ofe : dto.ofertas()) {
                Oferta oferta = ofertaRepository.findById(ofe.id());
                if(oferta.getClientes() == null || oferta.getClientes().isEmpty()){
                    oferta.setClientes(new ArrayList<Cliente>());
                }
                oferta.getClientes().add(novoCliente);
                ofertaRepository.persist(oferta);
                novoCliente.getOfertas().add(oferta);
            }
        }
        if (dto.vendas() != null && !dto.vendas().isEmpty()){
            if(novoCliente.getVendas() == null || novoCliente.getVendas().isEmpty()){
                novoCliente.setVendas(new ArrayList<Venda>());
            }
            for (VendaIdDTO ven : dto.vendas()) {
                Venda venda = vendaRepository.findById(ven.id());
                if(venda.getCliente() == null){
                    venda.setCliente(novoCliente);
                }
                vendaRepository.persist(venda);
                novoCliente.getVendas().add(venda);
            }
        }
        repository.persist(novoCliente);
        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
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
