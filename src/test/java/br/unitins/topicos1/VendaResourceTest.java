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

import java.util.Date;

import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioIdDTO;
import br.unitins.topicos1.dto.VendaDTORepository.VendaInsertDTO;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoDePagamento;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.VendaRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.resource.VendaResource;

@QuarkusTest
public class VendaResourceTest {
    
    @Inject
    VendaResource resource;

    @Inject
    AuthResource authResource;

    @Inject
    VendaRepository repository;

    @Test
    public void testFindAll() {
        given()
          .when().get("/vendas")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        CarroIdDTO carro  = new CarroIdDTO(1L);
        UsuarioIdDTO usuario = new UsuarioIdDTO(1L);
        VendaInsertDTO venda = new VendaInsertDTO(new Date(), 70000, "Compra da Lamborguine", carro, TipoDePagamento.DEBITO, StatusVenda.APROVADA, usuario);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );

        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(venda)
            .when().post("/vendas/venda-add")
            .then()
            .statusCode(201);
    }

    //Produzir mais carros para
    @Test
    public void testUpdate() {
        CarroIdDTO carro  = new CarroIdDTO(7L);
        UsuarioIdDTO usuario = new UsuarioIdDTO(2L);
        VendaInsertDTO venda = new VendaInsertDTO(new Date(), 70300, "Compra da niva", carro, TipoDePagamento.DEBITO, StatusVenda.APROVADA, usuario);

        CarroIdDTO carroUpdate  = new CarroIdDTO(10L);
        UsuarioIdDTO usuarioUpdate = new UsuarioIdDTO(2L);
        VendaInsertDTO vendaUpdate = new VendaInsertDTO(new Date(), 70000, "Compra da IX35", carroUpdate, TipoDePagamento.DEBITO, StatusVenda.APROVADA, usuarioUpdate);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );

        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(venda)
            .when().post("/vendas/venda-add")
            .getBody();
        
        Venda vendadojson = new Venda();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            vendadojson.setId(jsonNode.path("id").asLong());
            vendadojson.setDescricao(jsonNode.path("descricao").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(vendadojson.getId());
        System.out.println(vendadojson.getDescricao());

        given()
            .auth().oauth2(token).pathParam("id", vendadojson.getId())
            .contentType(ContentType.JSON)
            .body(vendaUpdate)
            .when().put("/vendas/{id}")
            .then()
            .statusCode(204);

        Venda vendaResult = repository.findById(vendadojson.getId());
        System.out.println("Result:" + vendaResult.getDescricao());
    }

    @Test
    public void testFindById() {
        CarroIdDTO carro  = new CarroIdDTO(4L);
        UsuarioIdDTO usuario = new UsuarioIdDTO(5L);
        VendaInsertDTO venda = new VendaInsertDTO(new Date(), 70000, "Compra da FX35", carro, TipoDePagamento.DEBITO, StatusVenda.APROVADA, usuario);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(venda)
            .when().post("/vendas/venda-add")
            .getBody();
        
        Venda vendadojson = new Venda();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            vendadojson.setId(jsonNode.path("id").asLong());
            vendadojson.setDescricao(jsonNode.path("descricao").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(vendadojson.getId());
        System.out.println(vendadojson.getDescricao());

        ResponseBody responseResultVenda =given()
            .auth().oauth2(token).pathParam("id", vendadojson.getId())
            .contentType(ContentType.JSON)
            .when().get("/vendas/{id}")
            .getBody();

        if(responseResultVenda != null){
            System.out.println(responseResultVenda.asString());
        }
    }

}
