package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaInsertDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.CategoriaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.repository.OfertaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class OfertaServiceImpl implements  OfertaService{

    @Inject
    OfertaRepository repository;
    @Inject
    CarroRepository carroRepository;
    @Inject
    CategoriaRepository categoriaRepository;
    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public OfertaResponseDTO insert(@Valid OfertaInsertDTO dto) {
        Oferta novaOferta = Oferta.valueOfOfertaInsertDTO(dto);

        if(dto.nome() != null){
             novaOferta.setNome(dto.nome());
        }

        if(novaOferta.getCarros()!= null && !novaOferta.getCarros().isEmpty()){
            List<Carro> carroList = new ArrayList<Carro>();
            for (Carro car : novaOferta.getCarros()) {
                Carro carroDb = carroRepository.findById(car.getId());
                if(carroDb.getOfertas() == null || carroDb.getOfertas().isEmpty()){
                    carroDb.setOfertas(new ArrayList<Oferta>());
                }
                carroDb.getOfertas().add(novaOferta);
                carroRepository.persist(carroDb);
                carroList.add(carroDb);
            }
            novaOferta.setCarros(carroList);
        }else{
            List<Carro> carroList = new ArrayList<Carro>();
            novaOferta.setCarros(carroList);
        }

        if(novaOferta.getCategorias()!= null && !novaOferta.getCategorias().isEmpty()){
            List<Categoria> categoriaList = new ArrayList<Categoria>();
            for (Categoria cat : novaOferta.getCategorias()) {
                Categoria categoriaDb = categoriaRepository.findById(cat.getId());
                if(categoriaDb.getOfertas() == null || categoriaDb.getOfertas().isEmpty()){
                    categoriaDb.setOfertas(new ArrayList<Oferta>());
                }
                categoriaDb.getOfertas().add(novaOferta);
                categoriaRepository.persist(categoriaDb);
                categoriaList.add(categoriaDb);
            }
            novaOferta.setCategorias(categoriaList);
        }else{
            List<Categoria> categoriaList = new ArrayList<Categoria>();
            novaOferta.setCategorias(categoriaList);
        }

        if(novaOferta.getCategorias()!= null && !novaOferta.getCategorias().isEmpty()){
            List<Usuario> usuarios = new ArrayList<Usuario>();
            for (Usuario user : novaOferta.getUsuarios()) {
                Usuario usuarioDb = usuarioRepository.findById(user.getId());
                if(usuarioDb.getOfertas() == null || usuarioDb.getOfertas().isEmpty()){
                    usuarioDb.setOfertas(new ArrayList<Oferta>());
                }
                usuarioDb.getOfertas().add(novaOferta);
                usuarioRepository.persist(usuarioDb);
                usuarios.add(usuarioDb);
            }
            novaOferta.setUsuarios(usuarios);
        }else{
            List<Usuario> usuarios = new ArrayList<Usuario>();
            novaOferta.setUsuarios(usuarios);
        }
        novaOferta.setDeletado(false);
        novaOferta.setDesativado(false);
        repository.persist(novaOferta);
        return OfertaResponseDTO.valueOf(novaOferta);
    }

    @Override
    @Transactional
    public OfertaResponseDTO update(OfertaInsertDTO dto, Long id) {
        Oferta oferta = repository.findById(id);
        if(dto.nome() != null){
             oferta.setNome(dto.nome());
        }
        if(oferta.getCarros()!= null && !oferta.getCarros().isEmpty()){
            List<Carro> carroList = new ArrayList<Carro>();
            for (Carro car : oferta.getCarros()) {
                Carro carroDb = carroRepository.findById(car.getId());
                if(carroDb.getOfertas() == null || carroDb.getOfertas().isEmpty()){
                    carroDb.setOfertas(new ArrayList<Oferta>());
                }
                carroDb.getOfertas().add(oferta);
                carroRepository.persist(carroDb);
                carroList.add(carroDb);
            }
            oferta.setCarros(carroList);
        }

        if(oferta.getCategorias()!= null && !oferta.getCategorias().isEmpty()){
            List<Categoria> categoriaList = new ArrayList<Categoria>();
            for (Categoria cat : oferta.getCategorias()) {
                Categoria categoriaDb = categoriaRepository.findById(cat.getId());
                if(categoriaDb.getOfertas() == null || categoriaDb.getOfertas().isEmpty()){
                    categoriaDb.setOfertas(new ArrayList<Oferta>());
                }
                categoriaDb.getOfertas().add(oferta);
                categoriaRepository.persist(categoriaDb);
                categoriaList.add(categoriaDb);
            }
            oferta.setCategorias(categoriaList);
        }

        if(oferta.getCategorias()!= null && !oferta.getCategorias().isEmpty()){
            List<Usuario> usuarios = new ArrayList<Usuario>();
            for (Usuario user : oferta.getUsuarios()) {
                Usuario usuarioDb = usuarioRepository.findById(user.getId());
                if(usuarioDb.getOfertas() == null || usuarioDb.getOfertas().isEmpty()){
                    usuarioDb.setOfertas(new ArrayList<Oferta>());
                }
                usuarioDb.getOfertas().add(oferta);
                usuarioRepository.persist(usuarioDb);
                usuarios.add(usuarioDb);
            }
            oferta.setUsuarios(usuarios);
        }
        repository.persist(oferta);
        return OfertaResponseDTO.valueOf(oferta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Oferta oferta = repository.findById(id);
        repository.delete(oferta);
    }

    @Override
    public OfertaResponseDTO findById(Long id) {
        Oferta oferta = repository.findById(id);
        return OfertaResponseDTO.valueOf(oferta);
    }

    @Override
    public List<OfertaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<OfertaResponseDTO> findByAll() {
        return repository.findAllEnable().stream()
            .map(e -> OfertaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public void marcarComoDeletado(Long id){
        Oferta oferta = repository.findById(id);
        oferta.setDeletado(true);
        repository.persist(oferta);
    }
    @Override
    public void marcarComoDesativado(Long id){
        Oferta oferta = repository.findById(id);
        oferta.setDesativado(true);
        repository.persist(oferta);
    }
}
