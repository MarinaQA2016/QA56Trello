package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.CurrentBoardQAHaifa56Helper;
import pages.LoginPageHelper;

import java.util.List;

public class CurrentBoardTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardQAHaifa56Helper qaHaifa56Page;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = new LoginPageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qaHaifa56Page = new CurrentBoardQAHaifa56Helper(driver);
        loginPage.openLoginPage();
        loginPage.loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        qaHaifa56Page.openCurrentBoard();
        qaHaifa56Page.waitUntilPageIsLoaded();


        //--- Open 'QA Haifa56'
       /* WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator(BOARD_TITLE)));
        ourBoard.click();*/


    }

    @Test
    public void createNewList()  {

        //--- Add new list---

        /*List<WebElement> listLists = driver.
                findElements(By.xpath("//div[@class = 'list js-list-content']"));*/
        int beforeAdding = qaHaifa56Page.getListsQuantity();
        System.out.println("Lists before adding: " + beforeAdding);
        qaHaifa56Page.createNewList();
        qaHaifa56Page.enterTitle("Test");
        qaHaifa56Page.submitAddingList();
        qaHaifa56Page.cancelFromEditMode();

        int afterAdding = qaHaifa56Page.getListsQuantity();
        //System.out.println("Lists after adding: " + listLists.size());

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
                waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);

                //----Submit of adding list ----
                WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
                addListButton.click();

                //--- Cancel from edit mode ----
                WebElement cancelEdit = driver
                        .findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
                cancelEdit.click();
                waitUntilElementIsNotVisible(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"), 10);
                System.out.println("Lists after adding: " + driver.
                        findElements(By.xpath("//div[@class = 'list js-list-content']")).size());
            }

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
            waitUntilElementIsNotVisible(By.cssSelector("div.card-composer a.icon-close"),10);

        //---Receive the quantity of cards ---
        int afterAdding = driver.findElements(By.cssSelector("a.list-card")).size();
        Assert.assertEquals(afterAdding,beforeAdding+1,
                "The quantity of cards before adding new card is not the same as the quantity after adding");


    }





}
