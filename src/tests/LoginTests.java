package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.LoginPageHelper;

public class LoginTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests(){
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        loginPage.openLoginPage();
    }

    @Test
    public void loginTestPositive()  {
        loginPage.enterLoginAtlassianAndClickLogin(LOGIN);
        loginPage.enterPasswordAtlassionAndClickLogin(PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertEquals(boardsPage.getButtonBoardsText(),"Boards","Text on the boardIcon is not 'Boards'");
    }


    @Test
    public void loginNegativeNoLoginNoPassword()  {
        loginPage.pressLoginButton();
        loginPage.waitErrorMessage();

        Assert.assertEquals("Missing email",loginPage.getErrorMessage(),"The text of the message is not 'Missing email'");
    }


    @Test
    public void NegativeLoginIncorrect()  {
        loginPage.enterLoginNormal("ttt@test.com");
        loginPage.clickLoginButtonNormal();
        loginPage.waitErrorMessageLoginIncorrect();

        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),"There isn't an account for this email","Error message is not correct");
    }

    @Test
    public void NegativePasswordIncorrect()  {
        loginPage.loginAsAtlassian(LOGIN,"error");
        loginPage.waitErrorMessagePasswordIncorrect();

        //---Print error message ---
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(loginPage.getIncorrectPassswordMessage().contains("Incorrect email address and / or password."),
                "There is no error message or the text of the message is not correct");
    }


}
