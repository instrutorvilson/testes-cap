package treina.testes.TesteApi;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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
}
