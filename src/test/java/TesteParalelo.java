import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

public class TesteParalelo {

    @Test
    void teste1() {
        executarTeste("https://example.com");
    }

    @Test
    void teste2() {
        executarTeste("https://example.org");
    }

    @Test
    void teste3() {
        executarTeste("https://example.org");
    }

    void executarTeste(String url) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate(url);
            System.out.println("Título: " + page.title());

            browser.close();
        }
    }
}
