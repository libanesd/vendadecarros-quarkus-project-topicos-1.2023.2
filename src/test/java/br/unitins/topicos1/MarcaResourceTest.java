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
import br.unitins.topicos1.dto.CategoriaDTORepository.CategoriaInsertDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaInsertDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.resource.MarcaResource;

@QuarkusTest
public class MarcaResourceTest {

    @Inject
    MarcaResource resource;

    @Inject
    MarcaRepository repository;

    @Inject
    AuthResource authResource;
    
        @Test
    public void testInsert() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        MarcaInsertDTO marca = new MarcaInsertDTO("ferrari", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );

        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(marca)
            .when().post("/marcas/marca-add")
            .then()
            .statusCode(201);

        //List<Marca> marcaResult = repository.findByNome("hiunday");
        //System.out.println(marcaResult.size());
        //System.out.println(marcaResult.get(0).getNomeMarca());
        //System.out.println(carroResult.get(1).getNome());
    }

    @Test
    public void testUpdate() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        MarcaInsertDTO marca = new MarcaInsertDTO("mitsubshi", carros);

        List<CarroIdDTO> carrosUpdate  = new ArrayList<CarroIdDTO>();
        MarcaInsertDTO marcaUpdtae = new MarcaInsertDTO("mitsubshi3", carrosUpdate);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(marca)
            .when().post("/marcas/marca-add")
            .getBody();
        
        Marca marcadojson = new Marca();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            marcadojson.setId(jsonNode.path("id").asLong());
            marcadojson.setNomeMarca(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(marcadojson.getId());
        System.out.println(marcadojson.getNomeMarca());

        given()
            .auth().oauth2(token).pathParam("id", marcadojson.getId())
            .contentType(ContentType.JSON)
            .body(marcaUpdtae)
            .when().put("/marcas/{id}")
            .then()
            .statusCode(204);

        Marca marcaResult = repository.findById(marcadojson.getId());
        System.out.println(marcaResult.getNomeMarca());
    }

    @Test
    public void testDelete() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        MarcaInsertDTO marca = new MarcaInsertDTO("buildYourDreams", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(marca)
            .when().post("/marcas/marca-add")
            .getBody();
        
        Marca marcadojson = new Marca();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            marcadojson.setId(jsonNode.path("id").asLong());
            marcadojson.setNomeMarca(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(marcadojson.getId());
        System.out.println(marcadojson.getNomeMarca());

        given()
            .auth().oauth2(token).pathParam("id", marcadojson.getId())
            .contentType(ContentType.JSON)
            .when().delete("/marcas/delete/{id}")
            .then()
            .statusCode(204);

        Marca marcaResult = repository.findById(marcadojson.getId());
        if(marcaResult == null){
            System.out.println("Foi deletado com sucesso");
        }else{
            System.out.println(marcaResult.getNomeMarca());
        }
    }

    @Test
    public void testFindAll() {
        given()
          .when().get("/marcas")
          .then()
             .statusCode(200);
    }

    @Test
    public void testFindById() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        MarcaInsertDTO marca = new MarcaInsertDTO("Lada", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(marca)
            .when().post("/marcas/marca-add")
            .getBody();
        
        Marca marcadojson = new Marca();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            marcadojson.setId(jsonNode.path("id").asLong());
            marcadojson.setNomeMarca(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(marcadojson.getId());
        System.out.println(marcadojson.getNomeMarca());

        ResponseBody responseResultCategoria =given()
            .auth().oauth2(token).pathParam("id", marcadojson.getId())
            .contentType(ContentType.JSON)
            .when().get("/marcas/{id}")
            .getBody();

        if(responseResultCategoria != null){
            System.out.println(responseResultCategoria.asString());
        }
    }

    //Erro
    @Test
    public void testFindByNome() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        MarcaInsertDTO marca = new MarcaInsertDTO("Infinity", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(marca)
            .when().post("/marcas/marca-add")
            .getBody();
        
        Marca marcadojson = new Marca();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            marcadojson.setId(jsonNode.path("id").asLong());
            marcadojson.setNomeMarca(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(marcadojson.getId());
        System.out.println(marcadojson.getNomeMarca());

        ResponseBody responseResultCarro =given()
            .auth().oauth2(token).pathParam("nome", marcadojson.getNomeMarca())
            .contentType(ContentType.JSON)
            .when().get("/marcas/search/nome/{nome}")
            .getBody();

        if(responseResultCarro != null){
            System.out.println(responseResultCarro.asString());
        }
    }
}
