package adp.autotrader.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Base {
	private WebDriver driver;
	
	public Base(WebDriver driver) {
		 this.driver=driver;
	     PageFactory.initElements(driver, this);
	 }

	public void getScreeshot() throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(System.getProperty("user.dir"+"\\Failed.png"));
        FileUtils.copyFile(SrcFile, DestFile);
	}

}
