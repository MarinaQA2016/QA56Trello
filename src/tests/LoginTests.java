package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
    }

    @Test
    public void loginTestPositive()  {
        loginPage.openLoginPage();
        loginPage.enterLoginAtlassianAndClickLogin(LOGIN);
        loginPage.enterPasswordAtlassionAndClickLogin(PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertEquals(boardsPage.getButtonBoardsText(),"Boards","Text on the boardIcon is not 'Boards'");
    }


    @Test
    public void loginNegativeNoLoginNoPassword()  {
        loginPage.openLoginPage();
        loginPage.pressLoginButton();
        loginPage.waitErrorMessage();

        Assert.assertEquals("Missing email",loginPage.getErrorMessage(),"The text of the message is not 'Missing email'");
    }


    @Test
    public void NegativeLoginIncorrect() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsVisible(By.xpath("//input[@id='login']"),10);

        //--- Enter Incorrect Login
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("ttt@test.com");


        //----Click 'Log in' button ----
        driver.findElement(By.id("login")).click();
        waitUntilElementIsVisible(By.xpath("(//*[@class= 'error-message'])[1]"),15);
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertEquals(errorMessage.getText(), "There isn't an account for this email","Error message is not correct");
    }

    @Test
    public void NegativePasswordIncorrect() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsVisible(By.xpath("//input[@id='login']"),10);

        //--- Enter Correct Login
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        waitUntilAttributeValueIs(By.
                id("login"),"value","Log in with Atlassian",10);

        //----Click 'Log in' button ----
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("login-submit"),15);

        //---Enter incorrect password ---
        WebElement passwordLogin = driver.findElement(By.id("password"));
        passwordLogin.sendKeys("error");
        driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsVisible(By.xpath("//div[@id='login-error']/span"),15);

        //---Print error message ---
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(errorMessage.getText().contains("Incorrect email address and / or password."),
                "There is no error message or the text of the message is not correct");
    }


}
