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
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.automationtesting.in/Alerts.html");

        driver.findElement(By.cssSelector("a[href='#Textbox']")).click();

        driver.findElement(By.cssSelector("button.btn.btn-info")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String name = "Vako Kobulashvili";

        alert.sendKeys(name);
        alert.accept();

        WebElement result = driver.findElement(By.id("demo1"));
        String resultText = result.getText();

        Assert.assertTrue(resultText.contains(name));

        driver.quit();
    }
}
