package cucumbertests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import resources.utils.constants.GlobalVariables;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
			 "pretty", "html:target/cucumberHtmlReport",
		     "html:target/cucumberHtmlReport",     //  for html result
			 "pretty:target/cucumber-json-report.json"   // for json result
		     },
		features = GlobalVariables.FEATURES_PATH,
		glue = {GlobalVariables.GLUE_KEYWORDS_PATH   // predefined step definitions package
			   }
)

public class RunCukesTest { 	
}
