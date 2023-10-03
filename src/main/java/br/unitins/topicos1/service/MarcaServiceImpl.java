package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.repository.OfertaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImpl implements  MarcaService{
        @Inject
    OfertaRepository repository;

    @Override
    @Transactional
    public MarcaResponseDTO insert(MarcaDTO dto) {
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
        return MarcaResponseDTO.valueOf(novaOferta);
    }

    @Override
    public MarcaResponseDTO update(MarcaDTO dto, Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public MarcaResponseDTO findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<MarcaResponseDTO> findByNome(String nome) {
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<MarcaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }
}
