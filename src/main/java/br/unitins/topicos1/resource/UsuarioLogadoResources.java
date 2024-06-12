package br.unitins.topicos1.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioSemSenhaDTO;
import br.unitins.topicos1.form.CarroImagemForm;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.service.CarroFileServiceImpl;
import br.unitins.topicos1.service.CarroService;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.application.Error;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResources {
    
    @Inject
    JsonWebToken jwt;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    CarroService carroService;

    @Inject
    CarroFileServiceImpl fileService;

    @Inject
    UsuarioRepository repository;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);


    @GET
    @RolesAllowed({"User","Admin"})
    public Response getUsuario() {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        return Response.ok(usuarioService.findByLogin(login)).build();

    }

    @PUT
    @Path("/update-senha")
    @RolesAllowed({"User","Admin"})
    public Response getUpdateUserAdminSenha(String senha) {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        LOG.infof("Alteração para a seha a autenticacao do %s", senha);

        LoginDTO updatelogin = usuarioService.updateSenhaUsuarioLogado(login, senha);

        return Response.ok(updatelogin).build();

    }

    @PUT
    @Path("/update-usuario")
    @RolesAllowed({"User","Admin"})
    public Response UpdateUsuario( UsuarioSemSenhaDTO dto) {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        LOG.info("Alteração de usuario %s");

        UsuarioSemSenhaDTO updatelogin = usuarioService.updateUsuario(login, dto);

        return Response.ok(updatelogin).build();

    }

    @PATCH
    @Path("/upload/imagem/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm CarroImagemForm form, @PathParam("id") Long id){
        String nomeImagem;
        try {
            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }
        CarroResponseDTO carroResponseDTO = carroService.findById(id);
        carroResponseDTO = carroService.updateNomeImagem(carroResponseDTO.id(), nomeImagem);

        return Response.ok(carroResponseDTO).build();

    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
    }

   
}
