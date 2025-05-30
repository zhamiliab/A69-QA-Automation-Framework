import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.List;


public class countPlaylistSongs extends BaseTest {
    @Test
    public void countPlaylistSongs() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("zhamilia.begalieva@testpro.io","GaeuncKV");

        choosePlaylistByName("uniquePlaylist");
        displayAllSongs();
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));

    }

    public int countSongs() {
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();

    }

    public String getPlaylistDetails() {
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();

    }

    public void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]"))).click();


    }

    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("number of songs found: " + countSongs());
        for (WebElement e : songList) {
            System.out.println(e.getText());
        }
    }

}
