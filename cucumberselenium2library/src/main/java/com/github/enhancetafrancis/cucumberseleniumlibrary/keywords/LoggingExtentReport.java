package com.github.enhancetafrancis.cucumberseleniumlibrary.keywords;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import resources.utils.constants.GlobalVariables;

public class LoggingExtentReport{
    // ExtentReports
    public static final String EXTENT_REPORT_XML_PATH = "extentreports/extent-config.xml";
    public static final String EXTENT_REPORT_TARGET_PATH = "extent.html";
	//protected ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORT_TARGET_PATH);
	//protected ExtentTest extentTest;
    
    SessionExtentTestReport currentExtentTestReport;
	
	public ExtentHtmlReporter InitialiseHtmlReporter(){
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORT_TARGET_PATH);
        // ExtentReports initialization
    	htmlReporter.loadXMLConfig(EXTENT_REPORT_XML_PATH);

    	htmlReporter.setAppendExisting(true);
    	// make the charts visible on report open
    	htmlReporter.config().setChartVisibilityOnOpen(true);

    	// report title
    	htmlReporter.config().setDocumentTitle("ExtentReports");

    	// encoding, default = UTF-8
    	htmlReporter.config().setEncoding("UTF-8");

    	// protocol (http, https)
    	htmlReporter.config().setProtocol(Protocol.HTTPS);

    	// report or build name
    	htmlReporter.config().setReportName("Build-1224");

    	// chart location - top, bottom
    	htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);

    	// theme - standard, dark
    	htmlReporter.config().setTheme(Theme.STANDARD);

    	// set timeStamp format
    	htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");

    	// add custom css
    	htmlReporter.config().setCSS("css-string");

    	// add custom javascript
    	htmlReporter.config().setJS("js-string");
    	
		/* ExtentReports extentReports = new ExtentReports();
    	extentReports.attachReporter(htmlReporter);
    	currentExtentTest.extentTest = extentReports.createTest("Cucumber Selenium", "Cucumber tests"); */
    	return htmlReporter;
	}
	
	public ExtentReports getCurrentExtentReport() {
		if (currentExtentTestReport != null) {
			return currentExtentTestReport.extentReport;
		}
		return null;
	}
	
	public static class SessionExtentTestReport {
		public ExtentReports extentReport;
	}
	
}