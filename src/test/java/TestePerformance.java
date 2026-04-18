import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestePerformance {

    Playwright playwright;
    Browser browser;

    @BeforeAll
    void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
        );
    }

    @AfterAll
    void teardown() {
        browser.close();
        playwright.close();
    }

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

    @Test
    void teste4() {
        executarTeste("https://example.org");
    }

    void executarTeste(String url) {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate(url);
        System.out.println(page.title());

        context.close(); // importante!
    }
}

