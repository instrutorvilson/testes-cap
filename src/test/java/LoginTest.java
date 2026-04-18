package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void deveLogarComSucesso() {

        page.navigate("file:C:\\Users\\aluno\\Desktop\\Playwrigth-screens\\frontend\\login.html");

        page.fill("#usuario", "admin");
        page.fill("#senha", "123");

        // Screenshot antes do login
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/antes-login.png")));

        page.click("#btnLogin");

        page.waitForTimeout(2000);

        // Screenshot depois
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/depois-login.png")));

        // Validação: mudou de página
        assertTrue(page.url().contains("home.html"));
    }
}
