package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import br.unitins.topicos1.resource.CategoriaResource;

@QuarkusTest
public class CategoriaResourceTest {

    @Inject
    CategoriaResource resource;

    @Test
    public void testFindAll() {
        given()
          .when().get("/categorias")
          .then()
             .statusCode(200);
    }
}
