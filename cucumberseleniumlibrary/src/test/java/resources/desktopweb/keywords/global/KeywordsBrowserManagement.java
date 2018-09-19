package resources.desktopweb.keywords.global;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;
import resources.utils.constants.GlobalVariables;
import org.openqa.selenium.WebDriver;

import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.BrowserManagement;
import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.Element;

public class KeywordsBrowserManagement{
    public static PropertiesValue pvalue = new PropertiesValue();
    public static BrowserManagement browserInstance = new BrowserManagement();
    public static Element elementInstance = new Element();
	protected static String browser = System.getProperty("browser", "");
    protected static String baseURL = System.getProperty("baseURL", "");
    protected static String remoteURL = System.getProperty("remoteURL", "");
    protected WebDriver driver = browserInstance.getCurrentWebDriver();
    
    public void setGlobalVariables() throws Throwable{
    }
    
    /**
     *  --------------------
     *  Suite Setup Keywords
     *  --------------------
     */
    
	/**
	 *  Reusable setup to launch browser instance.
	 *  Set the properties in base.properties file.
	 */
    @Before(order=0)
    public void initialise_htmlreporter() throws Throwable{
    	pvalue.setPropertiesFilePath(GlobalVariables.BASE_PROPERTIES_FILE);
    }
	
    @Before(order=1)
    public void launch_browser() throws Throwable{
    	// Set selenium speed and timeout
    	browserInstance.setSeleniumSpeed(GlobalVariables.SELENIUM_SPEED);
    	browserInstance.setSeleniumTimeout(GlobalVariables.SELENIUM_TIMEOUT);
    	
    	// Set defaults if parameters are blank
    	String pbrowser = browser == "" ? GlobalVariables.BROWSER : browser;
    	String pbaseURL = baseURL == "" ? GlobalVariables.BASE_URL : baseURL;
    	String premoteURL = remoteURL == "" ? GlobalVariables.REMOTE_URL_FALSE : remoteURL;

    	// Launch Browser
    	browserInstance.openBrowser(
    			pbaseURL, 
    			pbrowser,
    			GlobalVariables.BROWSER_BASE_ALIAS,
    			premoteURL
    			);
    	// Set maximum browser size.
    	browserInstance.maximizeBrowserWindow();    	
    	// Set browser size to 1024 by 768
    	//browserInstance.setWindowSize(GlobalVariables.BROWSER_WIDTH, GlobalVariables.BROWSER_HEIGHT);
    }
    /**
     *  -----------------------
     *  Suite Teardown Keywords
     *  -----------------------
     */
    
	/**
	 *  Close all browsers.
	 */
    
    @After(order=0)
    public void close_all_browsers() throws Throwable{
    	browserInstance.closeAllBrowsers();
    }
    
    /**
     *  ------------------------------------
     *  Reusable keywords - GIVEN STATEMENTS
     *  ------------------------------------
     */
    
    @Given("^User opens url \"(.*?)\" in \"(.*?)\" browser$")
    public void user_opens_browser(String url, String browser) throws Throwable{
    	browserInstance.openBrowser(
    			url, 
    			browser
    			);
    }
   
    @Given("^User opens url \"(.*?)\" in \"(.*?)\" with browser alias \"(.*?)\"$")
    public void user_opens_browser_with_alias(String url, String browser, String browserAlias) throws Throwable{
    	browserInstance.openBrowser(url, browser, browserAlias);
    }
    
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    
    @When("^User navigates to \"(.*?)\"$")
    public void user_navigates_to(String url) throws Throwable{
    	browserInstance.goTo(url);
    }
   
    @When("^User switches to browser \"(.*?)\"$")
    public void user_switches_to_browser(String browserAlias) throws Throwable{
    	browserInstance.switchBrowser(browserAlias);
    }
    
    @When("^User reloads page$")
    public void user_reloads_page(String browserAlias) throws Throwable{
    	browserInstance.reloadPage();
    }
    
    @When("^User gets title$")
    public void user_gets_title() throws Throwable{
    	String pageTitle = browserInstance.getTitle();
    }

    @When("^User closes browser$")
    public void user_closes_browser() throws Throwable{
    	browserInstance.closeBrowser();
    }
   
    /**
     *  -----------------------------------
     *  Reusable keywords - THEN STATEMENTS
     *  -----------------------------------
     */
    @Then("^User should be forwarded to \"(.*?)\"$")
    public void user_should_be_forwarded_to_correct_page(String uri) throws Throwable{
    	pvalue.setKey(uri);
    	String p_uri = pvalue.getPropertiesValue();
    	browserInstance.locationShouldContain(p_uri);
    }
    
    @Then("^Page title should contain \"(.*?)\"$")
    public void page_title_should_contain(String title) throws Throwable{
    	browserInstance.titleShouldContain(title);;
    } 
    
    @Then("^Page title should be \"(.*?)\"$")
    public void page_title_should_be(String title) throws Throwable{
    	browserInstance.titleShouldBe(title);;
    } 
    /**
     *  -----------------
     *  Utilities
     *  -----------------
     */
    
    @When("^Get system info$")
    public void get_system_info() throws Throwable {
    	String sysinfo = browserInstance.getSystemInfo();
    	System.out.println(sysinfo);
	}
}