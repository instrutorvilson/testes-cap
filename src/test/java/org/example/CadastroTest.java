package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class CadastroTest {
    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void cadastro(){
        driver.get("http://127.0.0.1:5500/cadastro.html");
        WebElement nome = driver.findElement(By.id("nome"));
        nome.sendKeys("Jose da Silva");

        driver.findElement(By.id("email")).sendKeys("maria@gmail.com");
        driver.findElement(By.tagName("button")).click();

        String resposta = driver.findElement(By.id("resposta")).getText();
        assertEquals("registro inserido com sucesso",resposta);
    }
}
