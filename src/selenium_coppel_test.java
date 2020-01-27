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
public class selenium_coppel_test {

    @Test
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.coppel.com/");
        WebDriverWait wait_driver = new WebDriverWait(driver,10);
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='header']/nav/div/div[1]/div/div/div[2]/div[3]")));
        driver.findElement(By.xpath("//*[@id='header']/nav/div/div[1]/div/div/div[2]/div[3]")).click();
        driver.findElement(By.id("signInQuickLink")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")));
        driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")).sendKeys("edgar.navarrete@coppel.com");
        driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).sendKeys("1234");
        driver.findElement(By.id("btn-login")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("MiniShoppingCart")));
        driver.findElement(By.id("MiniShoppingCart")).click();
        boolean products = true;

        while (products){
            try {
                wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("WC_OrderItemDetailsf_links_2_1")));
                driver.findElement(By.id("WC_OrderItemDetailsf_links_2_1")).click();
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                driver.findElement(By.id("logo")).click();
                products = false;
            }
        }
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("SimpleSearchForm_SearchTerm")));
        driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys("409758");
        driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys(Keys.ENTER);
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("add2CartBtn")));
        driver.findElement(By.id("add2CartBtn")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("GotoCartButton2")));
        driver.findElement(By.id("GotoCartButton2")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("shopcartCheckout")));
        driver.findElement(By.id("shopcartCheckout")).click();

        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("WC_ShopcartAddressFormDisplay_links_metodo_pago")));
        driver.findElement(By.id("WC_ShopcartAddressFormDisplay_links_metodo_pago")).click();

        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("listPaymentMethod")));
        Select pay_method = new Select(driver.findElement(By.id("listPaymentMethod")));
        pay_method.selectByValue("PayU");

        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("pago_bancoppel")));
        driver.findElement(By.id("pago_bancoppel")).click();
        driver.findElement(By.id("shippingBillingPageNext")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("total_breakdown")));

        TimeUnit.SECONDS.sleep(10);
    }
}