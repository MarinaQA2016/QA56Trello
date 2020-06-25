package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CurrentBoardHelper extends PageBase{
    private String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
    }
    public void openCurrentBoard(){
        System.out.println("From openCurrentBoard: " + this.boardName);
        WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator()));
        ourBoard.click();
    }

    public void waitUntilPageIsLoaded(){
        //waitUntilElementIsVisible(By.xpath("//span[contains(text(),'QA Haifa56')]"),10);
        waitUntilElementIsVisible(By.xpath(boardTitleLocator()),10);
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']"),10);
    }



    public int getListsQuantity(){
        List<WebElement> listLists = driver.
                findElements(By.xpath("//div[@class = 'list js-list-content']"));
        return listLists.size();
    }

    public void createNewList(String title){
        this.pressCreateNewListButton();
        this.enterTitle(title);
        this.submitAddingList();
        this.cancelFromEditMode();
    }
    public void pressCreateNewListButton() {
        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        waitUntilElementIsVisible(By.xpath("//input[@placeholder='Enter list title...']"),10);
        //WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));
    }

    public void enterTitle(String test) {
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));
        addTitleField.click();
        addTitleField.sendKeys("Test");
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);
    }

    public void submitAddingList() {
        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();
    }

    public void cancelFromEditMode() {
        WebElement cancelEdit = driver
                .findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();
    }

    public boolean existsList() {
        Boolean existsList = false;
        if (driver.findElement(By
                .xpath("//span[@class='placeholder']")).getText().contains("another"))
        {
            existsList = true;
        }
        return existsList;
    }

    public int receiveQuantityOfCards() {
        return driver.findElements(By.cssSelector("a.list-card")).size();
    }

    public void pressAddCardButton(){
        //--- Define two possible buttons for adding new card and click on the displayed one---
        WebElement addCardButton = driver
                .findElement(By.cssSelector("span.js-add-a-card"));
        WebElement addAnotherCardButton = driver
                .findElement(By.cssSelector("span.js-add-another-card"));
        if (addCardButton.isDisplayed()) {
            addCardButton.click();
        }
        else addAnotherCardButton.click();
    }

    public void enterTextToCard(String test_card) {
        WebElement textCurrentCard = driver.findElement(By.cssSelector("textarea.list-card-composer-textarea"));
        textCurrentCard.click();
        textCurrentCard.sendKeys(test_card);
    }

    public void submitAddingCard() {
        WebElement submitCardButton = driver.findElement(By.xpath("//input[@type='submit'][@value = 'Add Card']"));
        submitCardButton.click();
    }

    public void cancelEditCardMode() {
        WebElement cancelEditCardButton = driver.findElement(By.cssSelector("div.card-composer a.icon-close"));
        cancelEditCardButton.click();
        waitUntilElementIsNotVisible(By.cssSelector("div.card-composer a.icon-close"),10);
    }

    private String boardLocator() {
        System.out.println("From boardLocator: " + this.boardName);
        return "//div[@title = '" + boardName + "']/../..";
    }

    private String boardTitleLocator(){
        return "//span[contains(text(),'" + boardName + "')]";
    }
}
