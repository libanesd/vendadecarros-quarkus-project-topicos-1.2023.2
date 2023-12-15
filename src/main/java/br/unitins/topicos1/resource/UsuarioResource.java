package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertUserDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioUpdateDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    
    @POST
    @Transactional
    @Path("/usuario-add")
    //@RolesAllowed({"Admin"})
    public Response insert(UsuarioInsertDTO dto) {
        LOG.infof("Iniciando o processo de inserçao do usuario %s", dto.nome());
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @POST
    @Transactional
    @Path("/cadastro")
    public Response insertUser(@Valid UsuarioInsertUserDTO dto) {
        LOG.infof("Iniciando o processo de inserçao do usuario do tipo usuario%s", dto.nome());
        return Response.status(Status.CREATED).entity(service.insertUser(dto)).build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response update(UsuarioUpdateDTO dto, @PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de update do usuario %s", dto.nome());
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/usuario/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete do usuario");
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Transactional
    @RolesAllowed({"Admin"})
    public Response findAll() {
        LOG.infof("buscando todos os usuarios");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("buscando usuario por id usuario");
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("buscando usuario por nome usuario %s");
        return Response.ok(service.findByNome(nome)).build();
    }
    
}
