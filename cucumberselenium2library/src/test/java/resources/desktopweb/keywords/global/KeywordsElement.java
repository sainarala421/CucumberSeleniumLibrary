package resources.desktopweb.keywords.global;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Element;
import cucumber.api.java.en.When;

public class KeywordsElement{
	// Constructor
    public KeywordsElement() throws Exception {
    }
    static Element elementinstance = new Element();
    
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User clicks \"(.*?)\"$")
    public void user_clicks_element(String locator) throws Throwable{
    	elementinstance.focus(locator);
    	elementinstance.clickElement(locator);
    }
}