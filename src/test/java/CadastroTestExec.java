import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CadastroTestExec extends BaseTest {
    private CadastroPage cadastroPage;
    @Test
    void deveMostrarErroQuandoNomeVazio(){
        cadastroPage = new CadastroPage(page);
        cadastroPage.acessarpagina();
        cadastroPage.clicarBotao();
        assertEquals("Nome obrigatório", cadastroPage.obterMensagem());
    }

    @Test
    void deveMostraSucessoQuandoNomeInformado(){
        cadastroPage = new CadastroPage(page);
        cadastroPage.acessarpagina();
        cadastroPage.preencherNome("joao");
        cadastroPage.clicarBotao();

        assertEquals("Cadastro realizado", cadastroPage.obterMensagem());
    }
}
