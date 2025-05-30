import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class ContextClick extends BaseTest{
@Test
    public void playSong(){
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("zhamilia.begalieva@testpro.io","GaeuncKV");

    chooseAllSongsList();
    contextClickFirstSong();
    ChoosePlayOption();
    Assert.assertTrue(isSongPlaying());
}

    public boolean isSongPlaying() {
    WebElement soundBarVisualizer=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']")));
    return soundBarVisualizer.isDisplayed();

    }

    public void ChoosePlayOption() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='playback']"))).click();
     }

    public void contextClickFirstSong() {
        WebElement firstSongElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='items']/tr[1]")));
     actions.contextClick(firstSongElement).perform();

}

    public void chooseAllSongsList() {
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='songs']"))).click();
    }
}
