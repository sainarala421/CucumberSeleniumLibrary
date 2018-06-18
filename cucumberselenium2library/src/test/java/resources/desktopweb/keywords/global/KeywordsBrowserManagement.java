package resources.desktopweb.keywords.global;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.BrowserManagement;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;
import resources.utils.constants.GlobalVariables;

public class KeywordsBrowserManagement{
	// Constructor
    public KeywordsBrowserManagement() throws Exception {
    }
    PropertiesValue pvalue = new PropertiesValue();
    static String propertiesfilepath = GlobalVariables.BASE_PROPERTIES_FILE;
    static BrowserManagement browserinstance = new BrowserManagement();
    static String browser = System.getProperty("browser", "");
    static String baseURL = System.getProperty("baseURL", "");
    static String remoteURL = System.getProperty("remoteURL", "");
    
    public void setGlobalVariables() throws Throwable{
    }
    
    /**
     *  --------------------
     *  Suite Setup Keywords
     *  --------------------
     */
    @Before
    public void launch_browser() throws Throwable{
    	/**
    	 *  Reusable setup to launch browser instance.
    	 *  Set the properties in base.properties file.
    	 */
    	
    	// Set defaults if parameters are blank
    	String pbrowser = browser == "" ? GlobalVariables.BROWSER : browser;
    	String pbaseURL = baseURL == "" ? GlobalVariables.BASE_URL : baseURL;
    	String premoteURL = remoteURL == "" ? GlobalVariables.REMOTE_URL_FALSE : remoteURL;
    	
    	// Set selenium speed and timeout
    	browserinstance.setSeleniumSpeed(GlobalVariables.SELENIUM_SPEED);
    	browserinstance.setSeleniumTimeout(GlobalVariables.SELENIUM_TIMEOUT);
    	
    	// Launch Browser
    	browserinstance.openBrowser(
    			pbaseURL, 
    			pbrowser,
    			GlobalVariables.BROWSER_BASE_ALIAS,
    			premoteURL
    			);
    	
    	// Set browser size.
    	browserinstance.setWindowSize(GlobalVariables.BROWSER_WIDTH, GlobalVariables.BROWSER_HEIGHT);
    }
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
     *  ------------------------------------
     *  Reusable keywords - GIVEN STATEMENTS
     *  ------------------------------------
     */
    
    @Given("^User opens url \"(.*?)\" in \"(.*?)\" browser$")
    public void user_opens_browser(String url, String browser) throws Throwable{
    	browserinstance.openBrowser(
    			url, 
    			browser
    			);
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
    
    @When("^User gets title$")
    public void user_gets_title() throws Throwable{
    	String pageTitle = browserinstance.getTitle();
    	System.out.printf("Page title: %s", pageTitle);
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