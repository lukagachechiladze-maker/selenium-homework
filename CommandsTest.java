import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommandsTest {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            /* ---------- Dynamic Controls ---------- */
            driver.get("http://the-internet.herokuapp.com/dynamic_controls");

            By inputField = By.xpath("//input[@type='text']");
            By message = By.id("message");
            By enableButton = By.xpath("//button[text()='Enable']");
            By disableButton = By.xpath("//button[text()='Disable']");

            driver.findElement(enableButton).click();

            wait.until(ExpectedConditions.elementToBeClickable(inputField));
            wait.until(ExpectedConditions.textToBe(message, "It's enabled!"));

            if (driver.findElement(inputField).isEnabled()) {
                System.out.println("Input field enabled and message visible!");
            }

            if (driver.findElement(disableButton).getText().equals("Disable")) {
                System.out.println("Button text changed successfully!");
            }

            WebElement input = driver.findElement(inputField);
            input.sendKeys("Bootcamp");
            input.clear();

            /* ---------- Drag and Drop ---------- */
            driver.get("http://the-internet.herokuapp.com/drag_and_drop");

            WebElement columnA = driver.findElement(By.id("column-a"));
            WebElement columnB = driver.findElement(By.id("column-b"));

            Actions actions = new Actions(driver);
            actions.dragAndDrop(columnA, columnB).perform();

            int yA = columnA.getLocation().getY();
            int yB = columnB.getLocation().getY();

            if (yA == yB) {
                System.out.println("Columns A and B aligned successfully!");
            }

        } finally {
            driver.quit();
        }
    }
}
