package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.VendaDTORepository.CompraUserDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaResponseDTO;
import jakarta.validation.Valid;

public interface VendaService {
 
    public VendaResponseDTO insert(@Valid VendaInsertDTO dto);

    public VendaResponseDTO insertCompraUser(CompraUserDTO dto,String login);

    public VendaResponseDTO update(VendaInsertDTO dto, Long id);

    public VendaResponseDTO findById(Long id);

    public List<VendaResponseDTO> findByNome(String nome);

    public List<VendaResponseDTO> findByAll(); 
}
