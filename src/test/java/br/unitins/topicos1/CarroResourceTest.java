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


import java.util.List;

import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaCarroDTO;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;
import br.unitins.topicos1.repository.CarroRepository;
import br.unitins.topicos1.resource.AuthResource;
import br.unitins.topicos1.resource.CarroResource;

@QuarkusTest
public class CarroResourceTest {


    @Inject
    CarroRepository repository;

    @Inject
    CarroResource resource;
    
    @Inject
    AuthResource authResource;
    
    @Test
    public void testInsert() {
        MarcaCarroDTO marca = new MarcaCarroDTO(2L);
        CarroDTO carro = new CarroDTO("CHEVROLET ONIX", "1.0 FLEX MANUAL", 
        "1.0 FLEX", "2021/2021", "Cinza", "#Ducarai", "Contagem - MG", 
        61990F, 72700F, marca, TipoCombustivel.GASOLINA, TipoCambio.MANUAL, TipoCarroceria.HATCHBACK,3);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );

        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(carro)
            .when().post("/carros/carro-add")
            .then()
            .statusCode(201);

        List<Carro> carroResult = repository.findByNome("LAMBORGHINI HURACÁN");
        System.out.println(carroResult.size());
        System.out.println(carroResult.get(0).getNomeCarro());
        System.out.println(carroResult.get(0).getAno());
    }

    @Test
    public void testUpdate() {
        MarcaCarroDTO marca = new MarcaCarroDTO(1L);
        CarroDTO carro = new CarroDTO("LAMBORGHINI HURACÁN2", "5.2 V10 GASOLINA LP 640-4 PERFORMANTE LDF", 
        "5.2 V10", "2018/2019", "Cinza", "#Ducarai", "Sorocaba - SP", 
        3890000F, 5861F, marca, TipoCombustivel.GASOLINA, TipoCambio.AUTOMATICA, TipoCarroceria.CUPE,3);

        CarroDTO carroupdate = new CarroDTO("LAMBORGHINI HURACÁN28", "5.2 V10 GASOLINA LP 640-4 PERFORMANTE LDF", 
        "5.2 V10", "2018/2019", "Cinza", "#Ducarai", "Sorocaba - SP", 
        3890000F, 5861F, marca, TipoCombustivel.GASOLINA, TipoCambio.AUTOMATICA, TipoCarroceria.CUPE,3);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(carro)
            .when().post("/carros/carro-add")
            .getBody();
        
        Carro carrodojson = new Carro();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            carrodojson.setId(jsonNode.path("id").asLong());
            carrodojson.setNomeCarro(jsonNode.path("nomeCarro").asText());
            carrodojson.setCarroSpec(jsonNode.path("carroSpec").asText());
            carrodojson.setVersao(jsonNode.path("versao").asText());
            carrodojson.setAno(jsonNode.path("ano").asText());
            carrodojson.setCor(jsonNode.path("cor").asText());
            carrodojson.setCaracteristicas(jsonNode.path("caracteristicas").asText());
            carrodojson.setCidade(jsonNode.path("cidade").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(carrodojson.getId());
        System.out.println(carrodojson.getNomeCarro());

        given()
            .auth().oauth2(token).pathParam("id", carrodojson.getId())
            .contentType(ContentType.JSON)
            .body(carroupdate)
            .when().put("/carros/{id}")
            .then()
            .statusCode(204);

        List<Carro> carroResult = repository.findByIdCarros(carrodojson.getId());
        System.out.println(carroResult.get(0).getNomeCarro());
    }

    @Test
    public void testDelete() {
        MarcaCarroDTO marca = new MarcaCarroDTO(1L);
        CarroDTO carro = new CarroDTO("LAMBORGHINI HURACÁN2", "5.2 V10 GASOLINA LP 640-4 PERFORMANTE LDF", 
        "5.2 V10", "2018/2019", "Cinza", "#Ducarai", "Sorocaba - SP", 
        3890000F, 5861F, marca, TipoCombustivel.GASOLINA, TipoCambio.AUTOMATICA, TipoCarroceria.CUPE,3);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(carro)
            .when().post("/carros/carro-add")
            .getBody();
        
        Carro carrodojson = new Carro();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            carrodojson.setId(jsonNode.path("id").asLong());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(carrodojson.getId());

        given()
            .auth().oauth2(token).pathParam("id", carrodojson.getId())
            .contentType(ContentType.JSON)
            .when().delete("/carros/delete/{id}")
            .then()
            .statusCode(204);

        List<Carro> carroResult = repository.findByIdCarros(carrodojson.getId());
        if(carroResult.isEmpty() || carroResult == null){
            System.out.println("Foi deletado com sucesso");
        }
    }

    @Test
    public void testFindAll() {
        given()
          .when().get("/carros")
          .then()
             .statusCode(200);
    }

    @Test
    public void testFindAllEstoques() {
        given()
          .when().get("/carros/estoque")
          .then()
             .statusCode(200);
    }

    @Test
    public void testFindById() {
        MarcaCarroDTO marca = new MarcaCarroDTO(1L);
        CarroDTO carro = new CarroDTO("LAMBORGHINI HURACÁN2", "5.2 V10 GASOLINA LP 640-4 PERFORMANTE LDF", 
        "5.2 V10", "2018/2019", "Cinza", "#Ducarai", "Sorocaba - SP", 
        3890000F, 5861F, marca, TipoCombustivel.GASOLINA, TipoCambio.AUTOMATICA, TipoCarroceria.CUPE,3);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(carro)
            .when().post("/carros/carro-add")
            .getBody();
        
        Carro carrodojson = new Carro();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            carrodojson.setId(jsonNode.path("id").asLong());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(carrodojson.getId());

        ResponseBody responseResultCarro =given()
            .auth().oauth2(token).pathParam("id", carrodojson.getId())
            .contentType(ContentType.JSON)
            .when().get("/carros/{id}")
            .getBody();

        if(responseResultCarro != null){
            System.out.println(responseResultCarro.asString());
        }
    }

    @Test
    public void testFindByNome() {
        MarcaCarroDTO marca = new MarcaCarroDTO(1L);
        CarroDTO carro = new CarroDTO("LAMBORGHINI HURACÁN2", "5.2 V10 GASOLINA LP 640-4 PERFORMANTE LDF", 
        "5.2 V10", "2018/2019", "Cinza", "#Ducarai", "Sorocaba - SP", 
        3890000F, 5861F, marca, TipoCombustivel.GASOLINA, TipoCambio.AUTOMATICA, TipoCarroceria.CUPE,3);

        LoginDTO dto = new LoginDTO(
            "yasminartunes@gmail.com",
            "123456"
        );
        Response response = authResource.login(dto);

        String token = response.getHeaderString("Authorization");

        ResponseBody responseResult = given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(carro)
            .when().post("/carros/carro-add")
            .getBody();
        
        Carro carrodojson = new Carro();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseResult.asString());
            
            carrodojson.setId(jsonNode.path("id").asLong());
            carrodojson.setNomeCarro(jsonNode.path("nomeCarro").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(carrodojson.getId());

        ResponseBody responseResultCarro =given()
            .auth().oauth2(token).pathParam("nome", carrodojson.getNomeCarro())
            .contentType(ContentType.JSON)
            .when().get("/carros/search/nome/{nome}")
            .getBody();

        if(responseResultCarro != null){
            System.out.println(responseResultCarro.asString());
        }
    }
}
