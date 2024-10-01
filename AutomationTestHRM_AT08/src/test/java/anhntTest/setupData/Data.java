package anhntTest.setupData;

import anhntTest.constants.ConfigData;
import anhntTest.helpers.DataFakerHelper;
import anhntTest.helpers.ExcelHelper;
import anhntTest.helpers.SystemHelper;

public class Data {
    static DataFakerHelper dataFakerHelper;
    static ExcelHelper excelHelper;
    static boolean refreshData = ConfigData.REFRESH_DATA;

    private static String[] arrCountry = {"Albania", "United States", "Angola", "Singapore", "Australia", "Afghanistan"};
    private static String[] arrPriority = {"Highest", "High", "Normal", "Low"};
    private static String[] arrTeam = {"Admin Example", "Project Manager"};
    private static String[] arrClients = {"Tuấn Anh"};
    private static String[] arrSatus = {"Not Started","In Progress","Cancelled","On Hold","Completed"};
    private static String[] arrProject = {"DemoProject 01","DemoProject 02","DemoProject 03"};
    public static void setUpAllData() {
        setUpDataClients();
        setUpDataProjects();
        setUpDataEditProjectStatus();
        setUpDataTask();
        setUpDataAttach();
    }

    public static void setUpDataClients() {
        if (refreshData) {
            ExcelHelper excelHelper = new ExcelHelper();
            excelHelper.setExcelFile(SystemHelper.getCurrentDir() + "src/test/resources/exceldata/DataHRM.xlsx", "Clients");
            dataFakerHelper = new DataFakerHelper(ConfigData.FAKER_LANGUAGE);
            for (int i = 1; i <= ConfigData.AMOUNT_OF_DATA; i++) {
                excelHelper.setCellData(dataFakerHelper.getFirstName(), "FIRST NAME", i);
                excelHelper.setCellData(dataFakerHelper.getLastName(), "LAST NAME", i);
                excelHelper.setCellData(dataFakerHelper.getPassword() + "anhnt", "PASSWORD", i);
                excelHelper.setCellData(dataFakerHelper.getPhoneNumber().replaceAll("-", ""), "CONTACT", i);
                excelHelper.setCellData(dataFakerHelper.getGender(), "GENDER", i);
                excelHelper.setCellData(dataFakerHelper.getEmail(), "EMAIL", i);
                excelHelper.setCellData(dataFakerHelper.getUsername() + "anhnt", "USERNAME", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrCountry), "COUNTRY", i);
                excelHelper.setCellData(dataFakerHelper.getFullAddress(), "ADDRESS", i);
                excelHelper.setCellData(dataFakerHelper.getStreetAddress(), "ADDRESS 2", i);
                excelHelper.setCellData(dataFakerHelper.getCity(), "CITY", i);
                excelHelper.setCellData(dataFakerHelper.getState(), "STATE", i);
                excelHelper.setCellData(dataFakerHelper.getZipCode(), "ZIP", i);
                excelHelper.setCellData(dataFakerHelper.getAvailableStatus(), "STATUS", i);
                excelHelper.setCellData(SystemHelper.getCurrentDir() + "src/test/resources/files/images/demo01.png", "IMAGE", i);
            }
        }

    }

    public static void setUpDataProjects() {
        if (refreshData) {
            ExcelHelper excelHelper = new ExcelHelper();
            excelHelper.setExcelFile(SystemHelper.getCurrentDir() + "src/test/resources/exceldata/DataHRM.xlsx", "Projects");
            dataFakerHelper = new DataFakerHelper(ConfigData.FAKER_LANGUAGE);
            for (int i = 1; i <= ConfigData.AMOUNT_OF_DATA; i++) {
                excelHelper.setCellData(dataFakerHelper.getFirstName(), "TITLE", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrClients), "CLIENT", i);
                excelHelper.setCellData(dataFakerHelper.getRandomNumber(1, 10), "HOUR", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrPriority), "PRIORITY", i);
                excelHelper.setCellData(dataFakerHelper.getDate(), "START DATE", i);
                excelHelper.setCellData(dataFakerHelper.getDate(), "END DATE", i);
                excelHelper.setCellData("Summary Project: " + dataFakerHelper.getDescribe(Integer.parseInt(dataFakerHelper.getRandomNumber(15, 20))), "SUMMARY", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrTeam), "TEAM", i);
                excelHelper.setCellData(dataFakerHelper.getDescribe(Integer.parseInt(dataFakerHelper.getRandomNumber(7, 10))), "DESCRIPTION", i);
                excelHelper.setCellData(dataFakerHelper.getRandomNumber(0,100), "PROGRESS", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrSatus), "STATUS", i);

            }
        }
    }
    public static void setUpDataEditProjectStatus(){
        if (refreshData) {
            ExcelHelper excelHelper = new ExcelHelper();
            excelHelper.setExcelFile(SystemHelper.getCurrentDir() + "src/test/resources/exceldata/DataHRM.xlsx", "EditProjectStatus");
            dataFakerHelper = new DataFakerHelper(ConfigData.FAKER_LANGUAGE);
            for (int i = 1; i <= ConfigData.AMOUNT_OF_DATA; i++) {
                excelHelper.setCellData("DemoProject 0"+i, "PROJECT", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrPriority), "PRIORITY", i);
                excelHelper.setCellData(dataFakerHelper.getOptions(arrSatus), "STATUS", i);
                excelHelper.setCellData(dataFakerHelper.getRandomNumber(0,100), "PROGRESS", i);
            }
        }
    }

    public static void setUpDataTask() {
        if (refreshData) {
            ExcelHelper excelHelper = new ExcelHelper();
            excelHelper.setExcelFile(SystemHelper.getCurrentDir() + "src/test/resources/exceldata/DataHRM.xlsx", "Task");
            dataFakerHelper = new DataFakerHelper(ConfigData.FAKER_LANGUAGE);
            for (int i = 1; i <= ConfigData.AMOUNT_OF_DATA; i++) {
                excelHelper.setCellData("DemoProject 0"+i,"PROJECT",i);
                excelHelper.setCellData(dataFakerHelper.getFirstName(), "TITLE", i);
                excelHelper.setCellData(dataFakerHelper.getDate(), "START DATE", i);
                excelHelper.setCellData(dataFakerHelper.getDate(), "END DATE", i);
                excelHelper.setCellData(dataFakerHelper.getRandomNumber(3, 10), "HOUR", i);
                excelHelper.setCellData("Summary Task: " + dataFakerHelper.getDescribe(Integer.parseInt(dataFakerHelper.getRandomNumber(15, 20))), "SUMMARY", i);
                excelHelper.setCellData("Description Task: "+dataFakerHelper.getDescribe(Integer.parseInt(dataFakerHelper.getRandomNumber(7, 10))), "DESCRIPTION", i);
            }
        }
    }
    public static void setUpDataAttach(){
        if (refreshData) {
            ExcelHelper excelHelper = new ExcelHelper();
            excelHelper.setExcelFile(SystemHelper.getCurrentDir() + "src/test/resources/exceldata/DataHRM.xlsx", "AttachFile");
            dataFakerHelper = new DataFakerHelper(ConfigData.FAKER_LANGUAGE);
            for (int i = 1; i <= ConfigData.AMOUNT_OF_DATA; i++) {
                excelHelper.setCellData("DemoProject 0"+i,"PROJECT",i);
                excelHelper.setCellData(dataFakerHelper.getFirstName(), "TITLE", i);
                excelHelper.setCellData(SystemHelper.getCurrentDir() + "src/test/resources/files/images/demo01.png", "IMAGE", i);
            }
        }
    }
}
