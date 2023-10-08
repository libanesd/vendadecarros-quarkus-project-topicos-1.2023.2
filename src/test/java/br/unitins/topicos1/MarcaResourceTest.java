package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import br.unitins.topicos1.resource.CarroResource;
import br.unitins.topicos1.resource.CategoriaResource;
import br.unitins.topicos1.resource.MarcaResource;
import br.unitins.topicos1.service.ClienteService;

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