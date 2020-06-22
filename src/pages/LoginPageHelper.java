package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageHelper extends PageBase{

    public LoginPageHelper(WebDriver driver){
        super(driver);
    }

    public void openLoginPage(){
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);
    }

    public void enterLoginAtlassianAndClickLogin(String login) {
        driver.findElement(By.id("user")).sendKeys(login);
        waitUntilAttributeValueIs(By.
                id("login"),"value","Log in with Atlassian",10);
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("login-submit"),15);
    }

    public void enterPasswordAtlassionAndClickLogin(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-submit")).click();
    }

    public void loginAsAtlassian(String login, String password){
        this.enterLoginAtlassianAndClickLogin(login);
        this.enterPasswordAtlassionAndClickLogin(password);
    }


    public void pressLoginButton() {
        driver.findElement(By.id("login")).click();
    }

    public void waitErrorMessage() {
        waitUntilElementIsVisible(By.cssSelector("#error>p"),10);
    }

    public String getErrorMessage(){
        WebElement errorMessage = driver.findElement(By.cssSelector("#error>p"));
        return errorMessage.getText();
    }
}
