package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

import br.unitins.topicos1.service.ClienteService;

@QuarkusTest
public class ClienteResourceTest {
    
    @Inject
    ClienteService service;

    @Test
    public void testFindAll() {
        given()
          .when().get("/clientes")
          .then()
             .statusCode(200);
    }
}
