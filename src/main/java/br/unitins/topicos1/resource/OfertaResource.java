package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.OfertaDTO;
import br.unitins.topicos1.dto.OfertaInsertDTO;
import br.unitins.topicos1.dto.OfertaResponseDTO;
import br.unitins.topicos1.service.OfertaService;
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

@Path("/ofertas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OfertaResource {

    @Inject
    OfertaService service;

     @POST
    public OfertaResponseDTO insert(OfertaInsertDTO dto) {
        return service.insert(dto);
    }


    @PUT
    @Transactional
    @Path("/{id}")
    public OfertaResponseDTO update(OfertaInsertDTO dto, @PathParam("id") Long id) {
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<OfertaResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public OfertaResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public List<OfertaResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }
    
}
