package br.unitins.topicos1.resource;
import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.ClienteDTORepository.ClienteJwtDTO;
import br.unitins.topicos1.service.ClienteService;
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


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    ClienteService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;


    @POST
    public Response login(@Valid LoginDTO dto) {

        
        String hashSenha = hashService.getHashSenha(dto.senha());

        ClienteJwtDTO result = service.findByLoginAndSenha(dto.login(), hashSenha);
        
        if (result == null) {
            return Response.status(Status.NOT_FOUND)
                .entity("Usuario não encontrado").build();
        } 
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(result))
            .build();
    }


}