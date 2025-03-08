
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver(); // instanciar o objeto do Selenium como ChromeDriver

        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize(); // maximiza a janela do browser
    }

    @Test
    public void testLoginComSucesso() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword");
        loginButton.click();

        
        WebElement secureArea = driver.findElement(By.xpath("//*[contains(text(), 'Your password is invalid!')]"));
        assertTrue(secureArea.isDisplayed());
       
    }

    // Após os testes, feche o navegador
    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testLoginUsuarioIncorretoSenhaCorreta() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("usuarioIncorreto");
        passwordField.sendKeys("SuperSecretPassword");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void testLoginUsuarioCorretoSenhaIncorreta() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("senhaIncorreta");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your password is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void testLoginComCamposEmBranco() {
        driver.get("https://the-internet.herokuapp.com/login");       
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void testLoginCampoUsuarioEmBranco() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("");
        passwordField.sendKeys("SuperSecretPassword");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void testLoginCampoSenhaEmBranco() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your password is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void testLoginComCamposLongos() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("usuarioSuperLongoQueDeveFalhar");
        passwordField.sendKeys("senhaMuitoLongaQueDeveFalhar1234567890");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void testLoginSenhaIncorretaFormato() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("senha@invalida!");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your password is invalid!')]"));
        assertTrue(errorMessage.isDisplayed());
    }
}
