import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class Hover extends BaseTest {
    @Test
    public void hoverOverPlayButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("zhamilia.begalieva@testpro.io","GaeuncKV");

        Assert.assertTrue(hoverPlay().isDisplayed());
    }

    public WebElement hoverPlay() {
        WebElement play = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play));
    }

}
