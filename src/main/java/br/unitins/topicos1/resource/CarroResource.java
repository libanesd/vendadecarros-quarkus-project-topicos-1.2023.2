package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.service.CarroService;
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
import jakarta.validation.Valid;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroResource {

    @Inject
    CarroService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert")
    @RolesAllowed({"Admin"})
    public Response insert(@Valid CarroDTO dto) {
        LOG.infof("Iniciando o processo de inserçao do carro %s", dto.nomeCarro());
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response update(CarroDTO dto, @PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de update do carro %s", dto.nomeCarro());
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete do carro");
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    //@RolesAllowed({"User","Admin"})
    public Response findAll() {
        LOG.infof("buscando todos os carros");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/estoque")
    @RolesAllowed({"Admin"})
    public Response findAllEstoque() {
        LOG.infof("buscando todos os carros com estoque");
        return Response.ok(service.findByAllEstoque()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("procurando o carro pelo id");
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("procurando o carro pelo nome");
        return Response.ok(service.findByNome(nome)).build();
    }
    
}
