package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ClienteDTORepository.ClienteInsertDTO;
import br.unitins.topicos1.dto.ClienteDTORepository.ClienteJwtDTO;
import br.unitins.topicos1.dto.ClienteDTORepository.ClienteResponseDTO;
import br.unitins.topicos1.dto.ClienteDTORepository.ClienteUpdateDTO;
import jakarta.validation.Valid;

public interface ClienteService {

    public ClienteResponseDTO insert(@Valid ClienteInsertDTO dto);

    public ClienteResponseDTO update(ClienteUpdateDTO dto, Long id);

    public ClienteJwtDTO findByLoginAndSenha(String login, String senha);

    public void delete(Long id);

    public ClienteResponseDTO findById(Long id);

    public ClienteJwtDTO findByLogin(String login);

    public List<ClienteResponseDTO> findByNome(String nome);

    public List<ClienteResponseDTO> findByAll();
}
