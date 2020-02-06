import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class formulariocorto_coppel_test {

    @Test
    public void main() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/Java/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://dev.coppel.com/solicita-tu-credito-coppel");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Luis");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("last_name")).sendKeys("Javier");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("apellido_paterno")).sendKeys("Yangmur");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("apellido_materno")).sendKeys("Luis");
        Select drpday = new Select (driver.findElement(By.id("birth_day")));
        drpday.selectByValue("16");
        Select drpmonth = new Select (driver.findElement(By.id("birth_month")));
        drpmonth.selectByValue("5");
        Select drpyear = new Select (driver.findElement(By.id("birth_year")));
        drpyear.selectByValue("1990");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("skyline.tan@gmail.com");
        driver.findElement(By.id("phone2")).click();
        driver.findElement(By.id("phone2")).sendKeys("6672296369");
        driver.findElement(By.id("phone1")).click();
        driver.findElement(By.id("phone1")).sendKeys("6677546967");
        driver.findElement(By.id("privacity")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnContinuar")));
        driver.findElement(By.id("btnContinuar")).click();
        wait.until(ExpectedConditions.urlToBe("https://dev.coppel.com/tienes-una-solicitud-en-proceso"));

        TimeUnit.SECONDS.sleep(10);
        driver.close();
    }
}
