package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaIdDTO;
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaInsertDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioIdDTO;
import br.unitins.topicos1.model.Oferta;
import br.unitins.topicos1.model.StatusOferta;
import br.unitins.topicos1.repository.OfertaRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.resource.OfertaResource;

@QuarkusTest
public class OfertaResourceTest {
    
    @Inject
    OfertaResource resource;
    @Inject
    OfertaRepository repository;

    @Inject
    AuthResource authResource;

    @Test
    public void testInsert() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        List<CategoriaIdDTO> categorias  = new ArrayList<CategoriaIdDTO>();
        List<UsuarioIdDTO> usuarios  = new ArrayList<UsuarioIdDTO>();
        OfertaInsertDTO oferta = new OfertaInsertDTO("Oferta De Carros 1", carros, categorias, usuarios, StatusOferta.ATIVA, 20f);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );

        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(oferta)
            .when().post("/ofertas/oferta-add")
            .then()
            .statusCode(201);

        List<Oferta> ofertaResult = repository.findByNome("Oferta De Carros 1");
        System.out.println(ofertaResult.size());
        System.out.println(ofertaResult.get(0).getNome());
    }

    @Test
    public void testUpdate() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        List<CategoriaIdDTO> categorias  = new ArrayList<CategoriaIdDTO>();
        List<UsuarioIdDTO> usuarios  = new ArrayList<UsuarioIdDTO>();
        OfertaInsertDTO oferta = new OfertaInsertDTO("Oferta De Carros Teste Update", carros, categorias, usuarios, StatusOferta.ATIVA, 20f);


        List<CarroIdDTO> carrosUpdate  = new ArrayList<CarroIdDTO>();
        List<CategoriaIdDTO> categoriasUpdate  = new ArrayList<CategoriaIdDTO>();
        List<UsuarioIdDTO> usuariosUpdate  = new ArrayList<UsuarioIdDTO>();
        OfertaInsertDTO ofertaUpdate = new OfertaInsertDTO("Oferta De Carros Update que levou update", carrosUpdate, categoriasUpdate, usuariosUpdate, StatusOferta.ATIVA, 30f);


        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(oferta)
            .when().post("/ofertas/oferta-add")
            .getBody();
        
        Oferta ofertadojson = new Oferta();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            ofertadojson.setId(jsonNode.path("id").asLong());
            ofertadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(ofertadojson.getId());
        System.out.println(ofertadojson.getNome());

        given()
            .auth().oauth2(token).pathParam("id", ofertadojson.getId())
            .contentType(ContentType.JSON)
            .body(ofertaUpdate)
            .when().put("/ofertas/{id}")
            .then()
            .statusCode(204);

        Oferta ofertaResult = repository.findById(ofertadojson.getId());
        System.out.println(ofertaResult.getNome());
    }

    @Test
    public void testDelete() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        List<CategoriaIdDTO> categorias  = new ArrayList<CategoriaIdDTO>();
        List<UsuarioIdDTO> usuarios  = new ArrayList<UsuarioIdDTO>();
        OfertaInsertDTO oferta = new OfertaInsertDTO("Oferta De Carros Teste Update", carros, categorias, usuarios, StatusOferta.ATIVA, 20f);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(oferta)
            .when().post("/ofertas/oferta-add")
            .getBody();
        
        Oferta ofertadojson = new Oferta();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            ofertadojson.setId(jsonNode.path("id").asLong());
            ofertadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(ofertadojson.getId());
        System.out.println(ofertadojson.getNome());

        given()
            .auth().oauth2(token).pathParam("id", ofertadojson.getId())
            .contentType(ContentType.JSON)
            .when().delete("/ofertas/delete/{id}")
            .then()
            .statusCode(204);

        Oferta ofertaResult = repository.findById(ofertadojson.getId());
        if(ofertaResult == null){
            System.out.println("Foi deletado com sucesso");
        }else{
            System.out.println(ofertaResult.getNome());
        }
    }

    @Test
    public void testFindAll() {
        given()
          .when().get("/ofertas")
          .then()
             .statusCode(200);
    }

    @Test
    public void testFindById() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        List<CategoriaIdDTO> categorias  = new ArrayList<CategoriaIdDTO>();
        List<UsuarioIdDTO> usuarios  = new ArrayList<UsuarioIdDTO>();
        OfertaInsertDTO oferta = new OfertaInsertDTO("Oferta De Carros para pesquisar id", carros, categorias, usuarios, StatusOferta.ATIVA, 20f);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(oferta)
            .when().post("/ofertas/oferta-add")
            .getBody();
        
        Oferta ofertadojson = new Oferta();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            ofertadojson.setId(jsonNode.path("id").asLong());
            ofertadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(ofertadojson.getId());
        System.out.println(ofertadojson.getNome());

        ResponseBody responseResultOferta =given()
            .auth().oauth2(token).pathParam("id", ofertadojson.getId())
            .contentType(ContentType.JSON)
            .when().get("/ofertas/{id}")
            .getBody();

        if(responseResultOferta != null){
            System.out.println(responseResultOferta.asString());
        }
    }

    @Test
    public void testFindByNome() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        List<CategoriaIdDTO> categorias  = new ArrayList<CategoriaIdDTO>();
        List<UsuarioIdDTO> usuarios  = new ArrayList<UsuarioIdDTO>();
        OfertaInsertDTO oferta = new OfertaInsertDTO("Oferta De Carros para pesquisar nome", carros, categorias, usuarios, StatusOferta.ATIVA, 20f);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(oferta)
            .when().post("/ofertas/oferta-add")
            .getBody();
        
        Oferta ofertadojson = new Oferta();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            ofertadojson.setId(jsonNode.path("id").asLong());
            ofertadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(ofertadojson.getId());
        System.out.println(ofertadojson.getNome());

        ResponseBody responseResultOferta =given()
            .auth().oauth2(token).pathParam("nome", ofertadojson.getNome())
            .contentType(ContentType.JSON)
            .when().get("/ofertas/search/nome/{nome}")
            .getBody();

        if(responseResultOferta != null){
            System.out.println(responseResultOferta.asString());
        }
    }

}
