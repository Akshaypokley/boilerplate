package ui.automation.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {
	
	  public static final String DateStr = null;

	public static String capture(WebDriver driver) throws Exception
	    {
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	        String DateStr = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
	        String imageName = "screenshot"+DateStr+".png";
	        String imagePath="test-output/Extent-report/screenshots/"+imageName;
	        FileUtils.copyFile(scrFile, new File(imagePath));

	        return imageName;
	    }

}
