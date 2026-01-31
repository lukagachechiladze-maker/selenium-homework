package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {

    @Test
    public void alertWithTextboxTest() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.automationtesting.in/Alerts.html");

            /* ---------- Navigate to Alert with Textbox ---------- */
            driver.findElement(By.cssSelector("a[href='#Textbox']")).click();
            driver.findElement(By.cssSelector("button.btn.btn-info")).click();

            /* ---------- Handle Alert ---------- */
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String name = "Vako Kobulashvili";

            alert.sendKeys(name);
            alert.accept();

            /* ---------- Validation ---------- */
            WebElement result = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("demo1"))
            );

            Assert.assertTrue(result.getText().contains(name),
                    "Result text does not contain entered name!");

        } finally {
            driver.quit();
        }
    }
}
