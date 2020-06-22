package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginTestPositive()  {

        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        //----Enter login value and click 'Log in' button ----
        driver.findElement(By.id("user")).sendKeys(LOGIN);
        waitUntilAttributeValueIs(By.
                id("login"),"value","Log in with Atlassian",10);
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("login-submit"),15);

        //---- Enter password value and click 'Log in' button
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By
                .xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"),40);
        WebElement boardIcon = driver.findElement(By
                .xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"));

        Assert.assertEquals(boardIcon.getText(),"Boards","Text on the boardIcon is not 'Boards'");

    }


    @Test
    public void loginNegativeNoLoginNoPassword()  {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        //----Click 'Log in' button ----
        driver.findElement(By.id("login")).click();
        waitUntilElementIsVisible(By.cssSelector("#error>p"),10);
        WebElement errorMessage = driver.findElement(By.cssSelector("#error>p"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(errorMessage.getText().equals("Missing email"));
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
