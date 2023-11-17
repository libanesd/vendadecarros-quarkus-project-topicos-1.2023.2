package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

import br.unitins.topicos1.resource.MarcaResource;

@QuarkusTest
public class MarcaResourceTest {

    @Inject
    MarcaResource resource;
    

    @Test
    public void testFindAll() {
        given()
          .when().get("/marcas")
          .then()
             .statusCode(200);
    }
}
