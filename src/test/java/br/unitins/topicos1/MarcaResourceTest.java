package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.AuthDTORepository.LoginDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroIdDTO;
import br.unitins.topicos1.dto.MarcaDTORepository.MarcaInsertDTO;
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
    public void testFindAll() {
        given()
          .when().get("/marcas")
          .then()
             .statusCode(200);
    }

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


}
