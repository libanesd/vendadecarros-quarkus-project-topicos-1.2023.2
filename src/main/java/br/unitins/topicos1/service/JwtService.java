package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ClienteDTORepository.ClienteJwtDTO;

public interface JwtService {

    public String generateJwt(ClienteJwtDTO dto);
} 
