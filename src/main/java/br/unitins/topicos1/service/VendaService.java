package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;

public interface VendaService {
 
    public VendaResponseDTO insert(VendaInsertDTO dto);

    public VendaResponseDTO update(VendaDTO dto, Long id);

    public void delete(Long id);

    public VendaResponseDTO findById(Long id);

    public List<VendaResponseDTO> findByNome(String nome);

    public List<VendaResponseDTO> findByAll(); 
}