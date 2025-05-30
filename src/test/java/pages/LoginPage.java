package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By emailField = By.cssSelector("[type='email']");
    By password = By.xpath("//input[@type='password']");
    By submit = By.cssSelector("[type='submit']");

    public void clickSubmit() {
        driver.findElement(submit).click();

    }

    public boolean isSubmitButtonDisplayed(){
       return driver.findElement(submit).isDisplayed();
    }

    public void provideEmail(String email) {
        ExpectedConditions.visibilityOfElementLocated(emailField);
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();  // so the email field should be empty after previous attempt
        driver.findElement(emailField).sendKeys(email);
    }

    public void providePassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(password));
        driver.findElement(password).click();
        driver.findElement(password).clear();    // so password field should be empty after prev. attempt
        driver.findElement(password).sendKeys(pass);
    }

    public void login(String email, String pass){
        provideEmail(email);
        providePassword(pass);
        clickSubmit();
    }

}
