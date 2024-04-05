package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ModeloDTORepository.ModeloInsertDTO;
import br.unitins.topicos1.dto.ModeloDTORepository.ModeloResponseDTO;
import jakarta.validation.Valid;

public interface ModeloService {

    public ModeloResponseDTO insert(@Valid ModeloInsertDTO dto);

    public ModeloResponseDTO update(ModeloInsertDTO dto, Long id);

    public void delete(Long id);

    public ModeloResponseDTO findById(Long id);

    public List<ModeloResponseDTO> findByNome(String nome);

    public List<ModeloResponseDTO> findByAll(); 
}

