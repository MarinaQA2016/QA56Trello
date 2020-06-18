package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTests extends TestBase{

    @BeforeMethod
    public void initTests() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //----Enter login value and click 'Log in' button ----
        driver.findElement(By.id("user")).sendKeys(LOGIN);
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);

        //---- Enter password value and click 'Log in' button
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(25000);
        System.out.println("'Boards' button text: " + driver
                .findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText());
        Thread.sleep(5000);

        //--- Open 'QA Haifa56'
        WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator(BOARD_TITLE)));
        ourBoard.click();
        //Thread.sleep(5000);
        waitUntilElementIsVisible(By.xpath("//span[contains(text(),'QA Haifa56')]"),10);
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']"),10);

    }

    @Test
    public void createNewList() throws InterruptedException {

        //--- Add new list---
        List<WebElement> listLists = driver.
                findElements(By.xpath("//div[@class = 'list js-list-content']"));
        int beforeAdding = listLists.size();
        System.out.println("Lists before adding: " + beforeAdding);
        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        waitUntilElementIsVisible(By.xpath("//input[@placeholder='Enter list title...']"),10);
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));

        //----Add title of the list

        addTitleField.click();
        addTitleField.sendKeys("Test");
       // Thread.sleep(2000);
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);

        //----Submit of adding list ----
        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();
        //Thread.sleep(2000);

        //--- Cancel from edit mode ----
        WebElement cancelEdit = driver
                .findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();
        //Thread.sleep(2000);
        //--- Receive new list of Lists---
        listLists = driver.findElements(By.xpath("//div[@class = 'list js-list-content']"));
        int afterAdding = listLists.size();
        System.out.println("Lists after adding: " + listLists.size());

        Assert.assertEquals(afterAdding,beforeAdding+1,
                "The quantity of lists before adding new list is not the same as the quantity after adding");

    }


    @Test
    public void createNewCard() throws InterruptedException {
        Boolean existsList = false;
        if (driver.findElement(By
                .xpath("//span[@class='placeholder']")).getText().contains("another"))
        {
            existsList = true;
        }

            if (!existsList) {
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
            Thread.sleep(3000);
            //---Receive the quantity of cards ---
            int beforeAdding = driver.findElements(By.cssSelector("a.list-card")).size();

            //--- Define two possible buttons for adding new card and click on the displayed one---
            WebElement addCardButton = driver
                    .findElement(By.cssSelector("span.js-add-a-card"));
            WebElement addAnotherCardButton = driver
                    .findElement(By.cssSelector("span.js-add-another-card"));
            if (addCardButton.isDisplayed()) {
                addCardButton.click();
            }
            else addAnotherCardButton.click();

            //--- Enter text, submit the card
            WebElement textCurrentCard = driver.findElement(By.cssSelector("textarea.list-card-composer-textarea"));
            textCurrentCard.click();
            textCurrentCard.sendKeys("test card");
            WebElement submitCardButton = driver.findElement(By.xpath("//input[@type='submit'][@value = 'Add Card']"));
            submitCardButton.click();

            // ---- Cancel edit mode of the next card
            WebElement cancelEditCardButton = driver.findElement(By.cssSelector("div.card-composer a.icon-close"));
            cancelEditCardButton.click();
            Thread.sleep(2000);
        //---Receive the quantity of cards ---
        int afterAdding = driver.findElements(By.cssSelector("a.list-card")).size();
        Assert.assertEquals(afterAdding,beforeAdding+1,
                "The quantity of cards before adding new card is not the same as the quantity after adding");


    }

    private String boardLocator(String boardTitle) {
        return "//div[@title = '" + boardTitle + "']/../..";
    }

}
