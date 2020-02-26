package adp.autotrader.stepDefinitations;

import org.openqa.selenium.support.ui.WebDriverWait;

import adp.autotrader.pages.AdvancedSearchPage;
import adp.autotrader.pages.HomePage;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HomePageStepDef {
	public static WebDriver driver;
	  HomePage hmePage;
	  AdvancedSearchPage advSearchPg;
	  Properties prop;


	@Given("^User navigates to autotrader page$")
	public void user_navigates_to_autotrader_page() throws IOException {
	prop = new Properties();
	FileInputStream fStream = null;
	System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\autotrader.properities");
	try {
		fStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\autotrader.properties");
		prop.load(fStream);   
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	
   if (prop.getProperty("browser").contains("chrome")) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        }
   else {
	   //handle all the other browsers
	   System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
       driver = new ChromeDriver();  
   }
	
   //To open the chrome browser with default profile 
	/*System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
	driver= new ChromeDriver();*/
	/*ChromeOptions options = new ChromeOptions();
	//options.addArguments("user-data-dir="+System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
    //options.addArguments("--start-maximized","allow-running-insecure-content", "--test-type");
	//driver = new ChromeDriver(options);*/
   
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   // driver.navigate().to("http://www.autotrader.com");
    driver.navigate().to(prop.getProperty("baseurl"));

}


@When("^The autoTrader home page loads$")
public void the_autoTrader_home_page_loads() {
        //assertEquals("https://www.autotrader.com/", driver.getCurrentUrl());
        assertEquals(prop.getProperty("baseurl"), driver.getCurrentUrl());
}

@Then("^user verifies following links are present$")
public void user_verifies_following_links_are_present(DataTable pgLinks) {

		  assertTrue(driver.findElement(By.xpath("//*[text() ='Search Cars for Sale']")).isDisplayed());
		  List<List<String>> data = pgLinks.raw();
		  assertTrue(driver.findElement(By.xpath("//span[text() ='"+data.get(0).get(0)+"']")).isDisplayed());
		  assertTrue(driver.findElement(By.xpath("//span[text() ='"+data.get(0).get(1)+"']")).isDisplayed());
		  assertTrue(driver.findElement(By.linkText("Advanced Search")).isDisplayed());

}


@Then("^user verifies \"([^\"]*)\" Button is present$")
public void user_verifies_Button_is_present(String btnName) throws Throwable {
	  assertTrue(driver.findElement(By.id(btnName)).isDisplayed());

}

@Then("^user verifies the dropdowns are present$")
public void user_verifies_the_dropdowns_are_present() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	hmePage= new HomePage(driver);
	 if( hmePage.verifyMakeModelDropDown()) {
		 assertTrue(true);
	 }
	 else { 
		 assertTrue("Not Found dropdowns Make and Modal on Home page", false);
		 };
	  //assertTrue(driver.findElement(By.xpath("//select[@name='makeCodeListPlaceHolder']")).isDisplayed());
	  //assertTrue(driver.findElement(By.id("modelCodeListPlaceHolder")).isDisplayed());
     // driver.quit();

	  }

@Given("^User is on Advanced Search page$")
public void user_is_on_Advanced_Search_page() throws Throwable {
	 HomePage  hmePage =new HomePage(driver);
	 advSearchPg= hmePage.doAdvancedSearch();
	 if(advSearchPg != null) {
		 assertTrue(true);
		 }else {
	      assertTrue(false);	
	}}

@When("^user Enter \"([^\"]*)\" in Zip Code textbox$")
public void user_Enter_in_Zip_Code_textbox(String strZipCode) {
	  advSearchPg.setZipCode(strZipCode);
      
}

@When("^select \"([^\"]*)\" check box under Condition$")
public void select_check_box_under_Condition(String strValue) throws Throwable {
	  advSearchPg.setVehicleCondition(strValue);

}

@When("^select \"([^\"]*)\" check box under Style$")
public void select_check_box_under_Style(String strValue) throws Throwable { 
	advSearchPg.setStyle(strValue);
}

@When("^select \"([^\"]*)\" as From year and select \"([^\"]*)\" as To year$")
public void select_as_From_year_and_select_as_To_year(String strYr, String strEndYr) throws Throwable {
	advSearchPg.selectStartAndEndYear(strYr, strEndYr);
}

@When("^Select Make as \"([^\"]*)\" under Make, Model & Trim section$")
public void select_Make_as_under_Make_Model_Trim_section(String strValue) throws Throwable {
	advSearchPg.selectMakeModalTrim(strValue, "", "");
}

@When("^clicks the button \"([^\"]*)\" at the bottom of the page$")
public void clicks_the_button_at_the_bottom_of_the_page(String btnSubmit) throws Throwable {
	advSearchPg.clickSearch();
}


@Then("^user will only see \"([^\"]*)\" cars in the results page$")
public void user_will_only_see_cars_in_the_results_page(String strMake) throws Throwable {
	advSearchPg.verifySearchResult(strMake);

}


@After
public void closeBrowser() {
    //driver.quit();
}






}
