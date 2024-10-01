package anhntTest.PageObjectModel.pages;

import anhntTest.drivers.DriverManager;
import anhntTest.constants.ConfigData;
import anhntTest.helpers.ExcelHelper;
import anhntTest.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends CommonPage {

    //Hàm xây dựng cho từng class Page
    public LoginPage() {
    }

    private By buttonLogin = By.xpath("//span[normalize-space()='Login']");
    private By buttonLogOut = By.xpath("//div[@class='page-header']//a[normalize-space()='Logout']");


    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputUsername = By.xpath("//input[@id='iusername']");
    private By inputPassword = By.xpath("//input[@id='ipassword']");

    public HomePage loginHRM(int row) {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA_PATH,"Login");
        WebUI.openURL(ConfigData.URL);
        WebUI.waitForPageLoaded();
        enterEmail(excelHelper.getCellData("EMAIL",row));
        enterPassword(excelHelper.getCellData("PASSWORD",row));
        clickLoginButton();
        WebUI.waitForPageLoaded();
        for (int i = 0; i < 1; i++) {
            if (WebUI.checkElementIsDisplay(toastMessage)) {
                if (WebUI.getTextElement(toastMessage).contains("Max no. of attempts. Try again after a minute.")) {
                    WebUI.sleep(10);
                    clickLoginButton();
                    i--;
                } else break;
            } else break;
        }
        return new HomePage();
    }
    public HomePage loginHRM(String email, String pass) {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA_PATH,"Login");
        WebUI.openURL(ConfigData.URL);
        WebUI.waitForPageLoaded();
        enterEmail(email);
        enterPassword(pass);
        clickLoginButton();
        WebUI.waitForPageLoaded();
        for (int i = 0; i < 1; i++) {
            if (WebUI.checkElementIsDisplay(toastMessage,5)) {
                if (WebUI.getTextElement(toastMessage).contains("Max no. of attempts. Try again after a minute.")) {
                    WebUI.sleep(10);
                    clickLoginButton();
                    i--;
                } else break;
            } else break;
        }
        return new HomePage();
    }

    public void enterEmail(String email) {
        WebUI.clearText(inputUsername);
        WebUI.setText(inputUsername, email);
    }

    public void enterPassword(String password) {
        WebUI.clearText(inputPassword);
        WebUI.setText(inputPassword, password);
    }

    public LoginPage clickLoginButton() {
        WebUI.clickElement(buttonLogin);
        return this;
    }

}
