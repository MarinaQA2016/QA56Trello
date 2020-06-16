package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginTestPositive() throws InterruptedException {

        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //----Enter login value and click 'Log in' button ----
        driver.findElement(By.id("user")).sendKeys(LOGIN);
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);

        //---- Enter password value and click 'Log in' button
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(35000);
        WebElement boardIcon = driver.findElement(By
                .xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"));
        System.out.println("'Boards' button text: " + boardIcon.getText());
        Thread.sleep(5000);
        Assert.assertEquals("Boards",boardIcon.getText(),"Text on the boardIcon is not 'Boards'");

    }

    @Test
    public void loginNegativeNoLoginNoPassword() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //----Click 'Log in' button ----

        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        System.out.println("Error message: " + driver.findElement(By.cssSelector("#error>p")).getText());
        Thread.sleep(5000);
    }

    @Test
    public void NegativeLoginIncorrect() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //--- Enter Incorrect Login
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("test@test.com");
        Thread.sleep(7000);

        //----Click 'Log in' button ----

        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id = 'error']/p"));
        System.out.println("Error message: " + errorMessage.getText());
        Thread.sleep(5000);
    }

    @Test
    public void NegativePasswordIncorrect() throws InterruptedException {
        //--- Press log In menu button
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        //--- Enter Correct Login
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        Thread.sleep(5000);

        //----Click 'Log in' button ----
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);

        //---Enter incorrect password ---
        WebElement passwordLogin = driver.findElement(By.id("password"));
        passwordLogin.sendKeys("error");
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(5000);
        //---Print error message ---
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        System.out.println("Error message: " + errorMessage.getText());
        Thread.sleep(5000);
    }




}
