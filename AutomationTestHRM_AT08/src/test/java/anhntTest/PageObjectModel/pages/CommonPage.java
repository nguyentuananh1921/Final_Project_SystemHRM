package anhntTest.PageObjectModel.pages;

import anhntTest.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CommonPage {

    private LoginPage loginPage;
    private ClientsPage clientsPage;
    private HomePage homePage;

    public CommonPage() {

    }
    public By menuHome =By.xpath("//span[normalize-space()='Home']");
    public By menuTask = By.xpath("//span[normalize-space()='Tasks']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuClients = By.xpath("//span[normalize-space()='Manage Clients']");

    public By buttonAddNew = By.xpath("//a[normalize-space()='Add New']");
    private By inputStartDate = By.xpath("//input[@name='start_date']");
    private By inputEndDate = By.xpath("//input[@name='end_date']");
    private By inputAddSummary = By.xpath("//form[contains(@name,'add')]//textarea[@name='summary']");
    private By inputUpdateSummary = By.xpath("//form[contains(@name,'update')]//textarea[@name='summary']");
    private By iframeDescription = By.xpath("//iframe[@title='Editable area. Press F10 for toolbar.']");
    private By inputDescription = By.xpath("//body");
    public By buttonSave = By.xpath("//span[normalize-space()='Save']");
    public By inputSearch = By.xpath("//div[@id='xin_table_filter']//input[@type='search']");

    public By buttonLogOut = By.xpath("//div[@class=\"page-header\"]//a[normalize-space()='Logout']");
    public By toastMessage = By.xpath("//div[@class='toast-message']");
    public By buttonConfirm = By.xpath("//span[normalize-space()='Confirm']");

    public By buttonFirstViewDetails = By.xpath("//table/tbody/tr[1]//span[@data-original-title='View Details']");
    public By buttonFirstDelete = By.xpath("//table/tbody/tr[1]//span[@data-original-title='Delete']");

    public By buttonEditTab = By.xpath("//a[normalize-space()='Edit']");

    public By textNoRecordsAvailable = By.xpath("//table/tbody/tr/td[normalize-space()='No records available']");
    public By textRecords = By.xpath("//div[@id='xin_table_info']");


    public LoginPage getLoginPage() {
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public CommonPage clickButtonAddNew(){
        WebUI.clickElement(buttonAddNew);
        return this;
    }
    public CommonPage setTextStartDate(String date){
        WebUI.chooseDate(inputStartDate,date);
        return this;
    }
    public CommonPage setTextEndtDate(String date){
        WebUI.chooseDate(inputEndDate,date);
        return this;
    }
    public CommonPage setTextAddSummary(String content){
        WebUI.setText(inputAddSummary,content);
        return this;
    }
    public CommonPage setTextUpdateSummary(String content){
        WebUI.clearAndSetText(inputUpdateSummary,content);
        return this;
    }
    public CommonPage setTextDescription(String content){
        WebUI.goToIframe(iframeDescription);
        WebUI.clearAndSetText(inputDescription, content);
        WebUI.exitIframe();
        return this;
    }

    public CommonPage setTextInputSearch(String value){
        WebUI.clearAndSetText(inputSearch,value);
        return this;
    }
    public TaskPage clickMenuTask(){
        WebUI.clickElement(menuTask);
        return new TaskPage();
    }
    public ProjectsPage clickMenuProject(){
        WebUI.clickElement(menuProjects);
        return new ProjectsPage();
    }

    public ClientsPage clickMenuClients(){
        WebUI.hoverElement(menuHome);
        WebUI.scrollToElement(menuClients,"500");
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuClients);
        return new ClientsPage();
    }
    public HomePage getHomePage(){
        if (homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }
    public void clickButtonSave(){
        WebUI.clickElement(buttonSave);
    }
    public void clickButtonConfirm() {
        WebUI.clickElement(buttonConfirm);
    }
    public LoginPage clickButtonLogOut(){
        WebUI.clickElement(buttonLogOut);
        return  new LoginPage();
    }
    public CommonPage verifyContentMessage(String contentMessage) {
        Assert.assertTrue(WebUI.checkElementIsDisplay(toastMessage), "\uD83D\uDC1E FAIL!! The error message not display.");
        Assert.assertEquals(WebUI.getTextElement(toastMessage), contentMessage, "\uD83D\uDC1E FAIL!! The content of error massge not match.");
        return this;
    }
    public CommonPage clickButtonViewDtails() {
        WebUI.hoverAndClickElement(buttonFirstViewDetails);
        return this;
    }

    public CommonPage clickButtonFirstDelete() {
        WebUI.hoverAndClickElement(buttonFirstDelete);
        return this;
    }

    public CommonPage checkNoRecordsAvailable(){
        Assert.assertTrue(WebUI.checkElementIsDisplay(textNoRecordsAvailable),"The data is still displayed in the table!");
        return this;
    }
    
    public CommonPage clickButtonEditTab(){
        WebUI.clickElement(buttonEditTab);
        return this;
    }
    public int getTotalRecords(){
        String records = WebUI.getTextElement(textRecords);
        System.out.println(records);
        return Integer.parseInt(records.substring(records.indexOf("of")+2,records.indexOf("records")).trim());
    }

}
