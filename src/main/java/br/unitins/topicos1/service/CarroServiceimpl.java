package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.CarroResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.repository.CarroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CarroServiceimpl implements CarroService{

    @Inject
    CarroRepository repository;

    @Override    
    @Transactional
    public CarroResponseDTO insert(CarroDTO dto) {
        Carro novoCarro = new Carro();
        novoCarro.setNomeCarro(dto.nomeCarro());
        novoCarro.setCarroSpec(dto.carroSpec());
        novoCarro.setVersao(dto.versao());
        novoCarro.setAno(dto.ano());
        novoCarro.setKilometragem(dto.kilometragem());
        novoCarro.setCaracteristicas(dto.caracteristicas());
        novoCarro.setTipoCombustivel(dto.tipoCombustivel());
        novoCarro.setTipoCambio(dto.tipoCambio());
        novoCarro.setCor(dto.cor());
        novoCarro.setPreco(dto.preco());
        novoCarro.setCidade(dto.cidade());
        novoCarro.setTipoCarroceria(dto.tipoCarroceria());
        
        repository.persist(novoCarro);
        return CarroResponseDTO.valueOf(novoCarro);
    }

    @Override
    public CarroResponseDTO update(CarroDTO dto, Long id) {
        Carro novoCarro = repository.findById(id);
        novoCarro.setNomeCarro(dto.nomeCarro());
        novoCarro.setCarroSpec(dto.carroSpec());
        novoCarro.setVersao(dto.versao());
        novoCarro.setAno(dto.ano());
        novoCarro.setKilometragem(dto.kilometragem());
        novoCarro.setCaracteristicas(dto.caracteristicas());
        novoCarro.setTipoCombustivel(dto.tipoCombustivel());
        novoCarro.setTipoCambio(dto.tipoCambio());
        novoCarro.setCor(dto.cor());
        novoCarro.setPreco(dto.preco());
        novoCarro.setCidade(dto.cidade());
        novoCarro.setTipoCarroceria(dto.tipoCarroceria());
        repository.persist(novoCarro);
        return CarroResponseDTO.valueOf(novoCarro);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CarroResponseDTO findById(Long id) {
        Carro carro = repository.findById(id);
        return CarroResponseDTO.valueOf(carro);
    }

    @Override
    public List<CarroResponseDTO> findByNome(String nome) {
        List<Carro> carros = repository.findByNome(nome);
        List<CarroResponseDTO> carroDTO = new ArrayList<CarroResponseDTO>();

        for (Carro carro : carros) {
            carroDTO.add(CarroResponseDTO.valueOf(carro));
        }
        return carroDTO;
    }

    @Override
    public List<CarroResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> CarroResponseDTO.valueOf(e)).toList();
    }
    
}
