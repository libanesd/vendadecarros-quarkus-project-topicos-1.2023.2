package br.unitins.topicos1.dto.AuthDTORepository;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO (
    @NotEmpty(message = "Por favor preencha o login")
    String login,
    @NotEmpty(message = "Senha incompativel")
    String senha
) {

}
