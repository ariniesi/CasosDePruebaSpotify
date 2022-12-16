package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class CPs {
    //Atributos
    private HomePage homePage;
    private RegisterPage registerPage;
    private WebDriver driver;
    private String browser = "CHROME";
    private String propertyDriver = "webdriver.chrome.driver";
    private String urlDriver = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String url = "https://www.spotify.com/";

    @BeforeMethod
    public void preparacionTests(){
        homePage = new HomePage(driver); //Se crea la page del home
        homePage.conexionBrowser(browser,propertyDriver,urlDriver); //Se conecta el driver de chrome
        registerPage = new RegisterPage(homePage.getDriver()); //se crea la page de registro

        homePage.cargarPagina(url);
        homePage.maximizarBrowser();
    }

    @Test
    public void CP001_Registro_Fallido_Captcha_en_blanco(){
        homePage.irARegistrarte();
        registerPage.completarFormularioRegistro("domingo.saavedra@tsoftglobal.com", "domingo.saavedra@tsoftglobal.com", "123454321", "Pobre Domingo", "31", "Abril", "1990");
        Assert.assertEquals(registerPage.obtenerErrorCaptchaVacio(),"Confirma que no eres un robot.");
    }

    @AfterMethod
    public void posTests(){
        registerPage.cerrarBrowser();
    }

}
