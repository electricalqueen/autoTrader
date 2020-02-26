package adp.autotrader.pages;
import java.io.File;
import java.io.IOException;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
 
	public HomePage(WebDriver driver) {
		 this.driver=driver;
	     PageFactory.initElements(driver, this);
	 }
	
	 @FindBy(how = How.XPATH, using = "//*[text()='Location']") 
		private WebElement lblHeader;
	
	@FindBy(how = How.XPATH, using = "//input[@name='zip']") 
	private WebElement txtbxZipCode;
	
	
	@FindBy(how = How.XPATH, using = "//a[text()='Advanced Search']") 
	private WebElement linkAdvSearch;
	
	@FindBy(how = How.XPATH, using = "//select[@name='makeCodeListPlaceHolder']") 
	private WebElement drpdwnMake;
	
	@FindBy(how = How.XPATH, using = "//select[@name='makeCodeListPlaceHolder']") 
	private WebElement drpdwnModal;
	

	
	  public void clearZipCode() {
	    	 txtbxZipCode.clear();
	     }
	  
	  public void setZipCode() {
	    	 txtbxZipCode.sendKeys("30004");
	     }
	
     public AdvancedSearchPage doAdvancedSearch() throws Exception {
    	 Thread.sleep(5000);
    	 setZipCode();
    	 linkAdvSearch.click();
    	 Thread.sleep(5000);

    	 /*String tempURL=driver.getCurrentUrl();
    	 System.out.println(tempURL);
    	 driver.get(tempURL+"&zip=30004");*/
    	 
   	     if (isAdvancedSearchPage()) {
   			return new AdvancedSearchPage(driver); 
   	     }
		return null; 
     }
     
		
		public boolean isAdvancedSearchPage() throws IOException {
			boolean isFound =false;
			try {
				if (lblHeader.isDisplayed()) {
					isFound=true;}}
			catch(Exception NoSuchElementException) {
				this.getScreeshot();
			}
			return isFound;
		}
		
		public boolean verifyMakeModelDropDown() {
			boolean isFound=false;
			if (drpdwnMake.isDisplayed() && drpdwnModal.isDisplayed()) {
				isFound=true;

			}
			return isFound;
		}
		
		

		
		
		public void getScreeshot() throws IOException {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			String currentDirectory = System.getProperty("user.dir");			 
	        File DestFile=new File(currentDirectory+"\\Screenshots\\Failed"+ new Date().getTime()+".png");
	        FileUtils.copyFile(SrcFile, DestFile);	
}
}
