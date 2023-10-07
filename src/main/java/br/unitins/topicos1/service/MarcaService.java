package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaInsertDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;

public interface MarcaService {
    
    public MarcaResponseDTO insert(MarcaInsertDTO dto);

    public MarcaResponseDTO update(MarcaInsertDTO dto, Long id);

    public void delete(Long id);

    public MarcaResponseDTO findById(Long id);

    public List<MarcaResponseDTO> findByNome(String nome);

    public List<MarcaResponseDTO> findByAll(); 
}
