package com.testes.Testeui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetalhesPage {

    WebDriver driver;
    WebDriverWait wait;

    public DetalhesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By nome = By.id("nome");

    public void aguardarNome() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nome));
    }

    public String obterNome() {
        return driver.findElement(nome).getText();
    }
    
    By btnNovoPost = By.id("btnNovoPost");

    public void clicarNovoPost() {
        driver.findElement(btnNovoPost).click();
    }

}
