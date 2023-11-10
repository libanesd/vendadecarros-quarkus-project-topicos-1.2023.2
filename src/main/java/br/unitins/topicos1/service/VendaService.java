package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaResponseDTO;

public interface VendaService {
 
    public VendaResponseDTO insert(VendaInsertDTO dto);

    public VendaResponseDTO update(VendaInsertDTO dto, Long id);

    public void delete(Long id);

    public VendaResponseDTO findById(Long id);

    public List<VendaResponseDTO> findByNome(String nome);

    public List<VendaResponseDTO> findByAll(); 
}
