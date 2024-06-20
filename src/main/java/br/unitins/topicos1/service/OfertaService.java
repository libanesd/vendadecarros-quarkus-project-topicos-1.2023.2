package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaInsertDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaResponseDTO;
import jakarta.validation.Valid;

public interface OfertaService {

    public OfertaResponseDTO insert(@Valid OfertaInsertDTO dto);

    public OfertaResponseDTO update(OfertaInsertDTO dto, Long id);

    public void delete(Long id);

    public OfertaResponseDTO findById(Long id);

    public List<OfertaResponseDTO> findByNome(String nome);

    public List<OfertaResponseDTO> findByAll(); 

    public void marcarComoDeletado(Long id);

    public void marcarComoDesativado(Long id);
}
