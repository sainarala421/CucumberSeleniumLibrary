package com.github.enhancetafrancis.cucumberseleniumlibrary.keywords;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.RunOnFailureKeywordsAdapter;
import cucumber.api.Scenario;

@RobotKeywords
public class Screenshot extends RunOnFailureKeywordsAdapter {

	/**
	 * Instantiated BrowserManagement keyword bean
	 */
	@Autowired
	protected BrowserManagement browserManagement;
	/**
	 * Instantiated Logging keyword bean
	 */
	@Autowired
	protected LoggingLog4j logging;

	// ##############################
	// KeywordsBrowserManagement
	// ##############################
	public void capturePageScreenshot(WebDriver driver, Scenario scenario) {
		capturePageScreenshot(null, driver, scenario);
	}
    
	@ArgumentNames({ "filename=NONE" })
	public void capturePageScreenshot(String filename, WebDriver driver, Scenario scenario) {
    	File logdir = new File("target/cucumber-logs");
    	File path = new File(logdir, normalizeFilename(filename));
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
		if (takesScreenshot == null) {
			logging.warn("Can't take screenshot. No open browser found");
			return;
		}
		byte[] png = takesScreenshot.getScreenshotAs(OutputType.BYTES);
		writeScreenshot(path, png, scenario);
    }		
	protected void writeScreenshot(File path, byte[] png, Scenario scenario) {
		System.out.println("Line 62");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			fos.write(png);
			fos.flush();
			// Embed screenshot in cucumber log.
			byte[] data = FileUtils.readFileToByteArray(path);
			scenario.embed(data, "image/png");
		} catch (IOException e) {
			logging.warn(String.format("Can't write screenshot '%s'", path.getAbsolutePath()));
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logging.warn("Can't even close stream");
				}
			}
		}
	}
	protected String normalizeFilename(String filename) {
		if (filename == null) {
			Date now = new Date();
			DateFormat dateformat = new SimpleDateFormat("yyMMddhhmmss");
			filename = String.format("selenium-screenshot-%s.png",  dateformat.format(now));
		} else {
			filename = filename.replace('/', File.separatorChar);
		}
		return filename;
	}
}
