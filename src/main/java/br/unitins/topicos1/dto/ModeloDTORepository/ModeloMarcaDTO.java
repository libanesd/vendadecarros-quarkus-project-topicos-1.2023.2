package br.unitins.topicos1.dto.ModeloDTORepository;

import br.unitins.topicos1.dto.MarcaDTORepository.MarcaCarroDTO;
import br.unitins.topicos1.model.Modelo;
import jakarta.validation.constraints.NotBlank;

public record ModeloMarcaDTO (
    @NotBlank(message = "O campo especificacaoDoModelo não pode ser nulo.")
    String especificacaoDoModelo,
    @NotBlank(message = "O campo descricao não pode ser nulo.")
    String descricao,
    @NotBlank(message = "O campo nomeModelo não pode ser nulo.")
    String nomeModelo,
    @NotBlank(message = "O campo motorizacao não pode ser nulo.")
    String motorizacao,
    @NotBlank(message = "O campo numeroDePortas não pode ser nulo.")
    String numeroDePortas,
    @NotBlank(message = "O campo capacidadeDePassageiros não pode ser nulo.")
    String capacidadeDePassageiros,
    MarcaCarroDTO marca
){
    public static ModeloMarcaDTO valueOf(Modelo modelo){
        return new ModeloMarcaDTO(
            modelo.getEspecificacaoDoModelo(),
            modelo.getDescricao(),
            modelo.getNomeModelo(),
            modelo.getMotorizacao(),
            modelo.getNumeroDePortas(),
            modelo.getCapacidadeDePassageiros(),
            MarcaCarroDTO.valueOf(modelo.getMarca())
        );
    }
}
