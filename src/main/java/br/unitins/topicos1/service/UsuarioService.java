package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioUpdateDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    public UsuarioResponseDTO insert(@Valid UsuarioInsertDTO dto);

    public UsuarioResponseDTO update(UsuarioUpdateDTO dto, Long id);

    public UsuarioJwtDTO findByLoginAndSenha(String login, String senha);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public UsuarioJwtDTO findByLogin(String login);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public List<UsuarioResponseDTO> findByAll();
}
