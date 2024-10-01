package anhntTest.PageObjectModel.pages;

import anhntTest.drivers.DriverManager;
import anhntTest.keywords.WebUI;
import org.testng.Assert;

public class HomePage extends CommonPage{
    public HomePage verifyLoginSuccess(){
        Assert.assertTrue(WebUI.checkElementIsDisplay(buttonLogOut),"Button not found Sign out!");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://hrm.anhtester.com/erp/desk", "\uD83D\uDC1E FAIL!! The current url not match.");
        return this;
    }
    public LoginPage verifyLoginFail(String expectedMessage){
        Assert.assertTrue(WebUI.checkElementIsDisplay(toastMessage), "\uD83D\uDC1E FAIL!! The error message not display.");
        Assert.assertEquals(WebUI.getTextElement(toastMessage), expectedMessage, "\uD83D\uDC1E FAIL!! The content of error massge not match.");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://hrm.anhtester.com/", "\uD83D\uDC1E FAIL!! The current url not match.");
        return new LoginPage();
    }
}
