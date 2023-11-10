package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaInsertDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaResponseDTO;

public interface CategoriaService {
    
    public CategoriaResponseDTO insert(CategoriaInsertDTO dto);

    public CategoriaResponseDTO update(CategoriaInsertDTO dto, Long id);

    public void delete(Long id);

    public CategoriaResponseDTO findById(Long id);

    public List<CategoriaResponseDTO> findByNome(String nome);

    public List<CategoriaResponseDTO> findByAll(); 
    
}
