package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpperMenuHelper extends PageBase {
    @FindBy(xpath = "//button[@data-test-id = 'header-member-menu-button']")
    WebElement upperRight;
    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profilevisabilityMenuItem;

    public UpperMenuHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(profilevisabilityMenuItem,20);
    }

    public void openProfileVisabilityScreen(){
        profilevisabilityMenuItem.click();
    }

    public void openMenuPage(){
        waitUntilElementIsClickable(upperRight,20);
        upperRight.click();
    }
}
