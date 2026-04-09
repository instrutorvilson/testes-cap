package treina.testes.TesteApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import treina.testes.TesteApi.model.Usuario;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    private String user;
    private String password;

   /* @BeforeAll
    void setup1(){
        Dotenv dotenv = Dotenv.load();

        user = dotenv.get("APP_SECURITY_USER");
        password = dotenv.get("APP_SECURITY_PASSWORD");
        System.out.println(user +" cmcmm " + password);
    }
*/

    @BeforeEach
    void setup(){
        usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@gmail.com");

        Dotenv dotenv = Dotenv.load();

        user = dotenv.get("APP_USER");
        password = dotenv.get("APP_PASSWORD");

        System.out.println(user +" cmcmm " + password);
    }
    @Test
     void deveRetornar201AoCriarUsuarioComAutenticacao() throws Exception{
         mockMvc.perform(post("/v1/usuarios")
                         .with(httpBasic(user, password))
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(usuario)))
                 .andExpect(status().isCreated());
     }

    @Test
    @WithMockUser(username = "admin", password = "123")
    void devePermitirAcessoComUsuarioMock() throws Exception {
        mockMvc.perform(post("/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveNegarAcessoComCredenciaisInvalidas() throws Exception {
        mockMvc.perform(get("/v1/usuarios")
                        .with(httpBasic(user, "errado")))
                .andExpect(status().isUnauthorized());
    }

}
