package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTests {

    @Test
    public void fillForm() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Vako");
        driver.findElement(By.id("lastName")).sendKeys("Kobulashvili");
        driver.findElement(By.id("userEmail")).sendKeys("vakokobulashvili@ibsu.edu.ge");
        driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("598000000");
	driver.findElement(By.id("dateOfBirthInput")).sendKeys("12 Jun 2007\n");

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("Chemistry");
        subjects.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
        driver.findElement(By.id("currentAddress")).sendKeys("Gori, Tbilisi");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Uttar Pradesh')]")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Agra')]")).click();
        driver.findElement(By.id("submit")).click();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

	String popupText = driver.findElement(By.className("modal-content")).getText();

        Assert.assertTrue(popupText.contains("Vako Kobulashvili"));
        Assert.assertTrue(popupText.contains("vakokobulashvili@ibsu.edu.ge"));
        Assert.assertTrue(popupText.contains("Male"));
        Assert.assertTrue(popupText.contains("598000000"));
        Assert.assertTrue(popupText.contains("12 June,2007"));
        Assert.assertTrue(popupText.contains("Chemistry"));
        Assert.assertTrue(popupText.contains("Sports"));
        Assert.assertTrue(popupText.contains("Gori, Tbilisi"));

        driver.findElement(By.id("closeLargeModal")).click();

        driver.quit();
    }
}
