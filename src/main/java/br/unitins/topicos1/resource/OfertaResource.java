package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import jakarta.ws.rs.core.Response.Status;

import br.unitins.topicos1.dto.OfertaDTORepository.OfertaInsertDTO;
import br.unitins.topicos1.service.OfertaService;
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

@Path("/ofertas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OfertaResource {

    @Inject
    OfertaService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Transactional
    @Path("/oferta-add")
    @RolesAllowed({"Admin"})
    public Response insert(OfertaInsertDTO dto) {
        LOG.infof("Iniciando o processo de inserçao da oferta %s", dto.nome());
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(OfertaInsertDTO dto, @PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de update da oferta %s", dto.nome());
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete da oferta");
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    //@RolesAllowed({"User","Admin"})
    public Response findAll() {
        LOG.infof("buscando todas as oferta");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("buscando por id oferta");
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("buscando por nome oferta");
        return Response.ok(service.findByNome(nome)).build();
    }
    
}
