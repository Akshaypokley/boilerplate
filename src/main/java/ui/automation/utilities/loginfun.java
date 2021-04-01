package ui.automation.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import ui.automation.pages.LogSignUp;

import java.util.concurrent.TimeUnit;

/**
 * Created by ARP_System on 3/28/2021.
 */
public class loginfun {
    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest extentTest;
    static LogSignUp logsignup;

    public static void loginfunction() throws InterruptedException {
        extentTest = extent.startTest("Login Test. ");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        extentTest.log(LogStatus.INFO, "Login Page Validation.");
        logsignup = new LogSignUp(driver);
        extentTest.log(LogStatus.INFO, "Click on login Btn");
        logsignup.clicksignlink();
        extentTest.log(LogStatus.INFO, "Enter emaild");
        logsignup.setUseremail("Akshay85pokley@gmail.com");
        extentTest.log(LogStatus.INFO, "Password field validation");
        Thread.sleep(6000);
        if (logsignup.validpassfield().isDisplayed()) {
            extentTest.log(LogStatus.INFO, "Password filed is displayed ");
            logsignup.setPassword("AkshayPokley");
            extentTest.log(LogStatus.INFO, "Click on submit.");
            logsignup.clicksubmit();
        } else {
            extentTest.log(LogStatus.INFO, "Click on submit.");
            logsignup.clicksubmit();
            extentTest.log(LogStatus.INFO, "Enter Password");
            logsignup.setPassword("AkshayPokley");
            extentTest.log(LogStatus.INFO, "Click on submit.");
            logsignup.Clickloginsubmit();
            //Thread.sleep(6000);

        }
    }
}