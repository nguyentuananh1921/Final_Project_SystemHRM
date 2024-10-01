package anhntTest.PageObjectModel.testcases;

import anhntTest.PageObjectModel.pages.LoginPage;
import anhntTest.common.BaseTest;
import anhntTest.constants.ConfigData;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void testLoginHRMSuccess(){
        loginPage = new LoginPage();
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
        .verifyLoginSuccess();
    }

    @Test
    @Parameters({"row"})
    public void testLoginInvalidUsername(@Optional("2") int row){
        loginPage = new LoginPage();
        loginPage.loginHRM(row)
                .verifyLoginFail("Invalid Login Credentials.");
    }

    @Test
    @Parameters({"row"})
    public void testLoginInvalidPassword(@Optional("3") int row){
        loginPage = new LoginPage();
        loginPage.loginHRM(row)
        .verifyLoginFail("Invalid Login Credentials.");
    }
}
