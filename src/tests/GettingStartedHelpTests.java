package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class GettingStartedHelpTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    UpperMenuHelper upperMenuPage;
    HelpSectionPageHelper helpSectionPage;
    GettingStartedGuideHelper gettingStartedGuidePage;

    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        upperMenuPage = PageFactory.initElements(driver, UpperMenuHelper.class);
        helpSectionPage = PageFactory.initElements(driver,HelpSectionPageHelper.class);
        gettingStartedGuidePage = PageFactory.initElements(driver,GettingStartedGuideHelper.class);

        loginPage.openLoginPage();
        loginPage.loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        upperMenuPage.openMenuPage();
        upperMenuPage.waitUntilPageIsLoaded();
        upperMenuPage.openHelpMenu();
        helpSectionPage.waitUntilPageIsLoaded();


    }
    @Test
    public void gettingStartedGuideHelpOpening()  {
        helpSectionPage.chooseGettingStartedGuideMenu();
        gettingStartedGuidePage.switchToNewWindowAndWaitPageLoading();
        Assert.assertEquals(gettingStartedGuidePage.getTitle(),"Getting Started with Trello",
                "Actual title is not equal to expected title");


    }
}
