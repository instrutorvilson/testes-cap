import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

public class LoginTest extends  BaseTest {
    @Test
    void testeLogin1() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        LoginPage login = new LoginPage(page);
        login.acessar("https://www.google.com");

        context.close();
    }

    @Test
    void testeLogin2() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        LoginPage login = new LoginPage(page);
        login.acessar("https://www.google.com");

        context.close();
    }


    @Test
    void testeLogin3() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        LoginPage login = new LoginPage(page);
        login.acessar("https://www.google.com");

        context.close();
    }

}
