package br.unitins.topicos1.dto.ModeloDTORepository;

import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaCarroDTO;
import br.unitins.topicos1.model.Modelo;
import jakarta.validation.constraints.NotBlank;

public record ModeloResponseDTO(
    Long id,
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
    CarroIdDTO carro,
    MarcaCarroDTO marca
) {
    public static ModeloResponseDTO valueOf(Modelo modelo){
        return new ModeloResponseDTO(
            modelo.getId(),
            modelo.getEspecificacaoDoModelo(),
            modelo.getDescricao(),
            modelo.getNomeModelo(),
            modelo.getMotorizacao(),
            modelo.getNumeroDePortas(),
            modelo.getCapacidadeDePassageiros(),
            CarroIdDTO.valueOf(modelo.getCarro()),
            MarcaCarroDTO.valueOf(modelo.getMarca())
        );
    }
}
