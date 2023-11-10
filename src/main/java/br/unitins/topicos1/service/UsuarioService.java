package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioResponseDTO;

public interface UsuarioService {

    public UsuarioResponseDTO insert(UsuarioDTO dto);

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public List<UsuarioResponseDTO> findByAll(); 
    
}
