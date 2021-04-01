package ui.automation.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowseFun {
	
	static WebDriver driver;
    public static WebDriver openBrowser(String browserNm)
    {
        switch (browserNm)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
                driver=new ChromeDriver();
                driver.manage().window().maximize();
                break;

            case "HeadLess":
                //System.setProperty("webdriver.gecko.driver","Driver/geckodriver.exe");
                driver=new HtmlUnitDriver();
                driver.manage().window().maximize();
                break;

            case "IE":
                System.setProperty("webdriver.IE.driver","Driver/IEDriverServer.exe");
                driver=new FirefoxDriver();
                driver.manage().window().maximize();
                break;

                default:
                    System.out.println("browser : " + browserNm + " is invalid, Launching Firefox as browser of choice..");
                    System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
                    driver=new ChromeDriver();
                    driver.manage().window().maximize();
                    break;


        }

        return driver;
    }

    public static void openurl()
    {
    	driver.get("https://trello.com/");

    }
}
