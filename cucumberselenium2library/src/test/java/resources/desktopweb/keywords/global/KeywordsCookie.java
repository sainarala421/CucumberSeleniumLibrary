package resources.desktopweb.keywords.global;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import co.nz.enhanceconsulting.cucumberselenium2library.keywords.Cookie;
import cucumber.api.java.en.When;
import resources.utils.PropertiesValue;

public class KeywordsCookie{
    protected Cookie cookieInstance = new Cookie();
    protected WebDriver driver = KeywordsBrowserManagement.browserInstance.getCurrentWebDriver();
    protected ExtentTest test = KeywordsBrowserManagement.test;
    protected PropertiesValue pvalue = KeywordsBrowserManagement.pvalue;
    /**
     *  -----------------------------------
     *  Reusable keywords - WHEN STATEMENTS
     *  -----------------------------------
     */
    @When("^User adds cookie name \"(.*?)\" with value \"(.*?)\"$")
    public void user_adds_cookie(String cookieName, String cookieValue) throws Throwable{
    	cookieInstance.addCookie(driver, cookieName, cookieValue);
    }
    
    @When("^User gets value of cookie \"(.*?)\"$")
    public void user_get_cookie_value(String cookieName) throws Throwable{
    	//cookieInstance.getCookieValue(driver, cookieName);
    	System.out.printf("\r\n Cookie: %s \r\n", cookieInstance.getCookieValue(driver, cookieName));
    }
    
    @When("^User gets value of all cookies$")
    public void user_gets_cookies() throws Throwable{
    	//cookieInstance.getCookies(driver);
    	System.out.printf("\r\n Cookie: %s \r\n", cookieInstance.getCookies(driver));
    }
    
    @When("^User deletes cookie \"(.*?)\"$")
    public void user_deletes_cookie(String cookieName) throws Throwable{
    	cookieInstance.deleteCookie(driver, cookieName);
    }
    
    @When("^User deletes all cookies$")
    public void user_deletes_cookies() throws Throwable{
    	cookieInstance.deleteAllCookies(driver);
    }
}