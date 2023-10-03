package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.OfertaDTO;
import br.unitins.topicos1.dto.OfertaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.repository.OfertaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OfertaServiceImpl implements  OfertaService{

    @Inject
    OfertaRepository repository;

    @Override
    @Transactional
    public OfertaResponseDTO insert(OfertaDTO dto) {
        Oferta novaOferta = new Oferta();
        novaOferta.setNome(dto.nome());
        if (dto.carros() != null && 
                    !dto.carros().isEmpty()){
            novaOferta.setCarros(new ArrayList<Carro>());
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

                novaOferta.getCarros().add(carro);
            }
        }
        repository.persist(novaOferta);
        return OfertaResponseDTO.valueOf(novaOferta);
    }

    @Override
    public OfertaResponseDTO update(OfertaDTO dto, Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public OfertaResponseDTO findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<OfertaResponseDTO> findByNome(String nome) {
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<OfertaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }
}
