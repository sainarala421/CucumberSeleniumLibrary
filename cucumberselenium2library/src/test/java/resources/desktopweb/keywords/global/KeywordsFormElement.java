package resources.desktopweb.keywords.global;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.FormElement;
import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Element;
import cucumber.api.java.en.When;

public class KeywordsFormElement{
	// Constructor
    public KeywordsFormElement() throws Exception {
    }
    static FormElement formelementinstance = new FormElement();
    static Element elementinstance = new Element();
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User inputs \"(.*?)\" in \"(.*?)\" field$")
    public void user_inputs_text_in_field(String text, String locator) throws Throwable{
    	elementinstance.focus(text);
    	formelementinstance.inputText(locator, text);
    }
    
    @When("^User inputs password \"(.*?)\" in \"(.*?)\" field$")
    public void user_inputs_password_in_field(String password, String locator) throws Throwable{
    	elementinstance.focus(locator);
    	formelementinstance.inputPassword(locator, password);
    }
}