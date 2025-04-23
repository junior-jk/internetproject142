import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // 1. Login com sucesso
    @Order(1)
    @Test
    public void testLoginComSucesso() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement secureArea = driver.findElement(By.xpath("//*[contains(text(), 'You logged into a secure area!')]"));
        assertTrue(secureArea.isDisplayed());
    }

    // 2. Usuário incorreto Senha correta
    @Order(2)
    @Test
    public void testUsuarioIncorretoSenhaCorreta() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("usuarioIncorreto");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 3. Usuário correto Senha incorreta
    @Order(3)
    @Test
    public void testUsuarioCorretoSenhaIncorreta() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("senhaIncorreta");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your password is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 4. Campos em branco
    @Order(4)
    @Test
    public void testCamposEmBranco() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 5. Usuário em branco Senha correta
    
    @Order(5)
    @Test
    public void testUsuarioEmBrancoSenhaCorreta() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 6. Usuário correto Senha em branco
    @Order(6)
    @Test
    public void testUsuarioCorretoSenhaEmBranco() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your password is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 7. Usuário em branco  Senha incorreta
    @Order(7)
    @Test
    public void testUsuarioEmBrancoSenhaIncorreta() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("senhaErrada");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 8. Usuário incorreto Senha em branco
    @Order(8)
    @Test
    public void testUsuarioIncorretoSenhaEmBranco() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("usuarioErrado");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(error.isDisplayed());
    }

    // 9. Campos longos
    @Order(9)   
    @Test
    public void testLoginComCamposLongos() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("usuarioSuperLongoQueNaoExiste123456789");
        driver.findElement(By.id("password")).sendKeys("senhaExtremamenteLonga1234567890!@#");
        driver.findElement(By.cssSelector("#login > button")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]"));
        assertTrue(error.isDisplayed());
    }
}
