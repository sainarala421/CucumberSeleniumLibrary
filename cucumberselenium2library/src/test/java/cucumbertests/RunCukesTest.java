package cucumbertests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import resources.utils.constants.GlobalVariables;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
		customTemplatesPath = GlobalVariables.CUCUMBER_LOGS_TEMPLATES + "/templates.json",
		jsonReport = "target/cucumber-logs/cucumber.json",
	    detailedReport = true,
	    detailedAggregatedReport = true,
	    overviewReport = true,
	    coverageReport = true,
	    jsonUsageReport = "target/cucumber-logs/cucumber-usage.json",
	    usageReport = true,
	    toPDF = true,
	    excludeCoverageTags = {"@flaky" },
	    includeCoverageTags = {"@passed" },
	    
	    // Logs and screenshots directory
		outputFolder = "target/cucumber-logs/"
		)
@CucumberOptions(
		plugin = { 
				"html:target/cucumber-html-report",
		        "json:target/cucumber-logs/cucumber.json", 
		        "pretty:target/cucumber-logs/cucumber-pretty.txt",
		        "usage:target/cucumber-logs/cucumber-usage.json", 
		        "junit:target/cucumber-logs/cucumber-results.xml" },
        tags = {"@consistent"},
		features = GlobalVariables.FEATURES_PATH,
		glue = {GlobalVariables.GLUE_KEYWORDS_PATH}
)
public class RunCukesTest { 	
}
