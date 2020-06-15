package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTests {

    WebDriver driver;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        //---- Enter the application ---
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(5000);

        //--- Press 'Log In' menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //----Enter login value and click 'Log In' button ----
        driver.findElement(By.id("user")).sendKeys("grudcyn.news@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);

        //---- Enter password value and click 'Log In' button
        driver.findElement(By.id("password")).sendKeys("jh4j2ZgKzUAV2eD");
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(35000);

        //---- Open board 'QA Haifa56' ---
        driver.findElement(By.xpath("//div[@title='QA Haifa56']")).click();
        Thread.sleep(5000);
    }

    @Test
    public void createNewList() throws InterruptedException {
        // --- Display number of lists in board 'QA Haifa56' ---
        int initialNumberOfLists = numberOfLists();
        System.out.println("Initially number of lists in 'QA Haifa56' is " + initialNumberOfLists);
        Thread.sleep(2000);

        // --- Add new list 'Added by Selenium'
        driver.findElement(By.className("placeholder")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("list-name-input"))
                .sendKeys("Added by Selenium");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Add List']")).click();
        Thread.sleep(2000);

        // --- Display new number of lists in board 'QA Haifa56' ---
        int newNumberOfLists = numberOfLists();
        System.out.println("New number of lists in 'QA Haifa56' is " + newNumberOfLists);
        Thread.sleep(2000);

        // --- Assure that number of lists in board 'QA Haifa56' is correct
        if (newNumberOfLists == initialNumberOfLists + 1) {
            System.out.println("Test passed: one list added");
        } else {
            throw new IllegalArgumentException("Test failed!");
        }
        Thread.sleep(2000);
    }

    @Test
    public void createNewCard() throws InterruptedException {
        // --- Create new list in board 'QA Haifa56' if there is no lists in it ---
        if (numberOfLists() == 0) {
            // --- Add new list 'List with cards'
            driver.findElement(By.className("placeholder")).click();
            Thread.sleep(2000);
            driver.findElement(By.className("list-name-input"))
                    .sendKeys("List with cards");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@value='Add List']")).click();
            Thread.sleep(2000);
        }

        // --- Display number of cards in board 'QA Haifa56' ---
        int initialNumberOfCards = numberOfCards();
        System.out.println("Initially number of cards in 'QA Haifa56' is " + initialNumberOfCards);
        Thread.sleep(2000);

        // --- Add new card 'SeleniumCard' ---
        // --- try-catch block handles different 'Add' button names ---
        try {
            driver.findElement(By.xpath("//span[contains(text(),'Add a card')]")).click();
        } catch (ElementNotInteractableException ex) {
            driver.findElement(By.xpath("//span[contains(text(),'Add another card')]")).click();
        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='list-card-details u-clearfix']/textarea"))
                .sendKeys("SeleniumCard");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Add Card']")).click();
        Thread.sleep(2000);

        // --- Display new number of cards in board 'QA Haifa56' ---
        int newNumberOfCards = numberOfCards();
        System.out.println("New number of cards in 'QA Haifa56' is " + newNumberOfCards);
        Thread.sleep(2000);

        // --- Assure that number of cards in board 'QA Haifa56' is correct
        if (newNumberOfCards == initialNumberOfCards + 1) {
            System.out.println("Test passed: one card added");
        } else {
            throw new IllegalArgumentException("Test failed!");
        }
        Thread.sleep(2000);
    }

    // --- Utilities ---
    private int numberOfLists() throws InterruptedException {
        //---- Get number of lists in board 'QA Haifa56' ---
        List<WebElement> numberOfLists = driver.findElements(By.className("list-header-extras"));
        Thread.sleep(2000);
        return numberOfLists.size();
    }

    private int numberOfCards() throws InterruptedException {
        //---- Get number of cards in board 'QA Haifa56' ---
        List<WebElement> numberOfCards = driver.findElements(By.className("list-card-dropzone"));
        Thread.sleep(2000);
        return numberOfCards.size();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
