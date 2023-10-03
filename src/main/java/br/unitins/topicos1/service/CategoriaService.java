package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CategoriaDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;

public interface CategoriaService {
    
    public CategoriaResponseDTO insert(CategoriaDTO dto);

    public CategoriaResponseDTO update(CategoriaDTO dto, Long id);

    public void delete(Long id);

    public CategoriaResponseDTO findById(Long id);

    public List<CategoriaResponseDTO> findByNome(String nome);

    public List<CategoriaResponseDTO> findByAll(); 
    
}