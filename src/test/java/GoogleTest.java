package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTest extends BaseTest {

    @Test
    public void deveBuscarNoGoogle() {

        page.navigate("https://www.google.com");

        // Screenshot inicial
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/home.png")));

        // Pesquisa
        page.fill("textarea[name='q']", "Playwright Java");
        page.keyboard().press("Enter");

        page.waitForTimeout(2000);

        // Screenshot após busca
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/resultado.png")));

        // Validação simples
        assertTrue(page.title().toLowerCase().contains("playwright"));
    }

    @Test
    public void testeComErroParaDebug() {

        page.navigate("https://example.com");

        try {
            page.click("#elemento-inexistente");
        } catch (Exception e) {

            // Screenshot de erro
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/erro.png")));

            throw e;
        }
    }
}
