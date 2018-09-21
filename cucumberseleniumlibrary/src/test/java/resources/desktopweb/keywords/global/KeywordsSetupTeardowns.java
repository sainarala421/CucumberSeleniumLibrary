package resources.desktopweb.keywords.global;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import org.openqa.selenium.WebDriver;
import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.Screenshot;

public class KeywordsSetupTeardowns{

	protected Screenshot screenshot = new Screenshot();
	protected WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
	
    @After
    public void tearDown(Scenario scenario) {
    	if (scenario.getStatus().equalsIgnoreCase("failed")) {
    		screenshot.capturePageScreenshot(driver, scenario);
    	}
    }
}
