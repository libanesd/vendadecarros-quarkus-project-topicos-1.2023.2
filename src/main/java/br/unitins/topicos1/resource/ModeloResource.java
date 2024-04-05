package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.ModeloDTORepository.ModeloInsertDTO;
import br.unitins.topicos1.service.ModeloService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/modelos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModeloResource {
    
    @Inject
    ModeloService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    //@RolesAllowed("Admin")
    @Path("/insert")
    public Response insert(ModeloInsertDTO dto) {
        LOG.infof("Iniciando o processo de inserçao da modelo %s", dto.nomeModelo());
        LOG.infof("com a lista de carros: %s", dto.carro());
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(ModeloInsertDTO dto, @PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de update da modelo %s", dto.nomeModelo());
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete da modelo");
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    //@RolesAllowed({"User","Admin"})
    public Response findAll() {
        LOG.infof("Buscando todas as modelos");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando por id modelo");
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("Buscando por nome modelo");
        return Response.ok(service.findByNome(nome)).build();
    }
}
