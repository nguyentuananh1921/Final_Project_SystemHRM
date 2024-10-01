package anhntTest.PageObjectModel.testcases;

import anhntTest.PageObjectModel.pages.LoginPage;
import anhntTest.common.BaseTest;
import anhntTest.constants.ConfigData;
import anhntTest.dataproviders.DataProviderFactory;
import anhntTest.setupData.Data;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ProjectsTest extends BaseTest {
    LoginPage loginPage;
    @BeforeMethod
    public void initLogin(){
        loginPage = new LoginPage();
    }

    @Test(priority = 1,dataProvider = "data_Info_Project", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testAddNewProject(Hashtable<String, String> dataProject){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .addNewProject(dataProject)
                .verifyAddNewProject(dataProject);
    }

    @Test(priority = 2,dependsOnMethods = "testAddNewProject", threadPoolSize = 2,dataProvider = "data_Info_Project", dataProviderClass = DataProviderFactory.class)
    public void testEditProject(Hashtable<String, String> dataProject){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .EnterEditProject(dataProject)
                .clickMenuProject()
                .verifyEditProject(dataProject);
    }

    @Test(priority = 3,dependsOnMethods = "testEditProject", threadPoolSize = 2,dataProvider = "data_Info_Project", dataProviderClass = DataProviderFactory.class)
    public void testDeleteProject(Hashtable<String, String> dataProject){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .deleteProject(dataProject)
                .verifyDeleteProject(dataProject);
    }

    @Test(priority = 4,dependsOnMethods = "testDeleteProject", threadPoolSize = 2,dataProvider = "data_Info_Project", dataProviderClass = DataProviderFactory.class)
    public void testProjectCreationFunctionalProcess(Hashtable<String, String> dataProject){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .addNewProject(dataProject)
                .verifyAddNewProject(dataProject)
                .clickMenuProject()
                .EnterEditProject(dataProject)
                .clickMenuProject()
                .verifyEditProject(dataProject)
                .clickMenuProject()
                .deleteProject(dataProject)
                .verifyDeleteProject(dataProject);
    }
    @Test(priority = 5,dataProvider = "data_Info_Project", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testAddNewTask(Hashtable<String, String> dataTask){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .addProjectTask(dataTask)
                .verifyAddTask(dataTask);
    }
    @Test(priority = 6,dataProvider = "data_Info_EditProjectStatus", threadPoolSize = 2, dataProviderClass = DataProviderFactory.class)
    public void testEditProjectStatus(Hashtable<String, String> dataProject){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .editProjectStatus(dataProject)
                .clickMenuProject()
                .verifyEditProjectStatus(dataProject);
    }

    @Test(priority = 7,dataProvider = "data_AttachFile", dataProviderClass = DataProviderFactory.class,threadPoolSize = 2)
    public void testAddAttachFile(Hashtable<String, String> dataAttachFile){
        loginPage.loginHRM(ConfigData.EMAIL,ConfigData.PASSWORD)
                .verifyLoginSuccess()
                .clickMenuProject()
                .addAttachFile(dataAttachFile)
                .verifyAddAttachFile(dataAttachFile);
    }

}
