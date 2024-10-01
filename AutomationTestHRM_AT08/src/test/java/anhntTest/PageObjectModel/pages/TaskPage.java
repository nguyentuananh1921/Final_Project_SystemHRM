package anhntTest.PageObjectModel.pages;

import anhntTest.keywords.WebUI;
import org.openqa.selenium.By;

import java.util.Hashtable;

public class TaskPage extends CommonPage{
    private By inputTitle = By.xpath("//input[@name='task_name']");
    private By selectProject = By.xpath("//select[@name='project_id']");
    public TaskPage addNewTask(Hashtable<String, String> data){
        clickButtonAddNew();
        WebUI.setText(inputTitle,data.get("TITLE"));
        setTextStartDate(data.get("START DATE"));
        setTextEndtDate(data.get("END DATE"));
        WebUI.selectOption(selectProject,data.get("PROJECT"));
        setTextAddSummary(data.get("SUMMARY"));
        setTextDescription(data.get("DESCRIPTION"));
        clickButtonSave();
        return this;
    }
    public void verifyAddNewTask(){

    }
}
