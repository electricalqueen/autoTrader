package adp.autotrader;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
		 features = {"src/test/resources/adp/autotrader"}
		 ,glue={"adp.autotrader.stepDefinitations"}
		 ,tags= {"@run"}
		 ,plugin= {"pretty"})
public class RunCucumberTest {
}
