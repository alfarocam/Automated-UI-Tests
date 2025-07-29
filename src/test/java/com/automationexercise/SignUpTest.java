package com.automationexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SignUpTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\camil\\DriversCS\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void testSignUp() throws InterruptedException {
        // 1. Ir a la página principal (ya se hace en setUp)
        Thread.sleep(1000);

        // 2. Clic en Signup / Login
        driver.findElement(By.linkText("Signup / Login")).click();
        Thread.sleep(1500);

        // 3. Ingresar nombre y email
        driver.findElement(By.name("name")).sendKeys("Camila");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("camila_test8@example.com");
        Thread.sleep(1500);

        // 4. Clic en el botón Signup
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
        Thread.sleep(2000);

        // 5. Completar 'Enter Account Information'
        driver.findElement(By.id("id_gender2")).click(); // Mrs.
        driver.findElement(By.id("password")).sendKeys("Test1234");
        new Select(driver.findElement(By.id("days"))).selectByValue("10");
        new Select(driver.findElement(By.id("months"))).selectByValue("5");
        new Select(driver.findElement(By.id("years"))).selectByValue("1995");
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        Thread.sleep(1500);

        // 6. Llenar información de dirección
        driver.findElement(By.id("first_name")).sendKeys("Camila");
        driver.findElement(By.id("last_name")).sendKeys("Test");
        driver.findElement(By.id("company")).sendKeys("MiEmpresa");
        driver.findElement(By.id("address1")).sendKeys("123 Calle Principal");
        driver.findElement(By.id("address2")).sendKeys("Oficina 4");
        new Select(driver.findElement(By.id("country"))).selectByVisibleText("United States");
        driver.findElement(By.id("state")).sendKeys("California");
        driver.findElement(By.id("city")).sendKeys("Los Angeles");
        driver.findElement(By.id("zipcode")).sendKeys("90001");
        driver.findElement(By.id("mobile_number")).sendKeys("88888888");
        Thread.sleep(1500);

        // 7. Crear cuenta
        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
        Thread.sleep(3000);

        // 8. Validar que se creó correctamente
        WebElement successMessage = driver.findElement(By.xpath("//*[contains(text(),'Account Created!')]"));
        assertTrue("La cuenta no fue creada exitosamente", successMessage.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
