package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTests {
    private static final String BOARD_TITLE = "QA Haifa56";
    WebDriver driver;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        //---- Enter to the application ---
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(5000);
    }

    @Test
    public void createNewList() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //----Enter login value and click 'Log in' button ----
        driver.findElement(By.id("user")).sendKeys("marinaqatest2019@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);

        //---- Enter password value and click 'Log in' button
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(25000);
        System.out.println("'Boards' button text: " + driver
                .findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText());
        Thread.sleep(5000);

        //--- Open 'QA Haifa56'
        WebElement ourBoard = driver.findElement(By.xpath(boardLocator(BOARD_TITLE)));
        ourBoard.click();
        Thread.sleep(5000);

        //--- Add new list---
        System.out.println("Lists before adding: " + driver.
                findElements(By.xpath("//div[@class = 'list js-list-content']")).size());
        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));

        //----Add title of the list
        addTitleField.click();
        addTitleField.sendKeys("Test");
        Thread.sleep(2000);

        //----Submit of adding list ----
        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();
        Thread.sleep(2000);

        //--- Cancel from edit mode ----
        WebElement cancelEdit = driver
                .findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();
        Thread.sleep(2000);
        System.out.println("Lists after adding: " + driver.
                findElements(By.xpath("//div[@class = 'list js-list-content']")).size());

    }

    private String boardLocator(String boardTitle) {
        return "//div[@title = '" + boardTitle + "']/../..";
    }

    @Test
    public void createNewCard(){




    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
