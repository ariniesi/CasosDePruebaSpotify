package ejemplosSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ElementosWebSelenium {
    public static void main(String[] args) throws InterruptedException {

        String rutaDriver= System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://open.spotify.com/");

        //maximizar el browser
        driver.manage().window().maximize();

        /*
        By locatorBtnCerrarPoliticaPrivacidad = By.xpath("//button[@aria-label='Cerrar']");

        WebElement btnCerrarPoliticaPrivacidad = driver.findElement(locatorBtnCerrarPoliticaPrivacidad);

        Thread.sleep(3000);

        if(btnCerrarPoliticaPrivacidad.isDisplayed()){
            btnCerrarPoliticaPrivacidad.click();
        }*/

        //Crear Localizador
        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        //Crear Elemento Web
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        //Acción
        btnRegistrarse.click();

        Thread.sleep(3000);

        //agregar texto en campo email
        driver.findElement(By.id("email")).sendKeys("domingo.saavedra@tsoftglobal.com");

        Thread.sleep(2000);

        //agregar texto en campo email
        driver.findElement(By.name("confirm")).sendKeys("domingo.saavedra@tsoftglobal.com");

        Thread.sleep(2000);

        //agregar texto en campo email
        driver.findElement(By.name("password")).sendKeys("123454321");

        Thread.sleep(2000);

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        Thread.sleep(2000);

        //agregar texto en campo email
        driver.findElement(By.id("day")).sendKeys("28");

        Thread.sleep(2000);

        //instanciar un comboBox
        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");

        Thread.sleep(2000);

        driver.findElement(By.name("year")).sendKeys("1991");

        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[@for='gender_option_male']")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();

        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Find element by link text and store in variable "Element"
        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Thread.sleep(2000);

        String resultadoEsperado = "Confirma que no eres un robot.";

        String resultadoActual = driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText();

        if(resultadoActual.equals(resultadoEsperado)){
            System.out.println("Caso OK. Se verifica mensaje de ausencia de captcha");
        }else{
            System.out.println("Buuuu");
        }


        driver.quit();
    }
}
