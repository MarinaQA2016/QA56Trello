package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static tests.TestBase.BOARD_TITLE;

public class CurrentBoardQAHaifa56Helper extends PageBase{

    public CurrentBoardQAHaifa56Helper(WebDriver driver) {
        super(driver);
    }
    public void openCurrentBoard(){
        WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator(BOARD_TITLE)));
        ourBoard.click();
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(By.xpath("//span[contains(text(),'QA Haifa56')]"),10);
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']"),10);
    }

    private String boardLocator(String boardTitle) {
        return "//div[@title = '" + boardTitle + "']/../..";
    }

    public int getListsQuantity(){
        List<WebElement> listLists = driver.
                findElements(By.xpath("//div[@class = 'list js-list-content']"));
        return listLists.size();
    }

    public void createNewList() {
        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        waitUntilElementIsVisible(By.xpath("//input[@placeholder='Enter list title...']"),10);
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));
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
}
