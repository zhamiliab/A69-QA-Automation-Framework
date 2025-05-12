import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";
    public WebDriverWait wait  = null;
    public Actions actions = null;
    public FluentWait wait1 = null;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @DataProvider(name = "IncorrectLoginData")
    public static Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {"invalid@testPro.ca", "invalidPass"},
                {"wrong@test.ru", ""},
                {"zhamilia.begalieva@testpro.io", "invalidPass"},
                {"wrong@test.ru", "GaeuncKV"},
                {"", "invalid"},
                {"", "GaeuncKV"},
                {"", ""},
        };
    }




    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait1 = new FluentWait(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        url = BaseURL;
        navigateToPage();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public void navigateToPage() {
        driver.get(url);
    }

    public void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']")));
        emailField.clear();  // so the email field should be empty after previous attempt
        emailField.sendKeys(email);
    }

    public void providePassword(String pass) {
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        password.clear();    // so password field should be empty after prev. attempt
        password.sendKeys(pass);
    }

    public void clickSubmit() {
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']")));
        submit.click();
    }

    public void clickAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.xpath("//img[@class='avatar']"));
        avatarIcon.click();
    }

    public void provideCurrentPassword(String currPass) {
        WebElement currentPass = driver.findElement(By.xpath("//input[@id='inputProfileCurrentPassword']"));
        currentPass.sendKeys(currPass);
    }

    public void provideProfileName(String randomName) {
        WebElement profileName = driver.findElement(By.xpath("//input[@id='inputProfileName']"));
        profileName.clear();
        profileName.sendKeys(randomName);
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void clickSaveButton() {
        WebElement save = driver.findElement(By.xpath("//button[@class='btn-submit']"));
        save.click();
    }
}