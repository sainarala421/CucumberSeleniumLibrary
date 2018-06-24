package resources.desktopweb.keywords.global;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.FormElement;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Element;
import cucumber.api.java.en.When;

public class KeywordsFormElement{
	// Constructor
    public KeywordsFormElement() throws Exception {
    }
    static FormElement formelementInstance = new FormElement();
    static Element elementInstance = new Element();
    static WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
    static ExtentTest test = KeywordsBrowserManagement.test;
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User inputs \"(.*?)\" in \"(.*?)\" field$")
    public void user_inputs_text_in_field(String text, String locator) throws Throwable{
    	test.createNode("When", String.format("When user inputs text '%s' in '%s' field", text, locator));
    	elementInstance.focus(driver, locator);
    	formelementInstance.inputText(driver, locator, text);
    }
    
    @When("^User inputs password \"(.*?)\" in \"(.*?)\" field$")
    public void user_inputs_password_in_field(String password, String locator) throws Throwable{
    	test.createNode("When", String.format("When user inputs password in '%s' field", password, locator));
    	elementInstance.focus(driver, locator);
    	formelementInstance.inputPassword(driver, locator, password);
    }
}
