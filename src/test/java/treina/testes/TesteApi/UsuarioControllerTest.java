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
        String user = System.getenv().getOrDefault("APP_USER","admin");
        String password = System.getenv().getOrDefault("APP_PASSWORD","123");

        given()
                .auth().basic(user,password)
                .when()
                .get("/v1/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    void deveCriarUsuario() {
        String user = System.getenv().getOrDefault("APP_USER","admin");
        String password = System.getenv().getOrDefault("APP_PASSWORD","123");

        String json = """
                            {
                                "nome": "João",
                                "email": "joao@email.com"
                            }
                       """;
        given()
                .auth().basic(user, password)
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
