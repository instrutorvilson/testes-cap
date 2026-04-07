package treina.testes.TesteApi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaApiTest {
    @LocalServerPort
    int port;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.proxy = null;
    }

    @BeforeEach
    void setPort() {
        RestAssured.port = port;
    }

    @Test
    void deveListarContas() {
        given()
                .when()
                .get("/v1/contas")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
        @Test
        void deveCriarConta() {
            given()
                    .contentType("application/json")
                    .body("""
                            {
                              "titular": "João",
                              "saldo": 1000
                            }
                        """)
                    .when()
                    .post("/v1/contas")
                    .then()
                    .statusCode(201)
                    .body("titular", equalTo("João"))
                    .body("saldo", equalTo(1000));
        }

    @Test
    void deveAtualizarConta() {
            Long id = criarConta("ze", new BigDecimal("1000.00"));
            // atualiza
            given()
                    .contentType("application/json")
                    .body("""
                                {
                                  "titular": "Maria",
                                  "saldo": 2000
                                }
                            """)
                    .when()
                    .put("/v1/contas/" + id)
                    .then()
                    .statusCode(200)
                    .body("titular", equalTo("Maria"));
        }


    @Test
    void deveExcluirConta() {
        Long id = criarConta("ze", new BigDecimal("1000.00"));
        given()
                .when()
                .delete("/v1/contas/" + id)
                .then()
                .statusCode(204);
    }
    @Test
    void deveBuscarContaPorId() {
        Long id = criarConta("ze", new BigDecimal("1000.00"));
        given()
                .when()
                .get("/v1/contas/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.intValue()));
    }

    private Long criarConta(String titular, BigDecimal saldo){
        return given()
                .contentType("application/json")
                .body("""
                            {
                              "titular": "%s",
                              "saldo": %s
                            }
                        """.formatted(titular, saldo)
                )
                .when()
                .post("/v1/contas")
                .then()
                .extract()
                .jsonPath()
                .getLong("id");
    }

}
