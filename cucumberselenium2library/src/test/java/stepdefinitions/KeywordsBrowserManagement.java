package stepdefinitions;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.BrowserManagement;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
//import cucumber.api.java.Before;
import cucumber.api.java.en.When;

public class KeywordsBrowserManagement{

    public KeywordsBrowserManagement() throws Exception {
    }
    BrowserManagement browserinstance = new BrowserManagement();

    /**
     *  --------------------
     *  Suite Setup Keywords
     *  --------------------
     */
    
    /**
     *  -----------------------
     *  Suite Teardown Keywords
     *  -----------------------
     */
    
    @After
    public void close_all_browsers() throws Throwable{
    	browserinstance.closeAllBrowsers();
    }
    
    /**
     *  -----------------------------------
     *  Reusable keywords - GIVEN STATEMENTS
     *  -----------------------------------
     */
    
    @Given("^User opens url \"(.*?)\" in \"(.*?)\" browser$")
    public void user_opens_browser(String url, String browser) throws Throwable{
    	browserinstance.openBrowser(url, browser);
    }
   
    @Given("^User opens url \"(.*?)\" in \"(.*?)\" with browser alias \"(.*?)\"$")
    public void user_opens_browser_with_alias(String url, String browser, String browserAlias) throws Throwable{
    	browserinstance.openBrowser(url, browser, browserAlias);
    }
    
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    
    @When("^User navigates to \"(.*?)\"$")
    public void user_navigates_to(String url) throws Throwable{
    	browserinstance.goTo(url);
    }
   
    @When("^User switches to browser \"(.*?)\"$")
    public void user_switches_to_browser(String browserAlias) throws Throwable{
    	browserinstance.switchBrowser(browserAlias);
    }
    
    @When("^User reloads page$")
    public void user_reloads_page(String browserAlias) throws Throwable{
    	browserinstance.reloadPage();
    }

    /**
     *  -----------------------------------
     *  Reusable keywords - THEN STATEMENTS
     *  -----------------------------------
     */
    
    /**
     *  -----------------
     *  Utilities
     *  -----------------
     */
    
    @When("^Get system info$")
    public void get_system_info() throws Throwable {
    	String sysinfo = browserinstance.getSystemInfo();
    	System.out.println(sysinfo);
	}
}