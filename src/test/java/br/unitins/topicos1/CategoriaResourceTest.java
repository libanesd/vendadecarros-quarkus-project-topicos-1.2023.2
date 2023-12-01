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
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.repository.CategoriaRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.resource.CarroResource;
import br.unitins.topicos1.resource.CategoriaResource;

@QuarkusTest
public class CategoriaResourceTest {

    @Inject
    CategoriaResource resource;

    @Inject
    CategoriaRepository repository;

    @Inject
    CarroRepository repositoryCarro;

    @Inject
    CarroResource resourceCarro;
    
    @Inject
    AuthResource authResource;

    @Test
    public void testInsert() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        CategoriaInsertDTO categoria = new CategoriaInsertDTO("Carros Populares", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );

        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(categoria)
            .when().post("/categorias/categoria-add")
            .then()
            .statusCode(201);

        List<Categoria> carroResult = repository.findByNome("Carros Populares");
        System.out.println(carroResult.size());
        System.out.println(carroResult.get(0).getNome());
        //System.out.println(carroResult.get(1).getNome());
    }

    @Test
    public void testUpdate() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        CategoriaInsertDTO categoria = new CategoriaInsertDTO("Carros para viagem", carros);

        List<CarroIdDTO> carrosUpdate  = new ArrayList<CarroIdDTO>();
        CategoriaInsertDTO categoriaUpdate = new CategoriaInsertDTO("Carros para viagem e esportivos", carrosUpdate);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(categoria)
            .when().post("/categorias/categoria-add")
            .getBody();
        
        Categoria categoriadojson = new Categoria();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            categoriadojson.setId(jsonNode.path("id").asLong());
            categoriadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(categoriadojson.getId());
        System.out.println(categoriadojson.getNome());

        given()
            .auth().oauth2(token).pathParam("id", categoriadojson.getId())
            .contentType(ContentType.JSON)
            .body(categoriaUpdate)
            .when().put("/categorias/{id}")
            .then()
            .statusCode(204);

        Categoria carroResult = repository.findById(categoriadojson.getId());
        System.out.println(carroResult.getNome());
    }

    @Test
    public void testDelete() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        CategoriaInsertDTO categoria = new CategoriaInsertDTO("Carros usados por jogadores de futbol", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(categoria)
            .when().post("/categorias/categoria-add")
            .getBody();
        
        Categoria categoriadojson = new Categoria();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            categoriadojson.setId(jsonNode.path("id").asLong());
            categoriadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(categoriadojson.getId());
        System.out.println(categoriadojson.getNome());

        given()
            .auth().oauth2(token).pathParam("id", categoriadojson.getId())
            .contentType(ContentType.JSON)
            .when().delete("/categorias/delete/{id}")
            .then()
            .statusCode(204);

        Categoria carroResult = repository.findById(categoriadojson.getId());
        if(carroResult == null){
            System.out.println("Foi deletado com sucesso");
        }else{
            System.out.println(carroResult.getNome());
        }
    }

    @Test
    public void testFindAll() {
        given()
          .when().get("/categorias")
          .then()
             .statusCode(200);
    }

    @Test
    public void testFindById() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        CategoriaInsertDTO categoria = new CategoriaInsertDTO("Carros de praia", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(categoria)
            .when().post("/categorias/categoria-add")
            .getBody();
        
        Categoria categoriadojson = new Categoria();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            categoriadojson.setId(jsonNode.path("id").asLong());
            categoriadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(categoriadojson.getId());

        ResponseBody responseResultCategoria =given()
            .auth().oauth2(token).pathParam("id", categoriadojson.getId())
            .contentType(ContentType.JSON)
            .when().get("/categorias/{id}")
            .getBody();

        if(responseResultCategoria != null){
            System.out.println(responseResultCategoria.asString());
        }
    }

    @Test
    public void testFindByNome() {
        List<CarroIdDTO> carros  = new ArrayList<CarroIdDTO>();
        CategoriaInsertDTO categoria = new CategoriaInsertDTO("Carros de corrida", carros);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(categoria)
            .when().post("/categorias/categoria-add")
            .getBody();
        
        Categoria categoriadojson = new Categoria();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            categoriadojson.setId(jsonNode.path("id").asLong());
            categoriadojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(categoriadojson.getId());

        ResponseBody responseResultCarro =given()
            .auth().oauth2(token).pathParam("nome", categoriadojson.getNome())
            .contentType(ContentType.JSON)
            .when().get("/categorias/search/nome/{nome}")
            .getBody();

        if(responseResultCarro != null){
            System.out.println(responseResultCarro.asString());
        }
    }
}
