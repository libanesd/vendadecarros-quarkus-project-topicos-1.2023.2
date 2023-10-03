package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.CarroResponseDTO;

public interface CarroService {

    public CarroResponseDTO insert(CarroDTO dto);

    public CarroResponseDTO update(CarroDTO dto, Long id);

    public void delete(Long id);

    public CarroResponseDTO findById(Long id);

    public List<CarroResponseDTO> findByNome(String nome);

    public List<CarroResponseDTO> findByAll(); 
}
