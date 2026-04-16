package com.testes.Testeui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;
    By email = By.id("email");
    By senha = By.id("senha");
    By btnLogin = By.id("btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessar() {
        driver.get("http://localhost:5500/login.html");
    }

    public void logar() {
        driver.findElement(email).sendKeys("teste");
        driver.findElement(senha).sendKeys("123");
        driver.findElement(btnLogin).click();
    }
}
