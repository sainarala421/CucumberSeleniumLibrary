package resources.desktopweb.keywords.global;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.FormElement;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Element;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;

public class KeywordsFormElement{
	// Constructor
    public KeywordsFormElement() throws Exception {
    }
    static FormElement formelementInstance = new FormElement();
    static Element elementInstance = new Element();
    static WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
    static ExtentTest test = KeywordsBrowserManagement.test;
    protected static PropertiesValue pvalue = KeywordsBrowserManagement.pvalue;
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User inputs \"(.*?)\" in \"(.*?)\"$")
    public void user_inputs_text_in_field(String text, String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user inputs text '%s' in '%s' field", text, p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	formelementInstance.inputText(driver, p_locator, text);
    }
    
    @When("^User inputs password \"(.*?)\" in \"(.*?)\"$")
    public void user_inputs_password_in_field(String password, String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user inputs password in '%s' field", password, p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	formelementInstance.inputPassword(driver, p_locator, password);
    }
}
