package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests {
    WebDriver driver;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        //---- Enter to the application ---
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(5000);
    }

    @Test
    public void createNewList(){

    }

    @Test
    public void createNewCard(){

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
