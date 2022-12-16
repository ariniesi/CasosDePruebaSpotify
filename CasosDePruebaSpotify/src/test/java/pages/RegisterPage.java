package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class RegisterPage extends BaseClass {

    public RegisterPage(WebDriver driver) {
        super(driver);
    } //Agrego el constructor

    //Las pages tienen por objetivo centralizar los localizadores y acciones de pagina

    //Identificar localizadores
    By locatorTxtCorreo = By.id("email");
    By locatorTxtConfirmarCorreo = By.name("cosdfvsadfggnfirm");
    By locatorTxtContrasena = By.name("password");
    By locatorTxtNickName = By.name("displayname");
    By locatorTxtDiaNac = By.id("day");
    By locatorDDLMesNac = By.id("month");
    By locatorTxtAnnioNac = By.name("year");
    By locatorRdbGeneroH = By.xpath("//label[@for='gender_option_male']");
    By locatorChkMarketing = By.xpath("//label[@for='marketing-opt-checkbox']");
    By locatorChkCompartirDatos = By.xpath("//label[@for='third-party-checkbox']");
    By locatorBtnRegistrarte = By.xpath("//button[@type='submit']");

    By locatorLblErrorChaptcha = By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]");


    //Realizar acciones en el sitio
    public void completarFormularioRegistro(String correo,String confirmarCorreo,String password,String apodo,
                                            String diaNac, String mes, String annio){

        //Completar Formulario
        esperarXSegundos(1000);
        agregarTexto(esperarAElementoWeb(locatorTxtCorreo),correo);
        agregarTexto(esperarAElementoWeb(locatorTxtConfirmarCorreo),confirmarCorreo);
        agregarTexto(esperarAElementoWeb(locatorTxtContrasena),password);
        agregarTexto(esperarAElementoWeb(locatorTxtNickName),apodo);
        agregarTexto(esperarAElementoWeb(locatorTxtDiaNac),diaNac);
        seleccionarDDlPorTexto(esperarAElementoWeb(locatorDDLMesNac),mes);
        agregarTexto(esperarAElementoWeb(locatorTxtAnnioNac),annio);
        click(esperarAElementoWeb(locatorRdbGeneroH));
        ScrollElementoWeb(esperarAElementoWeb(locatorChkMarketing));
        click(esperarAElementoWeb(locatorChkMarketing));
        click(esperarAElementoWeb(locatorChkCompartirDatos));
        ScrollElementoWeb(esperarAElementoWeb(locatorBtnRegistrarte));
        click(esperarAElementoWeb(locatorBtnRegistrarte));
    }

    public String obtenerErrorCaptchaVacio(){
        return obtenerTexto(esperarAElementoWeb(locatorLblErrorChaptcha));
    }

}
