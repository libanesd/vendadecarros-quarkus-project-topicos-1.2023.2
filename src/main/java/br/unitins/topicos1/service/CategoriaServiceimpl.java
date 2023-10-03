package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.CategoriaDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoriaServiceimpl implements CategoriaService{
    
    @Inject
    CategoriaRepository repository;

    @Override
    public CategoriaResponseDTO insert(CategoriaDTO dto) {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(dto.nome());
        if (dto.carros() != null && 
                    !dto.carros().isEmpty()){
            novaCategoria.setCarros(new ArrayList<Carro>());
            for (CarroDTO car : dto.carros()) {
                Carro carro = new Carro();
                carro.setNomeCarro(car.nomeCarro());
                carro.setCarroSpec(car.carroSpec());
                carro.setVersao(car.versao());
                carro.setAno(car.ano());
                carro.setKilometragem(car.kilometragem());
                carro.setTipoCombustivel(car.tipoCombustivel());
                carro.setTipoCambio(car.tipoCambio());
                carro.setCor(car.cor());
                carro.setCaracteristicas(car.caracteristicas());
                carro.setPreco(car.preco());
                carro.setCidade(car.cidade());
                carro.setTipoCarroceria(car.tipoCarroceria());

                novaCategoria.getCarros().add(carro);
            }
        }
        repository.persist(novaCategoria);
        return CategoriaResponseDTO.valueOf(novaCategoria);
    }

    @Override
    public CategoriaResponseDTO update(CategoriaDTO dto, Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public CategoriaResponseDTO findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<CategoriaResponseDTO> findByNome(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<CategoriaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }
    
}
