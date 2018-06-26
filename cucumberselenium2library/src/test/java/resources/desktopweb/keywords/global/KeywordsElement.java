package resources.desktopweb.keywords.global;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Element;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;

public class KeywordsElement{
	// Constructor
    public KeywordsElement() throws Exception {
    }
    static Element elementInstance = new Element();
    static WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
    static ExtentTest test = KeywordsBrowserManagement.test;
    protected static PropertiesValue pvalue = KeywordsBrowserManagement.pvalue;
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User clicks \"(.*?)\"$")
    public void user_clicks_element(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user clicks element %s", p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clickElement(driver, p_locator);
    }
   
    @When("^User double clicks \"(.*?)\"$")
    public void user_double_clicks_element(String locator) throws Throwable{
    	elementInstance.focus(driver, locator);
    	elementInstance.doubleClickElement(driver, locator);
    }
    
    @When("^User clicks image \"(.*?)\"$")
    public void user_clicks_image(String locator) throws Throwable{
    	elementInstance.focus(driver, locator);
    	elementInstance.clickImage(driver, locator);
    }
    
    @When("^User clicks link \"(.*?)\"$")
    public void user_clicks_link(String locator) throws Throwable{
    	elementInstance.focus(driver, locator);
    	elementInstance.clickLink(driver, locator);
    }
    
    @When("^User clears element \"(.*?)\"$")
    public void user_clears_element(String locator) throws Throwable{
    	elementInstance.focus(driver, locator);
    	elementInstance.clearElementText(driver, locator);
    }
    
    /**
     *  -----------------------------------
     *  Reusable keywords - THEN STATEMENTS
     *  -----------------------------------
     */
    @Then("^Element \"(.*?)\" should contain text \"(.*?)\"$")
    public void element_should_contain(String locator, String text) throws Throwable{
    	elementInstance.focus(driver, locator);
    	elementInstance.elementShouldContain(driver, locator, text);
    }
}