package br.unitins.topicos1.dto.AuthDTORepository;

import jakarta.validation.constraints.NotEmpty;

public record ValidarCodigo(
    @NotEmpty(message = "Por favor preencha o login")
    String codigo,
    @NotEmpty(message = "Senha incompativel")
    String senha,
    @NotEmpty(message = "Senha incompativel")
    String repetirSenha
) {
}

