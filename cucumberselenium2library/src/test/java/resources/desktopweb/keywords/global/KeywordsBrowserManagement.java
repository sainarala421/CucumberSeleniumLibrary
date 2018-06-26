package resources.desktopweb.keywords.global;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.BrowserManagement;
import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Element;
import co.nz.enhanceconsulting.cucumberselenium2library.keywords.LoggingExtentReport;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;
import resources.utils.constants.GlobalVariables;

public class KeywordsBrowserManagement{
	// Constructor

    protected static PropertiesValue pvalue = new PropertiesValue();
    public static BrowserManagement browserInstance = new BrowserManagement();
    static Element elementInstance = new Element();
	protected LoggingExtentReport logExtentReportCache = new LoggingExtentReport();
	public static ExtentReports report = new ExtentReports();
	public static ExtentTest test;
    static String browser = System.getProperty("browser", "");
    static String baseURL = System.getProperty("baseURL", "");
    static String remoteURL = System.getProperty("remoteURL", "");

    public void setGlobalVariables() throws Throwable{
    }
    
	public LoggingExtentReport getExtentReportCache() {
		return logExtentReportCache;
	}

	public ExtentReports getCurrentCachedExtentReport() {
		return logExtentReportCache.getCurrentExtentReport();
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
        ExtentHtmlReporter htmlreporter = getExtentReportCache().InitialiseHtmlReporter();
    	report.attachReporter(htmlreporter);
    	test = report.createTest(Feature.class, "Cucumber tests");
    }
	
    @Before(order=1)
    public void launch_browser() throws Throwable{
    	test.createNode("Test Setup", "Launch browser");
    	test.log(Status.INFO, "Setting defaults");
    	
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
    
    @After
    public void close_all_browsers() throws Throwable{
    	test.createNode("Test Teardown","User closes all browsers");
    	test.log(Status.INFO, "Closing all browsers");
    	browserInstance.closeAllBrowsers();
    	report.flush();
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
    	test.log(Status.INFO, String.format("Page title: '%s'", pageTitle));
    }

    /**
     *  -----------------------------------
     *  Reusable keywords - THEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User should be forwarded to \"(.*?)\"$")
    public void user_should_be_forwarded_to_correct_page(String uri) throws Throwable{
    	pvalue.setKey(uri);
    	String p_uri = pvalue.getPropertiesValue();
    	test.createNode("Then",String.format("User should be forwarded to '%s': %s", uri, p_uri));
    	browserInstance.locationShouldContain(p_uri);
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