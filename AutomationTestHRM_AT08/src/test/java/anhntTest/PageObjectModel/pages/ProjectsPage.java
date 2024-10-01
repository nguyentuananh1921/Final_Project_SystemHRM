package anhntTest.PageObjectModel.pages;

import anhntTest.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Hashtable;

public class ProjectsPage extends CommonPage {
    private By inputTitle = By.xpath("//input[@name='title']");
    private By selectClients = By.xpath("//select[@id='client_id']");
    private By inputEstimatedHour = By.xpath("//input[@name='budget_hours']");
    private By selectPriority = By.xpath("//select[@name='priority']");
    private By selectTeam = By.xpath("//select[@name='assigned_to[]']");
    private By buttonUpdateProject = By.xpath("//span[normalize-space()='Update Project']");

    private By textTitle = By.xpath("//table//tr//td[normalize-space()='Title']/following-sibling::td");
    private By textSummary = By.xpath("//div[h6[text()='Summary']]");
    private By texDescription = By.xpath("//div[h6[text()='Description']]");

    private By buttonTask = By.xpath("//a[@id='pills-tasks-tab']");
    private By buttonAttachFile = By.xpath("//a[normalize-space()='Attach files']");
    private By inputTitleFile = By.xpath("//input[@name='file_name']");
    private By inputFilePath = By.xpath("//input[@id='attachment_file']");
    private By inputTaskName = By.xpath("//input[@name='task_name']");
    private By inputStartDateTask = By.xpath("//form[@id='add_task']//input[@name='start_date']");
    private By inputEndDateTask = By.xpath("//form[@id='add_task']//input[@name='end_date']");
    private By inputEstimatedHourTask = By.xpath("//input[@name='task_hour']");
    private By inputSummaryTask = By.xpath("//textarea[@id='summary']");
    private By inputDescriptionTask = By.xpath("//form[@id='add_task']//iframe[@title='Editable area. Press F10 for toolbar.']");


    private By progressVal = By.xpath("//input[@id='progres_val']");
    private By textProgress = By.xpath("//span[@class='irs-single']");
    private By startPoint = By.xpath("//span[@class='irs-min']");
    private By endPoint = By.xpath("//span[@class='irs-max']");
    private By selectStatus = By.xpath("//select[@name='status']");
    private By buttonupdateStatus = By.xpath("//span[normalize-space()='Update Status']");
    private By textStatus = By.xpath("//form[@id='update_project_progress']//div[@class='br-current-rating']");
    private By buttonAddFile = By.xpath("//div[@id='pills-files']//button[@type='submit']");
    private By textTitleAttachFile = By.xpath("//h5[contains(text(),'Project :')]");


    private String messageAddProjectSuccess = "Project added.";
    private String messageDeleteProjectSuccess = "Project deleted.";


    public ProjectsPage addNewProject(Hashtable<String, String> data) {
        clickButtonAddNew();
        WebUI.setText(inputTitle, data.get("TITLE"));
        WebUI.selectOption(selectClients, data.get("CLIENT"));
        WebUI.setText(inputEstimatedHour, data.get("HOUR"));
        WebUI.selectOption(selectPriority, data.get("PRIORITY"));
        setTextStartDate(data.get("START DATE"));
        setTextEndtDate(data.get("END DATE"));
        setTextAddSummary(data.get("SUMMARY"));
        WebUI.selectOption(selectTeam, data.get("TEAM"));
        setTextDescription(data.get("DESCRIPTION"));
        clickButtonSave();
        return this;
    }

    public ProjectsPage verifyAddNewProject(Hashtable<String, String> data) {
        verifyContentMessage(messageAddProjectSuccess);
        setTextInputSearch(data.get("TITLE"));
        WebUI.checkDataInTableByColumn_Contains(1, data.get("TITLE"), "Project title", "//div[@id='xin_table_wrapper']");
        return this;
    }

    public ProjectsPage EnterEditProject(Hashtable<String, String> data) {
        setTextInputSearch(data.get("TITLE"));
        clickButtonViewDtails();
        clickButtonEditTab();
        WebUI.sleep(1);
        setTextUpdateSummary(data.get("SUMMARY") + "edited");
        setTextDescription(data.get("DESCRIPTION") + "edited");
        WebUI.clickElement(buttonUpdateProject);
        return this;
    }

    public ProjectsPage verifyEditProject(Hashtable<String, String> data) {
        setTextInputSearch(data.get("TITLE"));
        clickButtonViewDtails();
        SoftAssert softAssert = new SoftAssert();
        WebUI.softAssertEquals(softAssert,WebUI.getTextElement(textSummary).replaceFirst("Summary", "").trim(), (data.get("SUMMARY").trim() + "edited").trim(), "Summary does not match!");
        WebUI.softAssertEquals(softAssert,WebUI.getTextElement(texDescription).replaceFirst("Description", "").trim(), (data.get("DESCRIPTION") + "edited").trim(), "Summary does not match!");
        WebUI.softAssertAll(softAssert);
        return this;
    }

    public ProjectsPage deleteProject(Hashtable<String, String> data) {
        setTextInputSearch(data.get("TITLE"));
        clickButtonFirstDelete();
        clickButtonConfirm();
        return this;
    }

    public ProjectsPage verifyDeleteProject(Hashtable<String, String> data) {
        verifyContentMessage(messageDeleteProjectSuccess);
        /*setTextInputSearch(data.get("TITLE"));
        int totalRecordsBeforeDelete = getTotalRecords();
        clickButtonFirstDelete();
        clickButtonConfirm();
        setTextInputSearch(data.get("TITLE"));
        if (totalRecordsBeforeDelete>1){
            int totalRecordsAfterDelete = getTotalRecords();
            WebUI.assertEquals(totalRecordsBeforeDelete - 1,totalRecordsAfterDelete,"Line number does not decrease after deletion!");
        }
        else {
            checkNoRecordsAvailable();
        }*/
        return this;
    }

    public ProjectsPage clickButtonTask() {
        WebUI.clickElement(buttonTask);
        return this;
    }

    public ProjectsPage clickButtonAttachFile() {
        WebUI.clickElement(buttonAttachFile);
        return this;
    }

    public ProjectsPage addProjectTask(Hashtable<String, String> data) {
        clickButtonViewDtails();
        clickButtonTask();
        clickButtonAddNew();
        WebUI.setText(inputTaskName, data.get("TITLE"));
        WebUI.chooseDate(inputStartDateTask, data.get("START DATE"));
        WebUI.chooseDate(inputEndDateTask, data.get("END DATE"));
        WebUI.setText(inputEstimatedHourTask, data.get("HOUR"));
        WebUI.setText(inputSummaryTask, data.get("SUMMARY"));
        WebUI.setText(inputDescriptionTask, data.get("DESCRIPTION"));
        clickButtonSave();
        return this;
    }

    public ProjectsPage verifyAddTask(Hashtable<String, String> data) {
        verifyContentMessage("Task added.");
        setTextInputSearch(data.get("TITLE"));
        WebUI.checkDataInTableByColumn_Contains(1, data.get("TITLE"), "Task name", "//div[@id='xin_table_wrapper']");
        return this;
    }

    public ProjectsPage editProjectStatus(Hashtable<String, String> data) {
        setTextInputSearch(data.get("PROJECT"));
        clickButtonViewDtails();
        WebUI.setElementAttribute(progressVal,"value",data.get("PROGRESS"));
        /*WebUI.dragAndDrop(textProgress,startPoint);
        double distance = WebUI.calculateDistance(startPoint, endPoint);
        WebUI.dragAndDropOffset(textProgress, (int) Math.ceil((distance / 100 * Integer.parseInt(data.get("PROGRESS")))), 0);*/
        WebUI.selectByVisibleText(selectStatus, data.get("STATUS"));
        WebUI.clickElement(buttonupdateStatus);
        return this;
    }

    public ProjectsPage verifyEditProjectStatus(Hashtable<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        setTextInputSearch(data.get("PROJECT"));
        clickButtonViewDtails();
        String valueProgress = WebUI.getTextElement(textProgress);
        WebUI.softAssertEquals(softAssert,valueProgress, data.get("PROGRESS"), "The process is not correct!");
        String status = WebUI.getTextElement(textStatus);
        WebUI.softAssertEquals(softAssert,status, data.get("STATUS"), "The status is not correct!");
        WebUI.softAssertAll(softAssert);
        return this;
    }

    public ProjectsPage addAttachFile(Hashtable<String, String> data) {
        setTextInputSearch(data.get("PROJECT"));
        clickButtonViewDtails();
        clickButtonAttachFile();
        double distance = WebUI.calculateDistance(textTitleAttachFile,inputTitleFile);
        WebUI.hoverElement(textTitleAttachFile);
        WebUI.scrollToPosition(0,(int) distance);
        WebUI.setText(inputTitleFile, data.get("TITLE"));
        WebUI.uploadFileSendKey(inputFilePath, data.get("IMAGE"));
        WebUI.clickElement(buttonAddFile);
        return this;
    }

    public ProjectsPage verifyAddAttachFile(Hashtable<String, String> data) {
        verifyContentMessage("Project file added.");
        return this;
    }

}
