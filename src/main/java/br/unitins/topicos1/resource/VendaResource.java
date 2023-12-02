package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.service.VendaService;
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

@Path("/vendas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendaResource {

    @Inject
    VendaService service;
    
    @POST
    @Transactional
    @Path("/venda-add")
    @RolesAllowed({"User","Admin"})
    public Response insert(VendaInsertDTO dto) {
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response update(VendaInsertDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.noContent().build();
    }

    @GET
    @Transactional
    //@RolesAllowed({"User","Admin"})
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"User","Admin"})
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @Transactional
    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }
}
