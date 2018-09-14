package resources.desktopweb.keywords.global;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class KeywordsSetupTeardowns{
    protected WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();

    @After
    public void tearDown(Scenario scenario) {
        System.out.println(scenario.getStatus());
        if (scenario.getStatus().equalsIgnoreCase("failed")) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] data = FileUtils.readFileToByteArray(scrFile);
                scenario.embed(data, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
