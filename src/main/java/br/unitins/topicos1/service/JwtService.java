package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ClienteResponseDTO;

public interface JwtService {

    public String generateJwt(ClienteResponseDTO dto);
} 
