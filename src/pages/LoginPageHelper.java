package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;

public class LoginPageHelper extends PageBase{
    @FindBy(linkText = "Log In")
    WebElement logInIcon;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "user")
    WebElement userField;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(css = "#error>p")
    WebElement noLoginNoPasswordError;


    public LoginPageHelper(WebDriver driver){
        super(driver);
    }

    public void openLoginPage(){
        logInIcon.click();
        waitUntilElementIsClickable(loginButton,10);
    }

    public void enterLoginAtlassianAndClickLogin(String login) {
        userField.sendKeys(login);
        waitUntilAttributeValueIs(loginButton,"value","Log in with Atlassian",10);
        loginButton.click();
        waitUntilElementIsClickable(loginSubmitButton,15);
    }

    public void enterPasswordAtlassionAndClickLogin(String password) {

        passwordField.sendKeys(password);
        loginSubmitButton.click();

    }

    public void loginAsAtlassian(String login, String password){
        this.enterLoginAtlassianAndClickLogin(login);
        this.enterPasswordAtlassionAndClickLogin(password);
    }


    public void pressLoginButton() {
        //driver.findElement(By.id("login")).click();
        loginButton.click();
    }

    public void waitErrorMessage() {
        waitUntilElementIsVisible(noLoginNoPasswordError,10);

    }

    public String getErrorMessage(){
        //WebElement errorMessage = driver.findElement(By.cssSelector("#error>p"));
        return noLoginNoPasswordError.getText();
    }

    public void enterLoginNormal(String login) {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(login);
        loginField.sendKeys(Keys.ENTER);
    }

    public void clickLoginButtonNormal() {
        waitUntilElementIsClickable(loginButton,15);
        //System.out.println("Is loginButton is clickable: " + loginButton.isEnabled());
        loginButton.click();
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
