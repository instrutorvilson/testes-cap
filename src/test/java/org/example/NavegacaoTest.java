package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class NavegacaoTest extends  BaseTest{
    @Test
    public void deveNavegarPorUrl(){
        driver.get("http://127.0.0.1:5500/loadlazzing.html");
        driver.findElement(By.id("btnCadastro")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("cadastro"));

        assertTrue(driver.getCurrentUrl().contains("cadastro"));
    }

    @Test
    public void deveNavegarPorElemento(){
        driver.get("http://127.0.0.1:5500/loadlazzing.html");
        driver.findElement(By.id("btnCadastro")).click();

        WebElement campoNome = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));

        assertTrue(campoNome.isDisplayed());
    }

}
