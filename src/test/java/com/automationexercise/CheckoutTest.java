package com.automationexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\camil\\DriversCS\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void testCheckoutProcess() throws Exception {
        // Paso 1-2: Ir a 'Signup / Login'
        driver.findElement(By.linkText("Signup / Login")).click();
        Thread.sleep(500);

        // Paso 3-4: Iniciar sesión
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("camila_test6@example.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Test1234");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        Thread.sleep(500);

        // Paso 5: Ir a página de productos
        WebElement productsLink = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']")));
        productsLink.click();

        // Paso 6: Agregar producto (con eliminación de iframes)
        Thread.sleep(500); // esperar carga
        ((JavascriptExecutor) driver).executeScript("document.querySelectorAll('iframe').forEach(el => el.remove());");

        WebElement firstProduct = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-product-id='1']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        Thread.sleep(300);
        firstProduct.click();

        // Paso 7: Continuar al carrito
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Continue Shopping']")))
                .click();
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        Thread.sleep(500);

        // Paso 8: Proceder al checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Proceed To Checkout']"))).click();
        Thread.sleep(1000);

        // Paso 9: Confirmar dirección y mensaje
        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("message")));
        commentBox.sendKeys("Por favor, dejar en la puerta. Gracias.");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Place Order']")).click();
        Thread.sleep(1000);

        // Paso 10: Ingresar detalles de pago
        driver.findElement(By.name("name_on_card")).sendKeys("Camila Test");
        Thread.sleep(1000);
        driver.findElement(By.name("card_number")).sendKeys("4111111111111111");
        Thread.sleep(1000);
        driver.findElement(By.name("cvc")).sendKeys("123");
        Thread.sleep(1000);
        driver.findElement(By.name("expiry_month")).sendKeys("12");
        Thread.sleep(1000);
        driver.findElement(By.name("expiry_year")).sendKeys("2027");
        Thread.sleep(800);

        // Paso 11: Confirmar pago
        driver.findElement(By.id("submit")).click();
        Thread.sleep(1500);

        // Paso 12: Verificar mensaje de éxito
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Your order has been placed successfully!')]")));
        assertTrue("No se completó exitosamente el pedido", successMessage.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
