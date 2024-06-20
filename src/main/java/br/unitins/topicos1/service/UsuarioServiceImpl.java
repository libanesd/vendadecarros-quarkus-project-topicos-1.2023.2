package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertUserDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioSemSenhaDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioUpdateDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaIdDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.TipoDeUsuario;
import br.unitins.topicos1.model.MovimentacaoFinanceira;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.repository.OfertaRepository;
import br.unitins.topicos1.repository.VendaRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    UsuarioRepository repository;

    @Inject
    OfertaRepository ofertaRepository;

    @Inject
    VendaRepository vendaRepository;

    @Inject
    HashService hashService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);


    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioInsertDTO dto) {

        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario novoUsuario = Usuario.valueOfUsuarioInsertDTO(dto); 
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        List<Oferta> oferta = new ArrayList<Oferta>();
        List<MovimentacaoFinanceira> vendas = new ArrayList<MovimentacaoFinanceira>();
        novoUsuario.setOfertas(oferta);
        novoUsuario.setVendas(vendas);
        novoUsuario.setDeletado(false);
        novoUsuario.setDesativado(false);
        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioUpdateDTO dto, Long id) {
        Usuario novoUsuario = repository.findById(id);
        if(novoUsuario != null){
            if(dto.nome() != null){
                    novoUsuario.setNome(dto.nome());
            }
            if (dto.ofertas() != null && !dto.ofertas().isEmpty()){
                if(novoUsuario.getOfertas() == null || novoUsuario.getOfertas().isEmpty()){
                    novoUsuario.setOfertas(new ArrayList<Oferta>());
                }
                for (OfertaIdDTO ofe : dto.ofertas()) {
                    Oferta oferta = ofertaRepository.findById(ofe.id());
                    if(oferta.getUsuarios() == null || oferta.getUsuarios().isEmpty()){
                        oferta.setUsuarios(new ArrayList<Usuario>());
                    }
                    oferta.getUsuarios().add(novoUsuario);
                    ofertaRepository.persist(oferta);
                    novoUsuario.getOfertas().add(oferta);
                }
            }
            if (dto.vendas() != null && !dto.vendas().isEmpty()){
                if(novoUsuario.getVendas() == null || novoUsuario.getVendas().isEmpty()){
                    novoUsuario.setVendas(new ArrayList<MovimentacaoFinanceira>());
                }
                for (VendaIdDTO ven : dto.vendas()) {
                    MovimentacaoFinanceira venda = vendaRepository.findById(ven.id());
                    if(venda.getUsuario() == null){
                        venda.setUsuario(novoUsuario);
                    }
                    vendaRepository.persist(venda);
                    novoUsuario.getVendas().add(venda);
                }
            }
            repository.persist(novoUsuario);
            
            return UsuarioResponseDTO.valueOf(novoUsuario);
        }else{
            throw new ValidationException("Não é encontrado usuario", "não é encontrado usuario em UsuarioServiceImpl");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) { 
        repository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario novoUsuario = repository.findById(id);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.findAllEnable().stream()
            .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioJwtDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = repository.findByLoginAndSenha(login, senha);
        if (usuario == null) 
            throw new ValidationException("login", "Login ou senha inválido");

        return UsuarioJwtDTO.valueOf(usuario);
    }

    @Override
    public UsuarioJwtDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null) 
            throw new ValidationException("login", "Login inválido");

        return UsuarioJwtDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO insertUser(@Valid UsuarioInsertUserDTO dto) {
        // TODO Auto-generated method stub
        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }
        
        Usuario novoUsuario = Usuario.valueOfUsuarioInsertUserDTO(dto); 
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        List<Oferta> oferta = new ArrayList<Oferta>();
        List<MovimentacaoFinanceira> vendas = new ArrayList<MovimentacaoFinanceira>();
        novoUsuario.setOfertas(oferta);
        novoUsuario.setVendas(vendas);
        novoUsuario.setDeletado(false);
        novoUsuario.setDesativado(false);

        TipoDeUsuario tipoUsuario = TipoDeUsuario.USER;

        novoUsuario.setTipodeusuario(tipoUsuario);

        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public LoginDTO updateSenhaUsuarioLogado(String login,String senha) {
        // TODO Auto-generated method stub

        LOG.infof("Iniciando a autenticacao do %s", login);
        Usuario novoUsuario = repository.findByLogin(login);

        LOG.infof("Iniciando a autenticacao do usuario com as credenciais: %s", novoUsuario.getLogin());
        LOG.infof("Iniciando a autenticacao do usuario com as credenciais: %s", novoUsuario.getSenha());

        novoUsuario.setSenha(hashService.getHashSenha(senha));

        LOG.infof("update de Hash da senha gerado.",novoUsuario.getSenha());

        repository.persist(novoUsuario);

        LoginDTO updatelogin = new LoginDTO(novoUsuario.getLogin(), novoUsuario.getSenha());
        return updatelogin;
    }

    @Override
    @Transactional
    public UsuarioSemSenhaDTO updateUsuario(String login,UsuarioSemSenhaDTO dto) {
        // TODO Auto-generated method stub
        LOG.infof("Iniciando a update do %s", login);
        Usuario novoUsuario = repository.findByLogin(login);

        LOG.infof("Iniciando a autenticacao do usuario com as credenciais: %s", novoUsuario.getLogin());
        if(dto.cpf() != null && !dto.cpf().isEmpty()){
            novoUsuario.setCpf(dto.cpf());
        }
        if(dto.nome() != null && !dto.nome().isEmpty()){
            novoUsuario.setNome(dto.nome());
        }
        if(dto.login() != null && !dto.login().isEmpty()){
            novoUsuario.setLogin(dto.login());
        }
        if(dto.telefone() != null && !dto.telefone().isEmpty()){
            novoUsuario.setTelefone(dto.telefone());
        }
        if(dto.email() != null && !dto.email().isEmpty()){
            novoUsuario.setEmail(dto.email());
        }
        if(dto.endereco() != null && !dto.endereco().isEmpty()){
            novoUsuario.setEndereco(dto.endereco());
        }

        repository.persist(novoUsuario);
        dto = UsuarioSemSenhaDTO.valueOf(novoUsuario);
        LOG.info("processo de update do usuario concluido");

        return dto;

    }

    @Override
    public void marcarComoDeletado(Long id){
        Usuario novoUsuario = repository.findById(id);
        novoUsuario.setDeletado(true);
        repository.persist(novoUsuario);
    }
    @Override
    public void marcarComoDesativado(Long id){
        Usuario novoUsuario = repository.findById(id);
        novoUsuario.setDesativado(true);
        repository.persist(novoUsuario);
    }
    
}
