package anhntTest.constants;

import anhntTest.helpers.PropertiesHelper;

public class ConfigData {
    public static String BROWSER = PropertiesHelper.getValue("BROWSER");
    public static String URL = PropertiesHelper.getValue("URL");
    public static String EMAIL = PropertiesHelper.getValue("EMAIL");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static int EXPLICIT_WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT_TIMEOUT"));
    public static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
    public static int STEP_TIME = Integer.parseInt(PropertiesHelper.getValue("STEP_TIME"));
    public static int NUMBER_THREAD = Integer.parseInt(PropertiesHelper.getValue("NUMBER_THREAD"));
    public static String SCREENSHOT_PATH = PropertiesHelper.getValue("SCREENSHOT_PATH");
    public static String RECORD_VIDEO_PATH = PropertiesHelper.getValue("RECORD_VIDEO_PATH");
    public static String SCREENSHOT_STEP_ALL = PropertiesHelper.getValue("SCREENSHOT_STEP_ALL");
    public static String RECORD_VIDEO = PropertiesHelper.getValue("RECORD_VIDEO");
    public static String SCREENSHOT_STEP_FAIL = PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL");
    public static String SCREENSHOT_STEP_PASS = PropertiesHelper.getValue("SCREENSHOT_STEP_PASS");
    public static String FAKER_LANGUAGE = PropertiesHelper.getValue("FAKER_LANGUAGE");
    public static boolean PARALLEL = Boolean.parseBoolean(PropertiesHelper.getValue("PARALLEL"));
    public static String EXCEL_FILE_DATA_PATH =  PropertiesHelper.getValue("EXCEL_FILE_DATA_PATH");
    public static boolean REFRESH_DATA = Boolean.parseBoolean(PropertiesHelper.getValue("REFRESH_DATA"));
    public static int AMOUNT_OF_DATA = Integer.parseInt(PropertiesHelper.getValue("AMOUNT_OF_DATA"));
}
