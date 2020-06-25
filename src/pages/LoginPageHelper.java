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

    public void enterLoginNormal(String login) {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(login);
    }

    public void clickLoginButtonNormal() {
        driver.findElement(By.id("login")).click();
    }

    public void waitErrorMessageLoginIncorrect() {
        waitUntilElementIsVisible(By.xpath("(//*[@class= 'error-message'])[1]"),30);
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        System.out.println("Error message: " + errorMessage.getText());
    }

    public String getErrorMessageloginIncorrect() {
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        return errorMessage.getText();
    }

    public void waitErrorMessagePasswordIncorrect() {
        WebElement errorMessageIncorrectPassword;
        waitUntilElementIsVisible(By.xpath("//div[@id='login-error']/span"),15);
    }

    public String getIncorrectPassswordMessage(){
        WebElement errorMessageIncorrectPassword = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        return errorMessageIncorrectPassword.getText();
    }
}
