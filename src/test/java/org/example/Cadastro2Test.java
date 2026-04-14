package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Cadastro2Test {
    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void cadastro(){
        driver.get("http://127.0.0.1:5500/cadastro2.html");
        WebElement nome = driver.findElement(By.id("nome"));
        nome.sendKeys("Jose da Silva");

        driver.findElement(By.id("email")).sendKeys("maria@gmail.com");
        driver.findElement(By.tagName("button")).click();

        Alert alert = driver.switchTo().alert();

        assertEquals("registro inserido com sucesso",alert.getText());
    }

    @After
    public void finalizar(){
        driver.quit();
    }
}
