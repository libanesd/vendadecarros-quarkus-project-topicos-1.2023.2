package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;

public interface JwtService {

    public String generateJwt(UsuarioJwtDTO dto);
} 
