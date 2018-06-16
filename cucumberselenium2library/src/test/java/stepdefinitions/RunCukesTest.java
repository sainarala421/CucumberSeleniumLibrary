package stepdefinitions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
			 "pretty", "html:target/cucumberHtmlReport",
		     "html:target/cucumberHtmlReport",     //  for html result
			 "pretty:target/cucumber-json-report.json"   // for json result
		     },
		features = "src/test/java/features",
		glue = {"stepdefinitions"   // predefined step definitions package
				//"info.seleniumcucumber.userStepDefintions" // user step definitions package
			   }
)

public class RunCukesTest { 	
}
