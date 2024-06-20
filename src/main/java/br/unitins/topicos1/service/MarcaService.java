package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTORepository.MarcaInsertDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaResponseDTO;
import jakarta.validation.Valid;

public interface MarcaService {
    
    public MarcaResponseDTO insert(@Valid MarcaInsertDTO dto);

    public MarcaResponseDTO update(MarcaInsertDTO dto, Long id);

    public void delete(Long id);

    public MarcaResponseDTO findById(Long id);

    public List<MarcaResponseDTO> findByNome(String nome);

    public List<MarcaResponseDTO> findByAll(); 

    public void marcarComoDeletado(Long id);

    public void marcarComoDesativado(Long id);
}
