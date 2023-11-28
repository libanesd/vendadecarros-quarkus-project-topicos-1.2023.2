package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CarroDTORepository.CarroDTO;
import br.unitins.topicos1.dto.CarroDTORepository.CarroTestDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.TipoCambio;
import br.unitins.topicos1.model.TipoCarroceria;
import br.unitins.topicos1.model.TipoCombustivel;
import br.unitins.topicos1.model.TipoDeUsuario;
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

    // @Test
    // public void testUpdate() {
    //     List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
    //     telefones.add(new TelefoneDTO("63", "5555-5555"));

    //     UsuarioDTO dto = new UsuarioDTO(
    //         "Mark Zuckerberg Update",
    //         "marquinho",
    //         "333",
    //         telefones
    //     );

    //     // inserindo um usuario
    //     UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
    //     Long id = usuarioTest.id();

    //     UsuarioDTO dtoUpdate = new UsuarioDTO(
    //         "Mark Zuckerberg",
    //         "mark",
    //         "555",
    //         telefones
    //     );

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(dtoUpdate)
    //         .when().put("/usuarios/"+ id)
    //         .then()
    //         .statusCode(204);

    //     // verificando a alteracao

    //     System.out.println(id);
    //     System.out.println(id);
    //     System.out.println(id);
    //     System.out.println(id);
    //     UsuarioResponseDTO usu = usuarioService.findById(4l);
    //     assertThat(usu.nome(), is("Mark Zuckerberg"));
    //     assertThat(usu.login(), is("mark"));

    // }

}
