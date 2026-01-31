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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();
            driver.get("https://demoqa.com/automation-practice-form");

            /* ---------- Personal Info ---------- */
            driver.findElement(By.id("firstName")).sendKeys("Luka");
            driver.findElement(By.id("lastName")).sendKeys("Gachechiladze");
            driver.findElement(By.id("userEmail"))
                    .sendKeys("lukagachechiladze@ibsu.edu.ge");
            driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
            driver.findElement(By.id("userNumber")).sendKeys("555562643");

            /* ---------- Date of Birth ---------- */
            WebElement dob = driver.findElement(By.id("dateOfBirthInput"));
            dob.click();
            dob.sendKeys(Keys.CONTROL + "a");
            dob.sendKeys("24 Apr 2006");
            dob.sendKeys(Keys.ENTER);

            /* ---------- Subjects ---------- */
            WebElement subjects = driver.findElement(By.id("subjectsInput"));
            subjects.sendKeys("Chemistry");
            subjects.sendKeys(Keys.ENTER);

            /* ---------- Hobbies & Address ---------- */
            driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
            driver.findElement(By.id("currentAddress")).sendKeys("Akhaltsikhe");

            /* ---------- State & City ---------- */
            driver.findElement(By.id("state")).click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'Uttar Pradesh')]"))).click();

            driver.findElement(By.id("city")).click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'Agra')]"))).click();

            driver.findElement(By.id("submit")).click();

            /* ---------- Validation ---------- */
            WebElement modal = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("modal-content"))
            );

            String popupText = modal.getText();

            Assert.assertTrue(popupText.contains("Luka Gachechiladze"));
            Assert.assertTrue(popupText.contains("lukagachechiladze@ibsu.edu.ge"));
            Assert.assertTrue(popupText.contains("Male"));
            Assert.assertTrue(popupText.contains("555562643"));
            Assert.assertTrue(popupText.contains("24 April,2006"));
            Assert.assertTrue(popupText.contains("Chemistry"));
            Assert.assertTrue(popupText.contains("Sports"));
            Assert.assertTrue(popupText.contains("Akhaltsikhe"));

            driver.findElement(By.id("closeLargeModal")).click();

        } finally {
            driver.quit();
        }
    }
}
