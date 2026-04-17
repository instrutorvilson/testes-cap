import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayWrigthTest extends BaseTest {
    @Test
    void tituloGoogle(){
        page.navigate("https://www.google.com");
        assertTrue(page.title().contains("Google"));
    }

    @Test
    void mostraMensagemQuandoNomeVazio(){
       page.navigate("http://127.0.0.1:5500/index.html");
       page.click("#btnSalvar");
       String mensagem = page.textContent("#mensagem");
       assertEquals("Nome obrigatório", mensagem);
    }

    @Test
    void deveMostarMensagemSucessoQuandoNomeInformado(){
        page.navigate("http://127.0.0.1:5500/index.html");
        page.fill("#nome","joao");
        page.click("#btnSalvar");
        String mensagem = page.textContent("#mensagem");
        assertEquals("Cadastro realizado", mensagem);
    }
}
