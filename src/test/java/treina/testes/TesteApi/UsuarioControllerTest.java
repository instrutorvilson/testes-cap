package treina.testes.TesteApi;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsuarioControllerTest extends TestesApiBase{
    @Test
    void deveRetornar401SemAutenticacao() {
        given()
                .when()
                .get("/v1/usuarios")
                .then()
                .statusCode(401);
    }

    @Test
    void devePermitirAcessoComAuth() {
        given()
                .auth().basic("admin","123")
                .when()
                .get("/v1/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    void deveCriarUsuario() {
        String json = """
                            {
                                "nome": "João",
                                "email": "joao@email.com"
                            }
                       """;
        given()
                .auth().basic("admin", "123")
                .contentType("application/json")
                .body(json)
                .when()
                .post("/v1/usuarios")
                .then()
                .statusCode(201)
                .body("nome", equalTo("João"));
    }
    @Test
    void deveRetornar401AoCriarUsuarioSemAutenticacao() {
        String json = """
                            {
                                "nome": "João",
                                "email": "joao@email.com"
                            }
                       """;
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/v1/usuarios")
                .then()
                .statusCode(401);
    }
}
