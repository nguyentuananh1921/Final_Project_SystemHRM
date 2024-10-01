package anhntTest.keywords;

import anhntTest.constants.ConfigData;
import anhntTest.drivers.DriverManager;
import anhntTest.helpers.CaptureHelper;
import anhntTest.helpers.PropertiesHelper;
import anhntTest.helpers.SystemHelper;
import anhntTest.reports.AllureManager;
import anhntTest.reports.ExtentTestManager;
import anhntTest.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = ConfigData.EXPLICIT_WAIT_TIMEOUT;
    private static double STEP_TIME = ConfigData.STEP_TIME;
    private static int PAGE_LOAD_TIMEOUT = ConfigData.PAGE_LOAD_TIMEOUT;

    @Step("Check data: {1} in Table by column {2}")
    public static void checkDataInTableByColumn_Contains(int column, String value, String columnName, String additionalXpath) {
        LogUtils.info("\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
        ExtentTestManager.logMessage(Status.INFO, "\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
        //Xác định số dòng của table sau khi search
        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath(additionalXpath + "//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        LogUtils.info("Số dòng tìm thấy: " + rowTotal);
        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath(additionalXpath + "//table//tbody/tr[" + i + "]/td[" + column + "]"));
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);
            LogUtils.info(value + " - ");
            LogUtils.info(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }

    @Step("Check data: {1} in Table by column {2}")
    public static void checkDataInTableByColumn_Equals(int column, String value, String columnName) {
        LogUtils.info("\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
        ExtentTestManager.logMessage(Status.INFO, "\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
        //Xác định số dòng của table sau khi search
        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        LogUtils.info("Số dòng tìm thấy: " + rowTotal);
        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);
            System.out.print(value + " - ");
            LogUtils.info(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().equals(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }

    @Step("Select option by: {0} with value: {1}")
    public static void selectOption(By by, String value) {
        Select select = new Select(getWebElement(by));
        select.selectByVisibleText(value);
        LogUtils.info("Select option By " + by +"with value: " +value);
    }

    public static void selectByJs(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("document.querySelector(\"select[name='status']\").value = 'Completed';");

    }

    @Step("Hidden element by: {0}")
    public static void hiddenElement(By by) {
        try {
            WebElement element = getWebElement(by);

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display='none';", element);
            ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Hidden element by: " + by);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Step("Visible element by: {0}")
    public static void visibleElement(By by) {
        try {
            // Tìm phần tử select
            WebElement element = getWebElement(by);
            // Thay đổi thuộc tính display để hiển thị phần tử
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display='block';", element);
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.pointerEvents = 'auto';", element);
            ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Visible element by: " + by);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Step("Set Attribute by: {0}, attribute: {1} with value: {2}")
    public static void setElementAttribute(By by,String attribute, String value){
            // Tìm phần tử select
            WebElement element = getWebElement(by);
            // Thay đổi thuộc tính display để hiển thị phần tử
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0]."+attribute+"=arguments[1];", element, value);
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Set Attribute element by: " + by +", attribute: "+attribute+"with value: " +value);
    }
    @Step("Select option by: {0} with value : {1}")
    public static void selectByVisibleText(By by, String visibleText) {
        try {
            // Tìm phần tử select
            WebElement selectElement = getWebElement(by);

            // Thay đổi thuộc tính display để hiển thị phần tử
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display='block';", selectElement);
            // Chọn option
            Select select = new Select(selectElement);
            select.selectByVisibleText(visibleText);
            js.executeScript("arguments[0].style.display='none';", selectElement);
            ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Select option by:" + by + " with value:" + visibleText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        LogUtils.info(message);
    }


    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getListWebElement(By by) {
        return DriverManager.getDriver().findElements(by);
    }
    @Step("Check element is display by: {0}")
    public static Boolean checkElementIsDisplay(By by) {

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("✅ Check Element Exist: " + true + " ---> " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("❌ Check Element Exist: " + false + " ---> " + by);
            return false;
        }
    }
    @Step("Check element is display by: {0} with time is {1} second")
    public static Boolean checkElementIsDisplay(By by, int second) {

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("✅ Check Element Exist: " + true + " ---> " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("❌ Check Element Exist: " + false + " ---> " + by);
            return false;
        }
    }
    @Step("Calculate distance from by: {0} to {1}")
    public static double calculateDistance(By by1, By by2) {
        // Lấy vị trí của các phần tử
        int x1 = getWebElement(by1).getLocation().getX();
        int y1 = getWebElement(by1).getLocation().getY();
        int x2 = getWebElement(by2).getLocation().getX();
        int y2 = getWebElement(by2).getLocation().getY();
        // Tính khoảng cách
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Step("Open url {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("\uD83C\uDF10 Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Open URL: " + url);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("openURL_" + url));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("openURL_" + url));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Refresh Page on Web")
    public static void refreshPageWeb() {
        DriverManager.getDriver().navigate().refresh();
        waitForPageLoaded();
        sleep(STEP_TIME);
        LogUtils.info("Refresh Page on Web");
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Refresh Page on Web");
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("refreshPageWeb_"));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("refreshPageWeb_"));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Click element {0}")
    public static void clickElement(By by) {
        waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDFE2 Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Click element: " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Hover and click element {0}")
    public static void hoverAndClickElement(By by) {
        checkElementIsDisplay(by);
        sleep(STEP_TIME);
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            waitForElementClickable(by);
            getWebElement(by).click();
            LogUtils.info("\uD83D\uDFE2 Hover and Click element: " + by);
            ExtentTestManager.logMessage(Status.PASS, "➡\uFE0F Hover to element by :  " + by);
            AllureManager.saveTextLog("Hover and Click element by :  " + by);
            if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
                CaptureHelper.screenshot(SystemHelper.makeSlug("hover_click_element_" + by));
                ExtentTestManager.addScreenshot(SystemHelper.makeSlug("hover_click_element_" + by));
                AllureManager.saveScreenshotPNG();
            }
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            ExtentTestManager.logMessage(Status.FAIL, "➡\uFE0F Hover to element by :  " + by);
        }
    }

    @Step("Click element {0} with timeout {1}")
    public static void clickElement(By by, long timeout) {
        waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDFE2 Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Click element: " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Set text {1} on element {0}")
    public static void setText(By by, String value) {
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("\uD83D\uDFE2 Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Set text: " + value + " on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }
    @Step("Clear by: {0} and settext : {1")
    public static void clearAndSetText(By by, String value) {
        waitForPageLoaded();
        clearText(by);
        sleep(1);
        setText(by, value);
    }

    @Step("Clear text: on element {0}")
    public static void clearText(By by) {
        waitForPageLoaded();
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).clear();
        LogUtils.info("\uD83D\uDFE2 Clear text: on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Clear text: on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Set key {1} on element {0}")
    public static void setKey(By by, Keys keys) {
        getWebElement(by).sendKeys(keys);
        LogUtils.info("\uD83D\uDFE2 Set key: " + keys.name() + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Set key: " + keys.name() + " on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setKey_" + by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setKey_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("➡\uFE0F Get text: " + text);
        ExtentTestManager.logMessage(Status.PASS, "➡\uFE0F Get text: " + text);
        AllureManager.saveTextLog("➡\uFE0F Get text: " + text);
        return text;
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getAttribute(attributeName);
        LogUtils.info("➡\uFE0F Attribute value: " + text);
        return text;
    }

    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value + " on element " + by);
    }

//    public static void scrollToElement(By element) {
//        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
//    }

    public static void scrollToElementOnTop(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    public static void scrollToElementOnBottom(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(element));
    }

    /**
     * Cuộn đến vị trí phần tử chỉ định
     *
     * @param element
     * @param position nếu giá trị 'true' thì cuộn lên trên. Nếu giá trị 'false' thì cuộn xuống dưới
     */
    public static void scrollToElement(By element, String position) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(" + position + ");", getWebElement(element));
    }

    public static void scrollToElement( By by) {

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }
    @Step("scroll to position : {0}, {1}")
    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }
    @Step("Move to element: {0}")
    public static boolean moveToElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            ExtentTestManager.logMessage(Status.PASS, "➡\uFE0F Move to element: " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    @Step("Set the element {0} start date {1}")
    public static void chooseDate(By by, String time) {
        clickElement(by);
        sleep(1);
        // Chuyển đổi chuỗi thành LocalDate
        LocalDate date = LocalDate.parse(time);

        // Định dạng tháng thành dạng viết tắt với khu vực tiếng Anh
        String month = String.valueOf(date.getMonth()).substring(0, 3).toUpperCase();
        int year = date.getYear();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        String day = date.format(dayFormatter);
        /*Choose Month*/
        By buttonChangeMonth = By.xpath("//div[not(contains(@class, 'hidden')) and contains(@class,'dtp animated fadeIn')]//div[1]/div[normalize-space()='chevron_left']");
        By presentMonth = By.xpath("//div[not(contains(@class, 'hidden')) and contains(@class,'dtp animated fadeIn')]//div[normalize-space()='" + month + "']");
        By buttonMinusYear = By.xpath("//div[not(contains(@class, 'hidden')) and contains(@class,'dtp animated fadeIn')]//div[3]/div[normalize-space()='chevron_left']");
        By buttonPlusYear = By.xpath("//div[not(contains(@class, 'hidden')) and contains(@class,'dtp animated fadeIn')]//div[3]/div[normalize-space()='chevron_right']");
        //Get default month
        LogUtils.info("Present Month: " + presentMonth);
        waitForPageLoaded();
        while (!checkElementIsDisplay(presentMonth, 1)) {
            clickElement(buttonChangeMonth);
        }
        /*Choose Year*/
        int yearNow = LocalDate.now().getYear();
        for (int i = 0; i != Math.abs(yearNow - year); i++) {
            if (yearNow - year < 0) {
                clickElement(buttonPlusYear);
                sleep(0.1);
            } else {
                clickElement(buttonMinusYear);
                sleep(0.1);
            }
        }
        /*Choose Day*/

        By buttonSelectDay = By.xpath("//div[not(contains(@class, 'hidden')) and contains(@class,'dtp animated fadeIn')]//table/tbody/tr/td[normalize-space()='" + day + "']");
        By buttonConfirm = By.xpath("//div[not(contains(@class, 'hidden')) and contains(@class,'dtp animated fadeIn')]//button[normalize-space()='OK']");
        clickElement(buttonSelectDay);
        clickElement(buttonConfirm);
        ExtentTestManager.logMessage(Status.INFO, "➡\uFE0F Selected time :  " + time);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("Selected_date_" + time));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("Selected_date_" + time));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Hover to element by : {0}")
    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            ExtentTestManager.logMessage(Status.INFO, "➡\uFE0F Hover to element by :  " + by);
            AllureManager.saveTextLog("Hover to element by :  " + by);
            if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
                CaptureHelper.screenshot(SystemHelper.makeSlug("hover_element_" + by));
                ExtentTestManager.addScreenshot(SystemHelper.makeSlug("hover_element_" + by));
                AllureManager.saveScreenshotPNG();
            }
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }
    public static boolean dragAndDropWithJavaScript(By fromElement, double X, double Y) {
        try {
            WebElement element = getWebElement(fromElement);
            String script = "var src=arguments[0],tgt=arguments[1];" +
                    "var dataTransfer= new DataTransfer();" +
                    "var evt = new DragEvent('dragstart', {dataTransfer: dataTransfer});" +
                    "src.dispatchEvent(evt);" +
                    "var dropEvt = new DragEvent('drop', {dataTransfer: dataTransfer});" +
                    "tgt.dispatchEvent(dropEvt);";

            ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script, element);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='2px solid red'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElement(By by, String colorName) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='2px solid " + colorName + "'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }

    //Assert and Verify

    public static void assertTrue(boolean status, String expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert equals: " + status + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert True: " + status + " \uD83D\uDFF0 " + expected);
        Assert.assertTrue(status);
    }

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        return actual.equals(expected);
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static void softAssertEquals(SoftAssert softAssert,Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        softAssert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Verify contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static void softAssertContains(SoftAssert softAssert,String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        softAssert.assertTrue(check, message);
    }

    public static void softAssertAll(SoftAssert softAssert) {
        softAssert.assertAll();
    }


    //Wait for Element

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigData.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());

        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }


    public static void waitForAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static Boolean checkElementExist(WebDriver driver, By by) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("Element " + by + " existing.");
            ExtentTestManager.logMessage(Status.INFO, "Element " + by + " existing.");
            return true;
        } else {
            LogUtils.info("Element " + by + " NOT exist.");
            ExtentTestManager.logMessage(Status.INFO, "Element " + by + " NOT exist.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                LogUtils.error(error.getMessage());
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded(int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void goToIframe(By by) {
        WebElement frame = getWebElement(by);
        DriverManager.getDriver().switchTo().frame(frame);
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Go to Iframe by: " + by);
    }

    public static void exitIframe() {
        DriverManager.getDriver().switchTo().defaultContent();
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Switch to Iframe default: ");
    }
    @Step("Upload file with sendkey {1} to element {0}")
    public static void uploadFileSendKey(By by, String path) {
        getWebElement(by).sendKeys(path);
        LogUtils.info("Upload file with sendkey: " + path + " - To element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Upload file with sendkey: " + path + " - To element " + by);
        AllureManager.saveTextLog("Upload file with sendkey: " + path + " - To element " + by);
    }

    @Step("Upload file with robot class {1} to element {0}")
    public static void uploadFileWithRobot(By by, String path) {
        clickElement(by);
        sleep(2);
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        StringSelection str = new StringSelection(SystemHelper.getCurrentDir() + path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        sleep(2);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        LogUtils.info("Upload file with robot class: " + path + " - To element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Upload file with robot class: " + path + " - To element " + by);
        AllureManager.saveTextLog("Upload file: with robot class " + path + " - To element " + by);
    }

}