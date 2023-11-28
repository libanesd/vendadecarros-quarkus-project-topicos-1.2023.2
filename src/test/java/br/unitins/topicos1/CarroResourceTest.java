package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroTestDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;
import br.unitins.topicos1.model.TipoDeUsuario;
import br.unitins.topicos1.resource.CarroResource;

@QuarkusTest
public class CarroResourceTest {

    @Inject
    CarroResource resource;

    @Test
    public void testFindAll() {
        given()
          .when().get("/carros")
          .then()
             .statusCode(200);
    }
    
    @Test
    public void testInsert() {
        List<CarroTestDTO> carros = new ArrayList<CarroTestDTO>();
        Marca marca = new Marca();
        carros.add(new CarroTestDTO("LAMBORGHINI HURACÁN",
         "5.2 V10 GASOLINA LP 640-4 PERFORMANTE LDF", 
        "5.2 V10",
        "2018/2019",
        "Cinza",
        "#Ducarai", 
        "Sorocaba - SP",
        3890000, 
        5861, 
        marca, 
        TipoCombustivel.GASOLINA, 
        TipoCarroceria.CUPE,
        TipoCambio.AUTOMATICA));

        UsuarioDTO dto = new UsuarioDTO(
            "Leandro Artunes",
            "leandroartunes@gmail.com",
            "123456",
            TipoDeUsuario.ADMIN
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/carros")
            .then()
            .statusCode(201);
            // .body();
    }
}
