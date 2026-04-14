package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class AplicacaoTest {
    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void TestarSeletores(){
        driver.get("http://127.0.0.1:5500/aplicacao.html");
       // driver.findElement(By.id("nome")).sendKeys("Ana da Silva");
        driver.findElement(By.xpath("//input[@id='nome']")).sendKeys("Ana da Silva");

        //driver.findElement(By.name("email")).sendKeys("ana.silva@gmail.com");
        driver.findElement(By.className("email-field")).sendKeys("ana.silva@gmail.com");

        //driver.findElement(By.className("btn-cancelar")).click();
        String h2 =  driver.findElement(By.tagName("h2")).getText();

        driver.findElement(By.linkText("Ajuda")).click();
        //driver.findElement(By.partialLinkText("Ajuda")).click();

        driver.findElement(By.xpath("//button[text()='Salvar']")).click();
        String msg = driver.findElement(By.id("mensagem")).getText();
        assertEquals("Salvo com sucesso!",msg);

        WebElement perfil = driver.findElement(By.id("tipo"));
        Select select = new Select(perfil);
        //select.selectByVisibleText("Professor");
        select.selectByValue("professor");

        assertEquals("Cadastro", h2);
    }

    @After
    public void finalizar(){
        driver.quit();
    }
}
