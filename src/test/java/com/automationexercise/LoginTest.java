package com.automationexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\camil\\DriversCS\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void testLogin() throws InterruptedException {
        // 1. Clic en Signup / Login
        driver.findElement(By.linkText("Signup / Login")).click();
        Thread.sleep(1000);

        // 2. Ingresar email y contraseña
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("camila_test8@example.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Test1234");
        Thread.sleep(1000);

        // 3. Clic en botón Login
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        Thread.sleep(2000);

        // 4. Verificar
        WebElement loggedInText = driver.findElement(By.xpath("//*[contains(text(),'Logged in as')]"));
        assertTrue("No se mostró el mensaje de usuario logueado", loggedInText.getText().contains("Camila"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
