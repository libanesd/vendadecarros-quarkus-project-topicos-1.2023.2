package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTO;
import br.unitins.topicos1.dto.OfertaResponseDTO;

public interface OfertaService {

    public OfertaResponseDTO insert(OfertaDTO dto);

    public OfertaResponseDTO update(OfertaDTO dto, Long id);

    public void delete(Long id);

    public OfertaResponseDTO findById(Long id);

    public List<OfertaResponseDTO> findByNome(String nome);

    public List<OfertaResponseDTO> findByAll(); 
}