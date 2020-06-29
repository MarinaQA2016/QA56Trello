package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ActivityMenuTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qaHaifa56Page;
    UpperMenuHelper upperMenuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        qaHaifa56Page = new CurrentBoardHelper(driver,BOARD_TITLE);
        upperMenuPage = PageFactory.initElements(driver, UpperMenuHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);

        loginPage.openLoginPage();
        loginPage.loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        qaHaifa56Page.openCurrentBoard();
        qaHaifa56Page.waitUntilPageIsLoaded();
    }
    @Test
    public void addingNewListEventInActivity(){
        String listTitle = "Activity new";
        qaHaifa56Page.createNewList(listTitle);
        upperMenuPage.openMenuPage();
        upperMenuPage.waitUntilPageIsLoaded();
        upperMenuPage.openActivityPage();
        activityPage.waitUntilPageIsLoaded();

        Assert.assertTrue(activityPage.getLastActivityText().contains(" added list "+ listTitle + " to "),
                "The text in the last activity record doesn't correspond to event adding new list " + listTitle );




    }
}
