package resources.desktopweb.keywords.global;

import org.openqa.selenium.WebDriver;

import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.Element;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;

public class KeywordsElement{
    protected Element elementInstance = new Element();
    protected WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
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
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clickElement(driver, p_locator);
    }
   
    @When("^User double clicks \"(.*?)\"$")
    public void user_double_clicks_element(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.doubleClickElement(driver, p_locator);
    }
    
    @When("^User clicks image \"(.*?)\"$")
    public void user_clicks_image(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clickImage(driver, p_locator);
    }
    
    @When("^User clicks link \"(.*?)\"$")
    public void user_clicks_link(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.clickLink(driver, p_locator);
    }
    
    @When("^User clears element \"(.*?)\"$")
    public void user_clears_element(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
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
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	elementInstance.elementShouldContain(driver, p_locator, text);
    }
    
    @When("^The \"(.*?)\" should be displayed$")
    public void element_should_be_displayed(String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    }
}