package adp.autotrader.pages;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Date;
import java.util.List;
import adp.autotrader.*;
import adp.autotrader.base.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import cucumber.api.java.en.And;
import gherkin.deps.net.iharder.Base64;

public class AdvancedSearchPage {
	WebDriver driver;

	 public AdvancedSearchPage(WebDriver driver) {
		 this.driver=driver;
	     PageFactory.initElements(driver, this);
	 }
		
	 @FindBy(how = How.XPATH, using = "//*[text()='Location']") 
		private WebElement lblHeader;

		@FindBy(how = How.XPATH, using = "//input[@name='zip']") 
		private WebElement txtbxZipCode;
		
		@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @value='CERTIFIED']") 
		private WebElement chkbxCertified;
		
		@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @value='NEW']") 
		private WebElement chkbxNew;
		
		@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @value='CONVERT']") 
		private WebElement chkbxConvertible;
		
		@FindBy(how = How.XPATH, using = "//select[@name='startYear']") 
		private WebElement drpdwnStartYear;		
		
		@FindBy(how = How.XPATH, using = "//select[@name='endYear']") 
		private WebElement drpdwnEndYear;
		
		@FindBy(how = How.XPATH, using = "//select[@name='makeFilter0']") 
		private WebElement drpdwnMake;
		
		@FindBy(how = How.XPATH, using = "//select[@name='modelFilter0']") 
		private WebElement drpdwnModal;
		
		@FindBy(how = How.XPATH, using = "//select[@name='trimFilter0']") 
		private WebElement drpdwnTrim;
		
		
		@FindBy(how = How.XPATH, using = "//button[@type='submit']") 
		private WebElement btnSearch;
	
		
		public void setZipCode(String zip) {
			txtbxZipCode.sendKeys(zip);
			
		}
		public void setVehicleCondition(String options) {
			switch (options) {
			case "Certified":
				chkbxCertified.click();
				break;

			default:
				chkbxNew.click();
				break;
			}
		}
		public void setStyle(String style) {
			switch (style) {
			case "Convertible":
				chkbxConvertible.click();
				break;

			default:
				chkbxConvertible.click();
				break;
			}
			
		}
		public void selectStartAndEndYear(String strStartYr ,String strEndYr) {
			if(strStartYr != null){
			 Select drpStartYearSelect=new Select(drpdwnStartYear);
			 drpStartYearSelect.selectByVisibleText(strStartYr);}
			if(strEndYr !=null) {
			 Select drpEndYearSelect=new Select(drpdwnEndYear);
			 drpEndYearSelect.selectByVisibleText(strEndYr);}
		}
		
		public void selectMakeModalTrim(String strMake,String strModal,String strTrim) {
			if(strMake != null) {
				Select drpMakeSelect=new Select(drpdwnMake);
				drpMakeSelect.selectByVisibleText(strMake);}
			if(strModal != null) {
				Select drpModalSelect=new Select(drpdwnModal);
				drpModalSelect.selectByVisibleText(strModal);}
			if(strModal != null) {
				Select drpTrimSelect=new Select(drpdwnTrim);
				drpTrimSelect.selectByVisibleText(strTrim);}
			 
		}
		
		public void clickSearch() {
			btnSearch.click();		
		}
		
		
		
		public int verifySearchResult(String strMake) {
			int count =0;
			List<WebElement> allSearchLinks = driver.findElements(By.tagName("a"));
			for(WebElement link:allSearchLinks){
				if (link.getText().contains(strMake)) {
					count=count+1;				 
					//System.out.println(link.getText() + " - " + link.getAttribute("href"));	
				}
		     }
			return count;
		}
		

		
}
