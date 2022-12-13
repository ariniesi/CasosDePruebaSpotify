package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class CasosDePrueba {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private String rutaDriver= System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterMethod
    public void posCondicion(){
        driver.close();
    }

    @BeforeMethod
    public void preCondiciones(){

        System.setProperty(propertyDriver,rutaDriver);

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);

        js = (JavascriptExecutor) driver;

        driver.navigate().to("https://open.spotify.com/");

        driver.manage().window().maximize();
    }

    @Test
    public void CP001_Contraseña_Corta() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'password')]"))).sendKeys("123");

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Tu contraseña es')]")).getText(),"Tu contraseña es demasiado corta.");
    }

    @Test
    public void CP002_Dia_Invalido() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'day')]"))).sendKeys("33");

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Indica un día válido')]")).getText(),"Indica un día válido del mes.");
    }
    @Test
    public void CP003_Nombre_Perfil() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'displayname')]"))).sendKeys("");

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Indica un nombre')]")).getText(),"Indica un nombre para tu perfil.");
    }
    @Test
    public void CP004_Confirmacion_Email_Invalido() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[starts-with(@id,'email')]"))).sendKeys("ariana.niesi@tsoftglobal.com");

        driver.findElement(By.xpath("//input[attribute::id='confirm']")).sendKeys("");

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Es necesario que confirmes')]")).getText(),"Es necesario que confirmes tu correo electrónico.");
    }
    @Test
    public void CP005_Email_Invalido() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[starts-with(@id,'email')]"))).sendKeys("ariana.niesi.tsoftglobal.com");

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico no es válido')]")).getText(),"Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com");
    }

}
