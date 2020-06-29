package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ActivityPageHelper extends PageBase {
    @FindBy(xpath = "//div[@class = 'phenom-desc']")
    List<WebElement> activityRecordsList;

    public ActivityPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
    waitUntilAllElementsAreVisible(activityRecordsList,30);
    }

    public String getLastActivityText() {
        return activityRecordsList.get(0).getText();
    }
}
