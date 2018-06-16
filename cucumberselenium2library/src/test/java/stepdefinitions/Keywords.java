package stepdefinitions;

import co.nz.enhanceconsulting.cucumberselenium2library.keywords.BrowserManagement;
//import cucumber.api.java.Before;
import cucumber.api.java.en.When;

public class Keywords{

    public Keywords() throws Exception {
    }
    BrowserManagement bm = new BrowserManagement();
    
    @When("^User opens url \"(.*?)\" in \"(.*?)\" browser$")
    public void user_opens_browser(String url, String browser) throws Throwable{
    	String x = bm.getSystemInfo();
    	System.out.println(x);
    	
    	bm.openBrowser(url, browser);
    	bm.goTo("https://www.netflix.com/");
    	String a = bm.getTitle();
    	System.out.println(a);
    	String z = bm.getRemoteCapabilities();
    	System.out.println(z);
    }
   
    @When("^User gets info$")
    public void get_system_info() throws Throwable {
    	System.out.println("Test Line 17");
    	String sysinfo = bm.getSystemInfo();
    	System.out.println(sysinfo);
    	String title = bm.getTitle();
    	System.out.println(title);
	}
}