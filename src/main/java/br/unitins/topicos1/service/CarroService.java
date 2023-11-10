package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroEstoqueDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;

public interface CarroService {

    public CarroResponseDTO insert(CarroDTO dto);

    public CarroResponseDTO update(CarroDTO dto, Long id);

    public void delete(Long id);

    public CarroResponseDTO findById(Long id);

    public List<CarroResponseDTO> findByNome(String nome);

    public List<CarroResponseDTO> findByAll(); 

    public List<CarroEstoqueDTO> findByAllEstoque(); 
}
