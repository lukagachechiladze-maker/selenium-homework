import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommandsTest {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);

        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//button[text()='Enable']")).click();


        if (driver.findElement(By.xpath("//input[@type='text']")).isEnabled() &&
                driver.findElement(By.id("message")).getText().equals("It's enabled!")) {
            System.out.println("Input field enabled and text visible!");
        }

        if (driver.findElement(By.xpath("//button[text()='Disable']")).getText().equals("Disable")) {
            System.out.println("Button text changed successfully!");
        }
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Bootcamp");
        driver.findElement(By.xpath("//input[@type='text']")).clear();

        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        int yA = driver.findElement(By.id("column-a")).getLocation().getY();
        int yB = driver.findElement(By.id("column-b")).getLocation().getY();

        if (yA == yB) {
            System.out.println("Columns A and B aligned successfully!");
        }

        driver.quit();
    }
}