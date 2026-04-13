package teste.teste_selenium;

import static org.junit.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class GoogleTest {
	WebDriver driver;
	
	@Before
    public void setUp() {
      /*  System.setProperty("webdriver.edge.driver", "C:\\Users\\vilson.moro\\Downloads\\edgedriver_win32\\msedgedriver.exe");
        driver = new EdgeDriver();*/
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
	
	@Test
    public void cadastroTitle() {
        driver.get("http://127.0.0.1:5500/cadastro.html");
        String title = driver.getTitle();
        assertEquals("Cadastro de contatos", title);
    }

	@Test
    public void cadastroSucesso() {
        driver.get("http://127.0.0.1:5500/cadastro.html");
        driver.findElement(By.id("nome")).sendKeys("Ana da Silva");
        driver.findElement(By.id("email")).sendKeys("ana@gmail.com");
        driver.findElement(By.id("user")).sendKeys("Ana");
        driver.findElement(By.id("senha")).sendKeys("123");
        driver.findElement(By.tagName("button")).click();
        assertEquals(driver.findElement(By.id("info")).getText(), "user cadastrado");
    }
	
	/*@Test
    public void loginSucesso() {
        driver.get("http://127.0.0.1:5500/login.html");
        driver.findElement(By.id("user")).sendKeys("Ana");
        driver.findElement(By.id("senha")).sendKeys("123");
        driver.findElement(By.tagName("button")).click();
        assertEquals("http://127.0.0.1:5500/adm.html", driver.getCurrentUrl());
    }
	
	@Test
    public void logininvalido() {
        driver.get("http://127.0.0.1:5500/login.html");
        driver.findElement(By.id("user")).sendKeys("xxx");
        driver.findElement(By.id("senha")).sendKeys("123");
        driver.findElement(By.tagName("button")).click();
        assertEquals("http://127.0.0.1:5500/login.html", driver.getCurrentUrl());
        assertEquals(driver.findElement(By.id("info")).getText(), "login inválido");
    }*/
	@After
    public void tearDown() {
        driver.quit();
    }
}
