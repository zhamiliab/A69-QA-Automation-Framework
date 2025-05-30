import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test(dataProvider ="IncorrectLoginData", dataProviderClass = BaseTest.class)
    public void loginInvalidEmailValidPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);

        navigateToPage();
        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url); // asserting that website url stays the same

    }
    @Test(enabled = false, description = "Test has been marked as skipped due to am issue in Jira-124111")
    public void loginValidEmailPassword() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
//        navigateToPage();
        loginPage.login("zhamilia.begalieva@testpro.io","GaeuncKV");


      Assert.assertEquals(driver.getCurrentUrl(), url); // asserting that website url stays the same

        Assert.assertTrue(homePage.isAvatarDisplayed());

      }


    @Test
    public void loginValidEmailEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
//        navigateToPage();
        loginPage.login("zhamilia.begalieva@testpro.io","");

        Assert.assertTrue(loginPage.isSubmitButtonDisplayed());
    }

}
