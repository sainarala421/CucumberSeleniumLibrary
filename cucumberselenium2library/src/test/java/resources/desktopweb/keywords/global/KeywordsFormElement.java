package resources.desktopweb.keywords.global;

import org.openqa.selenium.WebDriver;

import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.Element;
import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.FormElement;

import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;

public class KeywordsFormElement{
	// Constructor
    public KeywordsFormElement() throws Exception {
    }
    protected FormElement formelementInstance = new FormElement();
    protected Element elementInstance = new Element();
    protected WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
    protected PropertiesValue pvalue = KeywordsBrowserManagement.pvalue;
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User inputs \"(.*?)\" in \"(.*?)\"$")
    public void user_inputs_text_in_field(String text, String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	formelementInstance.inputText(driver, p_locator, text);
    }
    
    @When("^User inputs password \"(.*?)\" in \"(.*?)\"$")
    public void user_inputs_password_in_field(String password, String locator) throws Throwable{
    	pvalue.setKey(locator);
    	String p_locator = pvalue.getPropertiesValue();
    	elementInstance.elementShouldBeVisible(driver, p_locator);
    	elementInstance.focus(driver, p_locator);
    	formelementInstance.inputPassword(driver, p_locator, password);
    }
}
