package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class CPs_Refactorizacion {
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
    @AfterMethod
    public void posTests(){
        registerPage.cerrarBrowser();
    }

    @Test
    public void CP001_ContraseñaCorta(){
        homePage.irARegistrarte();
        registerPage.completarFormularioRegistro("","","123","","","Enero","");
        Assert.assertEquals(registerPage.obtenerErrorPasswordCorta(),"Tu contraseña es demasiado corta.");
    }
    @Test
    public void CP002_Dia_Invalido(){
        homePage.irARegistrarte();
        registerPage.completarFormularioRegistro("","","","","33","Enero","");
        Assert.assertEquals(registerPage.obtenerErrorDiaInvalido(),"Indica un día válido del mes.");
    }

    @Test
    public void CP003_Nombre_Perfil(){
        homePage.irARegistrarte();
        registerPage.completarFormularioRegistro("","","","","","Enero","");
        Assert.assertEquals(registerPage.obtenerErrorNombrePerfil(),"Indica un nombre para tu perfil.");
    }

    @Test
    public void CP004_Confirmacion_Email_Invalido() {
        homePage.irARegistrarte();
        registerPage.completarFormularioRegistro("ariana.niesi@tsoftglobal.com", "", "", "", "", "Enero", "");
        Assert.assertEquals(registerPage.obtenerErrorConfirmacionEmailInvalido(), "Es necesario que confirmes tu correo electrónico.");
    }

    @Test
    public void CP005_EmailInvalido(){
        homePage.irARegistrarte();
        registerPage.completarFormularioRegistro("ariana.niesi.tsoftglobal.com","","","","","Enero","");
        Assert.assertEquals(registerPage.obtenerErrorEmail(),"Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com");
    }
}
