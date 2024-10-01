package anhntTest.PageObjectModel.testcases;

import anhntTest.PageObjectModel.pages.LoginPage;
import anhntTest.PageObjectModel.pages.ClientsPage;
import anhntTest.common.BaseTest;
import anhntTest.constants.ConfigData;
import anhntTest.dataproviders.DataProviderFactory;
import anhntTest.setupData.Data;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;


public class ClientsTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initLoginPage() {
        loginPage = new LoginPage();

    }

    @Test(priority = 1, dataProvider = "data_Info_Clients", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testAddNewClients(Hashtable<String, String> dataClients) {
        loginPage.loginHRM(ConfigData.EMAIL, ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuClients()
                .enterAddNewClient(dataClients)
                .verifyAddNewClient(dataClients);
    }

    @Test(priority = 2, dataProvider = "data_Info_Clients", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testEditClients(Hashtable<String, String> dataClients) {
        loginPage.loginHRM(ConfigData.EMAIL, ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuClients()
                .enterEditClient(dataClients);
    }

    @Test(priority = 3, dataProvider = "data_Info_Clients", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testDeleteClients(Hashtable<String, String> dataClients) {
        loginPage.loginHRM(ConfigData.EMAIL, ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuClients()
                .deleteClient(dataClients)
                .checkDeleteClient(dataClients);
    }
    @Test(priority = 4, dataProvider = "data_Info_Clients", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testClientCreationFunctionalProcess(Hashtable<String, String> dataClients){
        loginPage.loginHRM(ConfigData.EMAIL, ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuClients()
                .enterAddNewClient(dataClients)
                .verifyAddNewClient(dataClients)
                .clickButtonLogOut()
                .loginHRM(dataClients.get("USERNAME")+"fail",dataClients.get("PASSWORD"))//đăng nhập với tài khoản khách sai tài khoản
                .verifyLoginFail("Invalid Login Credentials.")
                .loginHRM(dataClients.get("USERNAME"),dataClients.get("PASSWORD")+"fail")//đăng nhập với tài khoản khách sai mật khẩu
                .verifyLoginFail("Invalid Login Credentials.")
                .loginHRM(dataClients.get("USERNAME"),dataClients.get("PASSWORD")) //đăng nhập với tài khoản khách đúng
                .verifyLoginSuccess()
                .clickButtonLogOut()
                .loginHRM(ConfigData.EMAIL, ConfigData.PASSWORD)
                .clickMenuClients()
                .enterEditClient(dataClients) //Chỉnh sửa thông tin tài khoản khách
                .clickMenuClients()
                .deleteClient(dataClients)  //Xóa tài khoản khách khỏi hệ thống
                .checkDeleteClient(dataClients)
                .clickButtonLogOut()
                .loginHRM(dataClients.get("USERNAME"),dataClients.get("PASSWORD"))//đăng nhập với tài khoản khách đúng sau khi tài khoản đã bị xóa
                .verifyLoginFail("Invalid Login Credentials.");
    }
}
