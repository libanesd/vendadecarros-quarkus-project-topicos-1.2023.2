package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaInsertDTO;
import br.unitins.topicos1.service.CategoriaService;
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

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {
    
    @Inject
    CategoriaService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert")
    public Response insert(CategoriaInsertDTO dto) {
        LOG.infof("Iniciando o processo de inserçao de categoria", dto.nome());
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(CategoriaInsertDTO dto, @PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de update de categoria", dto.nome());
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete de categoria");
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    //@RolesAllowed({"User","Admin"})
    public Response findAll() {
        LOG.infof("buscando todas as categorias");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("buscando por id de categoria");
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("buscando por nome de categoria");
        return Response.ok(service.findByNome(nome)).build();
    }

    @PUT
    @Transactional
    @Path("/deletado/{id}")
    @RolesAllowed({"User","Admin"})
    public Response deletadoById(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete do usuario %s");
        service.marcarComoDeletado(id);
        return Response.noContent().build();
    }

    @PUT
    @Transactional
    @Path("/desativado/{id}")
    @RolesAllowed({"User","Admin"})
    public Response desativadoById(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de desativação do usuario %s");
        service.marcarComoDesativado(id);
        return Response.noContent().build();
    }
}
