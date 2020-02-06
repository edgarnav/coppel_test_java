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
        // Se crea un  nuevo objeto llamado driver, indicando el navegador en el cual se ejecutará la prueba
        WebDriver driver = new ChromeDriver();

        // Se asigna una url de arranque para las pruebas (puede llamarse en cualquier ocasión
        driver.get("https://www.coppel.com/");

        // Se crea el objeto web_driver para esperas explicitas de condiciones de elementos
        WebDriverWait wait_driver = new WebDriverWait(driver,10);

        // Se declara un espera explicita para la visivilidad de los elementos por el localizador
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='header']/nav/div/div[1]/div/div/div[2]/div[3]")));

        // Se le da click al elelento
        driver.findElement(By.xpath("//*[@id='header']/nav/div/div[1]/div/div/div[2]/div[3]")).click();
        driver.findElement(By.id("signInQuickLink")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")));

        // Sen envía texto (para este caso) al input con el localizador por id.
        driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")).sendKeys("edgar.navarrete@coppel.com");
        driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).sendKeys("1234");
        driver.findElement(By.id("btn-login")).click();

        /* Una vez que se realiza el login, se va a carrito a verificar que no haya más productos que puedan afectar el test,
        si los hay los elimina uno por uno.
        En caso de que no los haya, símplemente dará click al logo de coppel e irá al inicio.
         */
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("MiniShoppingCart")));
        driver.findElement(By.id("MiniShoppingCart")).click();
        boolean products = true;

        while (products){
            try {
                wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Eliminar")));
                driver.findElement(By.linkText("Eliminar")).click();
                // Espera implícita
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                driver.findElement(By.id("logo")).click();
                products = false;
            }
        }
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("SimpleSearchForm_SearchTerm")));
        driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys("409758");

        /* Realiza el envío de una acción al input, en este caso es como si presionara "Enter" en el teclado para realizar
        la busqueda
        */
        driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys(Keys.ENTER);

        // Eespera explícita del elemento hasta que puede ser presionado
        wait_driver.until(ExpectedConditions.elementToBeClickable(By.id("add2CartBtn")));
        driver.findElement(By.id("add2CartBtn")).click();
        wait_driver.until(ExpectedConditions.elementToBeClickable(By.id("GotoCartButton2")));
        driver.findElement(By.id("GotoCartButton2")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("shopcartCheckout")));
        driver.findElement(By.id("shopcartCheckout")).click();

        wait_driver.until(ExpectedConditions.elementToBeClickable(By.id("WC_ShopcartAddressFormDisplay_links_metodo_pago")));
        driver.findElement(By.id("WC_ShopcartAddressFormDisplay_links_metodo_pago")).click();

        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("listPaymentMethod")));

        /* Se declara un nuevo objeto para el elemento de tipo "select". Posteriormente, se selecciona apor "value" la
        la opción "PayU"
        */
        Select pay_method = new Select(driver.findElement(By.id("listPaymentMethod")));
        pay_method.selectByValue("PayU");

        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("pago_bancoppel")));
        driver.findElement(By.id("pago_bancoppel")).click();
        driver.findElement(By.id("shippingBillingPageNext")).click();
        wait_driver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("total_breakdown")));

        TimeUnit.SECONDS.sleep(10);

        // Una vez que es terminada la prueba, se cierra el navegador
        driver.close();

    }
}