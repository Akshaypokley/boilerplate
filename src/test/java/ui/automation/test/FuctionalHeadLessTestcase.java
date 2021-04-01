package ui.automation.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import ui.automation.pages.Cardspage;
import ui.automation.pages.LogSignUp;
import ui.automation.utilities.BrowseFun;
import ui.automation.utilities.ExtentReportUtils;
import ui.automation.utilities.loginfun;

import static ui.automation.utilities.BrowseFun.openBrowser;
import static ui.automation.utilities.BrowseFun.openurl;
import static ui.automation.utilities.TakeScreenshot.capture;
import static ui.automation.utilities.loginfun.loginfunction;


public class FuctionalHeadLessTestcase {
    static WebDriver driver;
    static ExtentReports extent ;
    static int i=1;
    static ExtentTest extentTest;
    static LogSignUp logsignup;
    static Cardspage cardspage;
    static HSSFSheet sh;
    static int cloumCount;
    static int addtaskop=1;
	@BeforeClass
	public static void openbrows() throws IOException {
	    extentTest=extent.startTest("HeadLess Test Report....! ");
        driver=openBrowser("HeadLess");

		openurl();
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
	}
	
	@Test(dataProvider = "hybridData",enabled = false)
	public static void SignUpTest(String email,String fullnm,String pass) throws Exception {


        extentTest=extent.startTest("Free Signup Test. ");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        extentTest.log(LogStatus.INFO,"Signup Page Validation.");
		logsignup=new LogSignUp(driver);
        try {
            extentTest.log(LogStatus.INFO, "Enter emailID in email text field.");
            logsignup.setMailText(email);
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
            extentTest.log(LogStatus.INFO, "Click on free signup Btn.");
            logsignup.clickfreesignup();
            extentTest.log(LogStatus.INFO, "Enter full name in text field.");
            logsignup.setFullnm(fullnm);
            extentTest.log(LogStatus.INFO, "Set password.");
            logsignup.setPassword(pass);
            extentTest.log(LogStatus.INFO, "Click on signup Button.");
            //logsignup.clicksignup();
            //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
            extentTest.log(LogStatus.INFO, "Continue for login.");
            Thread.sleep(1000);
            //logsignup.clickonContinue();
            Thread.sleep(1000);
            //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        }catch (Throwable e)
        {
            extentTest.log(LogStatus.FAIL,"TestCase Fail "+e.getMessage());
            extentTest.log(LogStatus.FAIL, "Snapshot below: " + extentTest.addScreenCapture("./screenshots/" + capture(driver)));

        }
	}

	@Test(dataProvider = "hybridData",priority = 0)
    public static void loginTest(String UserNm, String Password) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        extentTest.log(LogStatus.INFO,"Login Page Validation.");
        logsignup=new LogSignUp(driver);
        extentTest.log(LogStatus.INFO,"Click on login Btn");
        logsignup.clicksignlink();
        extentTest.log(LogStatus.INFO,"Enter emaild");
        logsignup.setUseremail(UserNm);
        extentTest.log(LogStatus.INFO,"Password field validation");
        Thread.sleep(6000);
        if(logsignup.validpassfield().isDisplayed())
        {
            extentTest.log(LogStatus.INFO,"Password filed is displayed ");
            logsignup.setPassword(Password);
            extentTest.log(LogStatus.INFO,"Click on submit.");
            logsignup.clicksubmit();
        }else {
            extentTest.log(LogStatus.INFO,"Click on submit.");
            logsignup.clicksubmit();
            extentTest.log(LogStatus.INFO,"Enter Password");
            logsignup.setPassword(Password);
            extentTest.log(LogStatus.INFO,"Click on submit.");
            logsignup.Clickloginsubmit();
            //Thread.sleep(6000);
            //extentTest=extent.startTest("Create Cards. ");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            cardspage=new Cardspage(driver);
            extentTest.log(LogStatus.INFO,"Click on trello Board.");
            cardspage.clickonboard();

        }
    }

    @Test(dataProvider = "hybridData",priority = 1)
    public static void CreationAndValidationCards(String CardOption,String TaskDetails) throws Exception {

try {

    extentTest.log(LogStatus.INFO, "select the task option.");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    List<WebElement> option = driver.findElements(By.xpath("//*[@id='board']/div[1]/div/div[1]/textarea"));
    for (WebElement op : option) {
        String optxt = op.getText();
        if (optxt.equals(CardOption)) {
            extentTest.log(LogStatus.INFO, "Click on add task.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> addtask = driver.findElements(By.xpath("//*[@id='board']/div[1]/div/div[3]/a"));
            addtaskop++;

            for (WebElement task : addtask) {
                if (task.isDisplayed()) {
                    task.click();
                    extentTest.log(LogStatus.INFO, "Enter task details.");
                    cardspage.setTextArea(TaskDetails);
                    extentTest.log(LogStatus.INFO, "click on Add Card.");
                    cardspage.clickonAddcard();
                    extentTest.log(LogStatus.INFO, "Card Validation...!");
                    WebElement clist = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a[" + i + "]/div[3]/span"));
                    String clisted = clist.getText();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    extentTest.log(LogStatus.INFO, "List Value" + clisted);

                    extentTest.log(LogStatus.INFO, "Validating with values ( " + clisted + ") And (" + TaskDetails + " )");

                    Assert.assertEquals(clisted, TaskDetails);
                    extentTest.log(LogStatus.INFO, "Card added successfully --->" + TaskDetails);
                    i++;
                    }
                    else {
                    extentTest.log(LogStatus.INFO, "Enter task details.");
                    cardspage.setTextArea(TaskDetails);
                    extentTest.log(LogStatus.INFO, "click on Add Card.");
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    cardspage.clickonAddcard();
                    WebElement clist = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a[" + i + "]/div[3]/span"));
                    String clisted = clist.getText();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    extentTest.log(LogStatus.INFO, "List Value" + clisted);

                    extentTest.log(LogStatus.INFO, "Validating with values ( " + clisted + ") And (" + TaskDetails + " )");

                    Assert.assertEquals(clisted, TaskDetails);
                    extentTest.log(LogStatus.INFO, "Card added successfully --->" + TaskDetails);
                    i++;
                }

            }

        }
    }

    }catch (Throwable e)
{
    extentTest.log(LogStatus.FAIL, "TestCase Fail " + e.getMessage());
    extentTest.log(LogStatus.FAIL, "Snapshot below: " + extentTest.addScreenCapture("./screenshots/" + capture(driver)));

}
    }





	@DataProvider(name = "hybridData")
    public static Object[][] getDataFromDataprovider(Method GetMethodNm) throws IOException {
        Object[][] object = null;
        FileInputStream fis = new FileInputStream("ExternalSrcDir/TestData.xls");
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        String MethodName=GetMethodNm.getName();
        if(MethodName.equals("loginTest"))
        {
            sh = wb.getSheet("Sheet2");
            cloumCount=2;
        }else if (MethodName.equals("CreationAndValidationCards")){
            sh = wb.getSheet("Sheet3");
            cloumCount=2;
        }else if (MethodName.equals("CardValidation")){
            sh = wb.getSheet("Sheet4");
            cloumCount=1;
        }else {
            sh = wb.getSheet("Sheet1");
            cloumCount=3;
        }

        int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
        System.out.println(rowCount);

        object = new Object[rowCount][cloumCount];
        for (int i = 0; i < rowCount; i++) {

            HSSFRow row = sh.getRow(i+1);


            for (int j = 0; j < row.getLastCellNum(); j++) {
               // System.out.println(row.getCell(j).toString());
                object[i][j] = row.getCell(j).toString();

            }


        }
        return object;


    }
	
    @AfterClass
    public static void endTest()
    {

        extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
        extent.flush();
        driver.quit();
    }

}
