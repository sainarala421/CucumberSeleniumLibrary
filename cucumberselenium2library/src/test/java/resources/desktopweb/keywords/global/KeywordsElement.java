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
    protected Element elementInstance = new Element();
    protected WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
    protected ExtentTest test = KeywordsBrowserManagement.test;
    protected PropertiesValue pvalue = KeywordsBrowserManagement.pvalue;
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
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user double-clicks element %s", p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.doubleClickElement(driver, p_locator);
    }
    
    @When("^User clicks image \"(.*?)\"$")
    public void user_clicks_image(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user clicks image %s", p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clickImage(driver, p_locator);
    }
    
    @When("^User clicks link \"(.*?)\"$")
    public void user_clicks_link(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user clicks link %s", p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clickLink(driver, p_locator);
    }
    
    @When("^User clears element \"(.*?)\"$")
    public void user_clears_element(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user clears element %s", p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clearElementText(driver, p_locator);
    }
    
    /**
     *  -----------------------------------
     *  Reusable keywords - THEN STATEMENTS
     *  -----------------------------------
     */
    @Then("^Element \"(.*?)\" should contain text \"(.*?)\"$")
    public void element_should_contain(String locator, String text) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	
    	test.createNode("When", String.format("When user clears element %s", p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.elementShouldContain(driver, p_locator, text);
    }
    
    @When("^The \"(.*?)\" should be displayed$")
    public void element_should_be_displayed(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	test.createNode("Then",String.format("Element '%s': %s should be displayed", locator, p_locator));
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    }
}