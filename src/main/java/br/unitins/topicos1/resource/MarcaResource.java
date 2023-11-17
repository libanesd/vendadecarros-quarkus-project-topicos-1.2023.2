package br.unitins.topicos1.resource;
import java.util.List;

import br.unitins.topicos1.dto.MarcaDTORepository.MarcaInsertDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaResponseDTO;
import br.unitins.topicos1.service.MarcaService;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {
    
    @Inject
    MarcaService service;
    
    @POST
    @RolesAllowed("Admin")
    public MarcaResponseDTO insert(MarcaInsertDTO dto) {
        return service.insert(dto);
    }


    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public MarcaResponseDTO update(MarcaInsertDTO dto, @PathParam("id") Long id) {
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    //@RolesAllowed({"User","Admin"})
    public List<MarcaResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User","Admin"})
    public MarcaResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"User","Admin"})
    public List<MarcaResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }
}
