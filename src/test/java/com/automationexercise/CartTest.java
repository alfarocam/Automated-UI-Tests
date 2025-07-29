package com.automationexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\camil\\DriversCS\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void testAddProductToCart() throws InterruptedException {
        // 1. Clic en 'Products' (usamos xpath más robusto)
        WebElement productsLink = driver.findElement(By.xpath("//a[@href='/products']"));
        productsLink.click();
        Thread.sleep(2000);

        // 2. Clic en 'Add to cart' del primer producto
        WebElement addToCartBtn = driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[1]"));
        addToCartBtn.click();
        Thread.sleep(2000);

        // 3. Clic en 'View Cart'
        WebElement viewCartBtn = driver.findElement(By.xpath("//u[contains(text(),'View Cart')]"));
        viewCartBtn.click();
        Thread.sleep(2000);

        // 4. Verificación del producto en carrito
        WebElement cartTable = driver.findElement(By.id("cart_info"));
        assertTrue("El producto no fue agregado al carrito correctamente", cartTable.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
