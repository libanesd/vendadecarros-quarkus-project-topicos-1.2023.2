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
import br.unitins.topicos1.dto.OfertaDTORepository.OfertaIdDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioUpdateDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaIdDTO;
import br.unitins.topicos1.model.TipoDeUsuario;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.service.UsuarioService;

@QuarkusTest
public class UsuarioResourceTest {
    
    @Inject
    UsuarioService service;

    @Inject
    UsuarioRepository repository;

    @Inject
    AuthResource authResource;
  
    @Test
    public void testInsert() {

        UsuarioInsertDTO clienteInsertDTO = new UsuarioInsertDTO("37645783001",
                                                                 "Yasmin Artuness",
                                                                 "yasminartunes@gmail.com",
                                                                 "1228 suldeste",
                                                                 "55 66 7432-7766",
                                                                 "yasminartunes@gmail.com",
                                                                 "123456",
                                                                 TipoDeUsuario.ADMIN);


        given()
            .contentType(ContentType.JSON)
            .body(clienteInsertDTO)
            .when().post("/usuarios/usuario-add")
            .then()
            .statusCode(201);

        List<Usuario> carroResult = repository.findByNome("Yasmin Artuness");
        System.out.println(carroResult.size());
        System.out.println(carroResult.get(0).getNome());
        System.out.println(carroResult.get(0).getNome());
    }

    @Test
    public void testUpdate() {
        UsuarioInsertDTO clienteInsertDTO = new UsuarioInsertDTO("91636893082",
                                                                 "Vilmar Artuness",
                                                                 "vilmarartunes@gmail.com",
                                                                 "1230 suldeste",
                                                                 "55 66 7463-7889",
                                                                 "vilmarartunes@gmail.com",
                                                                 "123456",
                                                                 TipoDeUsuario.USER);

        List<OfertaIdDTO> ofertaIdDTOs = new ArrayList<OfertaIdDTO>();
        List<VendaIdDTO> vendaIdDTOs = new ArrayList<VendaIdDTO>();
        UsuarioUpdateDTO clienteInsertDTOUpdate = new UsuarioUpdateDTO("91636893082",
                                                                 "Vilmar Artuness Betoven",
                                                                 "vilmarartunes@gmail.com",
                                                                 "1230 suldeste",
                                                                 "55 66 7463-7889",
                                                                 "vilmarartunes@gmail.com",
                                                                 "123456",
                                                                 TipoDeUsuario.USER,
                                                                 ofertaIdDTOs,
                                                                 vendaIdDTOs);
        
        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .contentType(ContentType.JSON)
            .body(clienteInsertDTO)
            .when().post("/usuarios/usuario-add")
            .getBody();
        
        Usuario usuariodojson = new Usuario();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            usuariodojson.setId(jsonNode.path("id").asLong());
            usuariodojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(usuariodojson.getId());
        System.out.println(usuariodojson.getNome());

        given()
            .auth().oauth2(token).pathParam("id", usuariodojson.getId())
            .contentType(ContentType.JSON)
            .body(clienteInsertDTOUpdate)
            .when().put("/usuarios/{id}")
            .then()
            .statusCode(204);

        Usuario usuarioResult = repository.findById(usuariodojson.getId());
        System.out.println(usuarioResult.getNome());
    }

    @Test
    public void testDelete() {
        UsuarioInsertDTO clienteInsertDTO = new UsuarioInsertDTO("73124105028",
                                                                 "Carlos Artuness",
                                                                 "carlosartunes@gmail.com",
                                                                 "1233 suldeste",
                                                                 "55 66 7463-2522",
                                                                 "carlosartunes@gmail.com",
                                                                 "123456",
                                                                 TipoDeUsuario.USER);
        
        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .contentType(ContentType.JSON)
            .body(clienteInsertDTO)
            .when().post("/usuarios/usuario-add")
            .getBody();
        
        Usuario usuariodojson = new Usuario();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            usuariodojson.setId(jsonNode.path("id").asLong());
            usuariodojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(usuariodojson.getId());
        System.out.println(usuariodojson.getNome());

        given()
            .auth().oauth2(token).pathParam("id", usuariodojson.getId())
            .contentType(ContentType.JSON)
            .when().delete("/usuarios/usuario/{id}")
            .then()
            .statusCode(204);

        Usuario usuarioResult = repository.findById(usuariodojson.getId());
        if(usuarioResult == null){
            System.out.println("Foi deletado com sucesso");
        }else{
            System.out.println(usuarioResult.getNome());
        }
    }

    @Test
    public void testFindAll() {
        given()
          .when().get("/clientes")
          .then()
             .statusCode(200);
    }

    @Test
    public void testFindById() {
        UsuarioInsertDTO clienteInsertDTO = new UsuarioInsertDTO("83615537033",
                                                                 "Vanessa Artuness",
                                                                 "vanessaartunes@gmail.com",
                                                                 "1234 suldeste",
                                                                 "55 66 7463-8765",
                                                                 "vanessaartunes@gmail.com",
                                                                 "123456",
                                                                 TipoDeUsuario.USER);
        
        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .contentType(ContentType.JSON)
            .body(clienteInsertDTO)
            .when().post("/usuarios/usuario-add")
            .getBody();
        
        Usuario usuariodojson = new Usuario();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            usuariodojson.setId(jsonNode.path("id").asLong());
            usuariodojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(usuariodojson.getId());
        System.out.println(usuariodojson.getNome());

        ResponseBody responseResultCategoria =given()
            .auth().oauth2(token).pathParam("id", usuariodojson.getId())
            .contentType(ContentType.JSON)
            .when().get("/usuarios/{id}")
            .getBody();

        if(responseResultCategoria != null){
            System.out.println(responseResultCategoria.asString());
        }
    }

    @Test
    public void testFindByNome() {
        UsuarioInsertDTO clienteInsertDTO = new UsuarioInsertDTO("83891636083",
                                                                 "Gil Artuness",
                                                                 "gilartunes@gmail.com",
                                                                 "1237 suldeste",
                                                                 "55 63 7463-8765",
                                                                 "gilartunes@gmail.com",
                                                                 "123456",
                                                                 TipoDeUsuario.USER);
        
        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .contentType(ContentType.JSON)
            .body(clienteInsertDTO)
            .when().post("/usuarios/usuario-add")
            .getBody();
        
        Usuario usuariodojson = new Usuario();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            usuariodojson.setId(jsonNode.path("id").asLong());
            usuariodojson.setNome(jsonNode.path("nome").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(usuariodojson.getId());
        System.out.println(usuariodojson.getNome());


        ResponseBody responseResultCarro =given()
            .auth().oauth2(token).pathParam("nome", usuariodojson.getNome())
            .contentType(ContentType.JSON)
            .when().get("/usuarios/search/nome/{nome}")
            .getBody();

        if(responseResultCarro != null){
            System.out.println(responseResultCarro.asString());
        }
    }
}
