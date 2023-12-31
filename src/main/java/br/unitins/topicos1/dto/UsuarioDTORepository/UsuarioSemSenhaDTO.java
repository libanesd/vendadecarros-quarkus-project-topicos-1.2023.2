package br.unitins.topicos1.dto.UsuarioDTORepository;

import br.unitins.topicos1.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record UsuarioSemSenhaDTO (
    @NotBlank(message = "O campo cpf não pode ser nulo.")
    String cpf,
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O campo login não pode ser nulo.")
    @Email(message = "O login deve ser um endereço de e-mail válido")
    String login,
    @NotBlank(message = "O campo endereço não pode ser nulo.")
    String endereco,
    @NotBlank(message = "O campo telefone não pode ser nulo.")
    String telefone,
    @NotBlank(message = "O campo e-mail não pode ser nulo.")
    String email
){
    public static UsuarioSemSenhaDTO valueOf(Usuario usuario){
        return new UsuarioSemSenhaDTO(
            usuario.getCpf(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getEndereco(),
            usuario.getTelefone(),
            usuario.getEmail()
        );
    }
}
