package com.testes.Testeui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsuariosPage {

    WebDriver driver;
    WebDriverWait wait;

    public UsuariosPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By btnCarregar = By.id("btnCarregar");
    By tabela = By.id("tabelaUsuarios");

    public void carregar() {
        driver.findElement(btnCarregar).click();
    }

    public void aguardarTabela() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(tabela));
    }

    public boolean tabelaVisivel() {
        return driver.findElement(tabela).isDisplayed();
    }
}

