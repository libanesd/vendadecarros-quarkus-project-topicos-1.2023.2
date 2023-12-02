package br.unitins.topicos1.resource;
import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.JwtService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

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
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(result))
            .build();
    }


}