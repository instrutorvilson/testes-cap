package org.example;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoadLazzingTest extends BaseTest{

    @Test
    public void testeSemWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://127.0.0.1:5500/loadlazzing.html");
        driver.findElement(By.id("btnMensagem")).click();
        // Vai falhar porque ainda não apareceu
        WebElement msg = driver.findElement(By.id("mensagem"));
        assertTrue(msg.isDisplayed());
    }

    @Test
    public void testeExplicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://127.0.0.1:5500/loadlazzing.html");

        driver.findElement(By.id("btnMensagem")).click();
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
        assertTrue(msg.isDisplayed());
        assertEquals("Mensagem carregada!", driver.findElement(By.id("mensagem")).getText());
    }

    @Test
    public void testeBotaoClicavel() {
        driver.get("http://127.0.0.1:5500/loadlazzing.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("btnHabilitar")).click();

        WebElement botao = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("btnFinalizar"))
        );
        botao.click();
    }

    @Test
    public void testeAlert() {
        driver.get("http://127.0.0.1:5500/loadlazzing.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("btnAlert")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Cadastro realizado!", alert.getText());
        alert.accept();
    }


}
