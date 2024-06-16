package br.unitins.topicos1.resource;
import br.unitins.topicos1.application.Email;
import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.AuthDTORepository.ValidarCodigo;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;
import br.unitins.topicos1.model.EsqueceuSenha;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.EsqueceuSenhaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.service.EmailService;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.JwtService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService service;

    @Inject
    UsuarioRepository usuarioRepository;
    
    @Inject
    EsqueceuSenhaRepository esqueceuSenhaRepository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Inject
    EmailService emailService;
    

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response login(@Valid LoginDTO dto) {

        LOG.infof("Iniciando a autenticacao do %s", dto.login());
        
        String hashSenha = hashService.getHashSenha(dto.senha());

        LOG.info("Hash da senha gerado.");

        LOG.debug(hashSenha);

        UsuarioJwtDTO result = service.findByLoginAndSenha(dto.login(), hashSenha);
        
        if (result == null) {
            LOG.info("Login e senha incorretos.");
            return Response.status(Status.NOT_FOUND)
                .entity("Usuario não encontrado").build();
        }
        LOG.info("Login e senha corretos.");
        LOG.info("Finalizando o processo de login.");
        Response res = Response.ok()
        .header("Authorization", jwtService.generateJwt(result))
        .build();

        LOG.info("Esse é o response"+ res.getHeaders());
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(result))
            .entity(result)
            .build();
    }

    public Response loginInterno(@Valid LoginDTO dto) {

        UsuarioJwtDTO result = service.findByLoginAndSenha(dto.login(), dto.senha());
        
        if (result == null) {
            LOG.info("Login e senha incorretos.");
            return Response.status(Status.NOT_FOUND)
                .entity("Usuario não encontrado").build();
        }
        LOG.info("Login e senha corretos.");
        LOG.info("Finalizando o processo de login.");
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(result))
            .build();
    }

    @PUT
    @Path("/update-senha")
    @RolesAllowed({"User","Admin"})
    public Response getUpdateUserAdminSenha(String senha) {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        LOG.infof("Alteração para a seha a autenticacao do %s", senha);

        LoginDTO updatelogin = service.updateSenhaUsuarioLogado(login, senha);

        return Response.ok(updatelogin).build();

    }

    @POST
    @Path("/validar-codigo")
    @Transactional
    public Response validarCodigo(@Valid ValidarCodigo validarCodigo) {

        LOG.infof("inicialização do codigo: "+validarCodigo.codigo());
        EsqueceuSenha esqueceuSenha = esqueceuSenhaRepository.findCodigo(validarCodigo.codigo());
        if(esqueceuSenha == null){
            LOG.infof("Não foi encontrado Codigo %s");
            return Response.status(Status.NOT_FOUND)
                .entity("Não foi encontrado Codigo").build();
        }
        if(esqueceuSenha.isUtilizado()){
            LOG.infof("Codigo já utilizado %s");
            return Response.status(Status.NOT_FOUND)
                .entity("Codigo já utilizado").build();
        }
        Usuario usuario = usuarioRepository.findById(esqueceuSenha.getUsuario().getId());
        if(usuario == null){
            LOG.infof("Usuario não encontrado %s");
            return Response.status(Status.NOT_FOUND)
                .entity("Codigo não encontrado").build();
        }

        esqueceuSenha.setUtilizado(true);
        esqueceuSenhaRepository.persist(esqueceuSenha);

        usuario.setSenha(hashService.getHashSenha(validarCodigo.senha()));
        usuarioRepository.persist(usuario);
        LoginDTO login = new LoginDTO(esqueceuSenha.getUsuario().getLogin(), esqueceuSenha.getUsuario().getSenha());
        return loginInterno(login);
    }

    @POST
    @Path("/gerar-codigo")
    @Transactional
    public Response gerarCodigo(String emailDigitado) {

        LOG.infof(emailDigitado);
        Usuario recuperarUsuario = usuarioRepository.findByLogin(emailDigitado.replaceAll("\"", ""));
        if(recuperarUsuario == null){
            LOG.infof("Não foi encontrado usuario com esse e-mail %s");
            return Response.status(Status.NOT_FOUND)
                .entity("Usuario não encontrado").build();
        }

        Random random = new Random();
		random.nextInt(1000000);
		String codigo = new DecimalFormat("T-000000").format(random.nextInt(100000));

        EsqueceuSenha esqueceu = new EsqueceuSenha();
		esqueceu.setUsuario(recuperarUsuario);
		esqueceu.setUtilizado(false);
		esqueceu.setCodigo(codigo);
		esqueceu.setDataHoraLimite(LocalDateTime.now().plusDays(1));
        esqueceuSenhaRepository.persist(esqueceu);

        Email email = new Email(recuperarUsuario.getLogin(), 
				"Esqueceu a senha", 
				"Segue o código de recuperar a senha: " +codigo);

		if (!email.enviar(emailDigitado,codigo)) {
			LOG.infof("problema ao enviar email %s");
            return Response.status(Status.NOT_FOUND)
                .entity("problema ao enviar email").build();
		} else{
            LOG.infof("Código enviado para seu email. %s");
            return Response.ok().build();
        }
    }

}