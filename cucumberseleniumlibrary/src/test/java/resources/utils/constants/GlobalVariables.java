package resources.utils.constants;

/**
 * @author Francis John Agunday
 *
 * Global Variable Utility Class
 * Use variable class for variables that does not constantly change such a paths,
 * use properties files for variables that are subject to changes such as CSS locators
 *
 */
public class GlobalVariables{
    // Properties files
    public static final String BASE_PROPERTIES_FILE = "src/test/java/propertiesfiles/global/base.properties";
    public static final String FEATURES_PATH = "src/test/java/features";
    public static final String GLUE_KEYWORDS_PATH = "resources.desktopweb.keywords";
    public static final String LOG4J_PROPERTIES = "src/test/resources/log4j.properties";
    public static final String CUCUMBER_REPORTS_DIR = "target/cucumber-html-report";
    public static final String CUCUMBER_LOGS_DIR = "target/cucumber-logs";
    public static final String CUCUMBER_LOGS_TEMPLATES = "src/test/resources/templates";
    
    public static final String BASE_URL = "http://automationpractice.com";
    public static final String REMOTE_URL = "http://localhost:4444/wd/hub";
    public static final String REMOTE_URL_FALSE = "False";
    
    public static final String BROWSER = "chrome";
    public static final String BROWSER_BASE_ALIAS = "base_browser";
    public static final String BROWSER_WIDTH = "1366";
    public static final String BROWSER_HEIGHT = "768";
    
    public static final String SELENIUM_SPEED = "0.5";
    public static final String SELENIUM_TIMEOUT = "90 sec";
    
    public static final String USER_NAME = "abc@123.com";
    public static final String USER_PASSWORD = "Test@123";
    
    public static final int WAIT_FOR_VISIBILITY = 60;
 
}
