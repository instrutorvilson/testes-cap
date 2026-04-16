package com.testes.Testeui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostPage {

    WebDriver driver;
    WebDriverWait wait;

    public PostPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By nome = By.id("nome");
    By email = By.id("email");
    By btnSalvar = By.id("btnSalvar");
    By msg = By.id("msg");

    public void preencherFormulario(String n, String e) {
        driver.findElement(nome).sendKeys(n);
        driver.findElement(email).sendKeys(e);
    }

    public void salvar() {
        driver.findElement(btnSalvar).click();
    }

    public void aguardarMensagem() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(msg));
    }

    public String obterMensagem() {
        return driver.findElement(msg).getText();
    }
}

