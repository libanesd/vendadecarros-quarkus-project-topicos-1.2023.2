package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ClienteInsertDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.ClienteUpdateDTO;

public interface ClienteService {

    public ClienteResponseDTO insert(ClienteInsertDTO dto);

    public ClienteResponseDTO update(ClienteUpdateDTO dto, Long id);

    public void delete(Long id);

    public ClienteResponseDTO findById(Long id);

    public List<ClienteResponseDTO> findByNome(String nome);

    public List<ClienteResponseDTO> findByAll();
}
