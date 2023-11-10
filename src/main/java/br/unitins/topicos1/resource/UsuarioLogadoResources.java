package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.service.ClienteService;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResources {
    
    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService clienteService;

    @GET
    @RolesAllowed({"User","Admin"})
    public Response getUsuario() {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        return Response.ok(clienteService.findByLogin(login)).build();

    }

    @GET
    @RolesAllowed({"User","Admin"})
    @Path("/{auth}")
    public Response getUsuarioLogado2(@PathParam("auth") String auth) {

        io.jsonwebtoken.Claims claims = Jwts.parser()
                    .parseClaimsJwt(auth)
                    .getBody();

            // Itera sobre todas as reivindicações disponíveis
        for (String key : claims.keySet()) {
            System.out.println(key + ": " + claims.get(key));
        }

        // Obtém o login do token
        String login = claims.getSubject();

        // obtendo o login pelo token jwt
        //String login = jwt.getSubject();

        return Response.ok(clienteService.findByLogin(login)).build();

    }
}
