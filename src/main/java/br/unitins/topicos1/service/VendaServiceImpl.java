package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unitins.topicos1.dto.VendaDTORepository.CompraUserDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaResponseDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDeMovimentacaoFinanceira;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.MovimentacaoFinanceira;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.repository.OfertaRepository;
import br.unitins.topicos1.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    @Inject
    VendaRepository repository;
    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    CarroRepository carroRepository;
    @Inject
    OfertaRepository ofertaRepository;

    @Override
    @Transactional
    public VendaResponseDTO insert(@Valid VendaInsertDTO dto) {
        // TODO Auto-generated method stub
        MovimentacaoFinanceira novaVenda = MovimentacaoFinanceira.valueOfVendaInsertDTO(dto);
        Carro carro = carroRepository.findById(dto.carro().id());
        Usuario usuario = usuarioRepository.findById(dto.usuario().id());
        novaVenda.setCarro(carro);
        novaVenda.setUsuario(usuario);
        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    @Transactional
    public VendaResponseDTO update(VendaInsertDTO dto, Long id) {
        MovimentacaoFinanceira novaVenda = repository.findById(id);
        novaVenda = MovimentacaoFinanceira.valueOfVendaInsertDTO(dto);
        novaVenda.setId(id);
        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    public VendaResponseDTO findById(Long id) {
       MovimentacaoFinanceira novaVenda = repository.findById(id);
        return VendaResponseDTO.valueOf(novaVenda);
    }

    @Override
    public List<VendaResponseDTO> findByNome(String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNome(nome);
        List<VendaResponseDTO> listaDeVendas = new ArrayList<VendaResponseDTO>();

        for (MovimentacaoFinanceira venda: usuarios.get(0).getVendas()) {
            MovimentacaoFinanceira vendaAux = new MovimentacaoFinanceira();
            vendaAux = repository.findById(venda.getId());
            listaDeVendas.add(VendaResponseDTO.valueOf(vendaAux));
        }

        return listaDeVendas;
    }

    @Override
    public List<VendaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public VendaResponseDTO insertCompraUser(CompraUserDTO dto,String login) {
        // TODO Auto-generated method stub

        Usuario usuario = usuarioRepository.findByLogin(login);
        MovimentacaoFinanceira novaVenda = new MovimentacaoFinanceira();
        Carro carro = carroRepository.findById(dto.carro());
        
        novaVenda.setDataDeCompra(new Date());
        novaVenda.setCarro(carro);
        novaVenda.setPrecoDaCompra(carro.getPreco());
        novaVenda.setDescricao("compra do carro" + carro.getNomeCarro());
        novaVenda.setTipoDePagamento(dto.tipoDePagamento());
        novaVenda.setStatusVenda(StatusVenda.AGUARDANDOPAGAMENTO);
        novaVenda.setTipoDeMovimentacaoFinanceira(TipoDeMovimentacaoFinanceira.VENDA);
        novaVenda.setUsuario(usuario);

        repository.persist(novaVenda);
        return VendaResponseDTO.valueOf(novaVenda);
    }
    
}
