package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProfilesVisabilityTests extends TestBase {

    @BeforeMethod
    public void initTests() throws InterruptedException {
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
        System.out.println("'Boards' button text: " + driver
                .findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText());

        //---- Open Up_Right Menu ----
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        upRightMenu.click();
        waitUntilElementIsVisible(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"),10);

        //---- Open ProfileVisability Menu ----
        WebElement profileVisabilityMenu = driver
                .findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"));
        profileVisabilityMenu.click();
        waitUntilAllElementsAreVisible(By.xpath("//button[@data-test-id = 'header-member-menu-button']"),20);
        waitUntilElementIsClickable(By.xpath("//button[contains(text(),'Save')]"),10);


    }

    @Test
    public void lettersIconTest(){
        //--- Receive Upper Right Menu element---
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        WebElement upRightMenuText = upRightMenu.findElement(By.xpath(".//span"));
        //--- Receive list of necessary icons ---
        List<WebElement> iconsList = driver.findElements(By.xpath(createLocatorIconlist(USERNAME)));
        int counter = 0;
        for(WebElement element: iconsList)
            if (element.getText().equals(upRightMenuText.getText())) counter++;

        Assert.assertEquals(2,counter, "The text on the upper right icon and on the icon on profile is not the same");
    }

    @Test
    public void userNameDisplayingTest(){

        //--- Receive UserName after shtrudel without username value
        WebElement userNameAfterShtrudel = driver.findElement(By.xpath("//span[contains(text(),'@')]"));

        //--- Receive UserName from user name field
        WebElement userNameField = driver.findElement(By.xpath("//input[@name='username']"));
        System.out.println("userNameAfterShtrudel: " + userNameAfterShtrudel.getText());
        System.out.println("userNameField: " + userNameField.getAttribute("value"));

        Assert.assertTrue(userNameAfterShtrudel.getText().contains(USERNAME)&&userNameField.getAttribute("value").equals(USERNAME));
    }


    private String createLocatorIconlist(String username) {
        return "//div[@title='" + username + " (" + username + ")']//span";
    }


}
