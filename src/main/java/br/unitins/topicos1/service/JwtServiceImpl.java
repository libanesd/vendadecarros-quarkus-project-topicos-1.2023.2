package br.unitins.topicos1.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService{

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioJwtDTO dto) {
        // TODO Auto-generated method stub
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        // exemplo para teste
        Set<String> roles = new HashSet<String>();
        roles.add(dto.tipodeusuario().getLabel());
        System.out.println(dto.tipodeusuario().getLabel());
        

        return Jwt.issuer("unitins-jwt")
            .subject(dto.login())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
    }
    
}
