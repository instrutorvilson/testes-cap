package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class GoogleTest extends BaseTest {

    @Test
    public void lerTitleGoogle(){
        driver.get("http://google.com");
        String title = driver.getTitle();
        assertEquals("Google",title);
    }

    @After
    public void finalizar(){
        driver.quit();
    }
}
