package co.nz.enhanceconsulting.cucumberselenium2library.keywords;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import co.nz.enhanceconsulting.cucumberselenium2library.locators.ElementFinder;
import co.nz.enhanceconsulting.cucumberselenium2library.utils.Python;
import co.nz.enhanceconsulting.cucumberselenium2library.utils.RunOnFailureKeywordsAdapter;
import co.nz.enhanceconsulting.cucumberselenium2library.utils.Selenium2LibraryNonFatalException;
import resources.desktopweb.keywords.global.KeywordsBrowserManagement;
import resources.utils.constants.GlobalVariables;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RobotKeywords
public class Element extends RunOnFailureKeywordsAdapter {

	/**
	 * Instantiated BrowserManagement keyword bean
	 */
	@Autowired
	protected BrowserManagement browserManagement;
	
	protected ExtentTest test = KeywordsBrowserManagement.test;
	/**
	 * Instantiated FormElement keyword bean
	 */
	@Autowired
	protected FormElement formElement;

	/**
	 * Instantiated Logging keyword bean
	 */
	@Autowired
	private static final Logger logging = LogManager.getLogger(BrowserManagement.class.getName());

	// ##############################
	// KeywordsBrowserManagement - Element Lookups
	// ##############################

	@RobotKeywordOverload
	@ArgumentNames({ "text" })
	public void currentFrameContains(WebDriver driver, String text) {
		currentFrameContains(driver, text, GlobalVariables.INFO);
	}

	/**
	 * Verify the current frame contains <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about log levels.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "text", "logLevel=INFO" })
	public void currentFrameContains(WebDriver driver, String text, Level logLevel) {
		if (!isTextPresent(driver, text)) {
			logging.log(logLevel, String.format("Current Frame Contains: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Page should have contained text '%s', but did not.", text));
		} else {
			logging.log(logLevel, String.format("Current Frame Contains: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void currentFrameShouldNotContain(WebDriver driver, String text) {
		currentFrameShouldNotContain(driver, text, GlobalVariables.INFO);
	}

	/**
	 * Verify the current frame does not contain <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about log levels.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "text", "logLevel=INFO" })
	public void currentFrameShouldNotContain(WebDriver driver, String text, Level logLevel) {
		if (isTextPresent(driver, text)) {
			logging.log(logLevel, String.format("Current Frame Should Not Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Page should have not contained text '%s', but did.", text));
		} else {
			logging.log(logLevel, String.format("Current Frame Should Not Contain: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void elementShouldContain(WebDriver driver, String locator, String text) {
		elementShouldContain(driver, locator, text, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> contains <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about locators.
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void elementShouldContain(WebDriver driver, String locator, String text, String message) {
		String actual = getText(driver, locator);

		if (!actual.toLowerCase().contains(text.toLowerCase())) {
			logging.info(String.format("Element Should Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Element should have contained text '%s', but its text was %s.", text, actual));
		} else {
			logging.info(String.format("Element Should Contain: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void elementShouldNotContain(WebDriver driver, String locator, String text) {
		elementShouldNotContain(driver, locator, text, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> does not contain
	 * <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about locators.
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void elementShouldNotContain(WebDriver driver, String locator, String text, String message) {
		String actual = getText(driver, locator);

		if (actual.toLowerCase().contains(text.toLowerCase())) {
			logging.info(String.format("Element Should Not Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Element should not have contained text '%s', but its text was %s.", text, actual));
		} else {
			logging.info(String.format("Element Should Not Contain: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void frameShouldContain(WebDriver driver, String locator, String text) {
		frameShouldContain(driver, locator, text, GlobalVariables.INFO);
	}

	/**
	 * Verify the frame identified by <b>locator</b> contains <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about locators.
	 * 
	 * @param locator
	 *            The locator to locate the frame.
	 * @param text
	 *            The text to verify.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "logLevel=INFO" })
	public void frameShouldContain(WebDriver driver, String locator, String text, Level logLevel) {
		if (!frameContains(driver, locator, text)) {
			logging.log(logLevel, String.format("Frame Should Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Frame should have contained text '%s', but did not.", text));
		} else {
			logging.log(logLevel, String.format("Frame Should Contain: %s => OK", text));
		}
	}

	/**
	 * Verify the frame identified by <b>locator</b> does not contain
	 * <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about locators.
	 * 
	 * @param locator
	 *            The locator to locate the frame.
	 * @param text
	 *            The text to verify.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "logLevel=INFO" })
	public void frameShouldNotContain(WebDriver driver, String locator, String text, Level logLevel) {
		if (frameContains(driver, locator, text)) {
			logging.log(logLevel, String.format("Frame Should Not Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Frame should not have contained text '%s', but did.", text));
		} else {
			logging.log(logLevel, String.format("Frame Should Not Contain: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void pageShouldContain(WebDriver driver, String text) {
		pageShouldContain(driver, text, GlobalVariables.INFO);
	}

	/**
	 * Verify the current page contains <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about log levels.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "text", "logLevel=INFO" })
	public void pageShouldContain(WebDriver driver, String text, Level logLevel) {
		if (!pageContains(driver, text)) {
			logging.log(logLevel, String.format("Page Should Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Page should have contained text '%s' but did not.", text));
		} else {
			logging.log(logLevel, String.format("Page Should Contain: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void pageShouldNotContain(WebDriver driver, String text) {
		pageShouldNotContain(driver, text, GlobalVariables.INFO);
	}

	/**
	 * Verify the current page does not contain <b>text</b>.<br>
	 * <br>
	 * See `Introduction` for details about log levels.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "text", "logLevel=INFO" })
	public void pageShouldNotContain(WebDriver driver, String text, Level logLevel) {
		if (pageContains(driver, text)) {
			logging.log(logLevel, String.format("Page Should Not Contain: %s => FAILED", text));
			throw new Selenium2LibraryNonFatalException(
					String.format("Page should not have contained text '%s' but did.", text));
		} else {
			logging.log(logLevel, String.format("Page Should Not Contain: %s => OK", text));
		}
	}

	@RobotKeywordOverload
	public void pageShouldContainElement(WebDriver driver, String locator) {
		pageShouldContainElement(driver, locator, "", GlobalVariables.INFO);
	}

	@RobotKeywordOverload
	public void pageShouldContainElement(WebDriver driver, String locator, String message) {
		pageShouldContainElement(driver, locator, message, GlobalVariables.INFO);
	}

	/**
	 * Verify the element identified by <b>locator</b> is found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainElement(WebDriver driver, String locator, String message, Level logLevel) {
		pageShouldContainElement(driver, locator, null, message, GlobalVariables.INFO);
	}

	protected void pageShouldContainElement(WebDriver driver, String locator, String tag, String message, Level logLevel) {
		String name = tag != null ? tag : "element";
		if (!isElementPresent(driver, locator, tag)) {
			if (message == null || message.equals("")) {
				message = String.format("Page should have contained %s '%s' but did not", name, locator);
			}
			logging.log(logLevel, message);
			throw new Selenium2LibraryNonFatalException(message);
		} else {
			logging.log(logLevel, String.format("Current page contains %s '%s'.", name, locator));
		}
	}

	@RobotKeywordOverload
	public void pageShouldNotContainElement(WebDriver driver, String locator) {
		pageShouldNotContainElement(driver, locator, "", GlobalVariables.INFO);
	}

	@RobotKeywordOverload
	public void pageShouldNotContainElement(WebDriver driver, String locator, String message) {
		pageShouldNotContainElement(driver, locator, message, GlobalVariables.INFO);
	}

	/**
	 * Verify the element identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainElement(WebDriver driver, String locator, String message, Level logLevel) {
		pageShouldNotContainElement(driver, locator, null, message, GlobalVariables.INFO);
	}

	protected void pageShouldNotContainElement(WebDriver driver, String locator, String tag, String message, Level logLevel) {
		String name = tag != null ? tag : "element";
		if (isElementPresent(driver, locator, tag)) {
			if (message == null || message.equals("")) {
				message = String.format("Page should not have contained %s '%s' but did", name, locator);
			}
			logging.log(logLevel, message);
			throw new Selenium2LibraryNonFatalException(message);
		} else {
			logging.log(logLevel, String.format("Current page does not contain %s '%s'.", name, locator));
		}
	}

	// ##############################
	// KeywordsBrowserManagement - Attributes
	// ##############################

	/**
	 * Assigns a temporary identifier to the element identified by
	 * <b>locator</b><br>
	 * <br>
	 * This is mainly useful, when the locator is a complicated and slow XPath
	 * expression. The identifier expires when the page is reloaded.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Assign ID to Element</td>
	 * <td>xpath=//div[@id=\"first_div\"]</td>
	 * <td>my id</td>
	 * </tr>
	 * <tr>
	 * <td>Page Should Contain Element</td>
	 * <td>my id</td>
	 * <td></td>
	 * </tr>
	 * </table>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param id
	 *            The id to assign.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "id" })
	public void assignIdToElement(WebDriver driver, String locator, String id) {
		logging.info(String.format("Assigning temporary id '%s' to element '%s'", id, locator));
		List<WebElement> elements = elementFind(driver, locator, true, true);

		((JavascriptExecutor) driver)
				.executeScript(String.format("arguments[0].id = '%s';", id), elements.get(0));
	}

	/**
	 * Verify the element identified by <b>locator</b> is enabled.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void elementShouldBeEnabled(WebDriver driver, String locator) {
		if (!isEnabled(driver, locator)) {
			throw new Selenium2LibraryNonFatalException(String.format("Element %s is disabled.", locator));
		}
	}

	/**
	 * Verify the element identified by <b>locator</b> is disabled.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void elementShouldBeDisabled(WebDriver driver, String locator) {
		if (isEnabled(driver, locator)) {
			throw new Selenium2LibraryNonFatalException(String.format("Element %s is enabled.", locator));
		}
	}

	@RobotKeywordOverload
	public void elementShouldBeSelected(WebDriver driver, String locator) {
		elementShouldBeSelected(driver, locator, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> is selected.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE" })
	public void elementShouldBeSelected(WebDriver driver, String locator, String message) {
		logging.info(String.format("Verifying element '%s' is selected.", locator));
		boolean selected = isSelected(driver, locator);

		if (!selected) {
			if (message == null || message.equals("")) {
				message = String.format("Element '%s' should be selected, but it is not.", locator);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementShouldNotBeSelected(WebDriver driver, String locator) {
		elementShouldNotBeSelected(driver, locator, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> is not selected.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE" })
	public void elementShouldNotBeSelected(WebDriver driver, String locator, String message) {
		logging.info(String.format("Verifying element '%s' is not selected.", locator));
		boolean selected = isSelected(driver, locator);

		if (selected) {
			if (message == null || message.equals("")) {
				message = String.format("Element '%s' should not be selected, but it is.", locator);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementShouldBeVisible(WebDriver driver, String locator) {
		elementShouldBeVisible(driver, locator, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> is visible.<br>
	 * <br>
	 * Herein, visible means that the element is logically visible, not
	 * optically visible in the current browser viewport. For example, an
	 * element that carries display:none is not logically visible, so using this
	 * keyword on that element would fail.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE" })
	public void elementShouldBeVisible(WebDriver driver, String locator, String message) {
		test.log(Status.INFO, String.format("Verifying element '%s' is visible.", locator));
		//logging.info(String.format("Verifying element '%s' is visible.", locator));
		boolean visible = isVisible(driver, locator);

		if (!visible) {
			if (message == null || message.equals("")) {
				message = String.format("Element '%s' should be visible, but it is not.", locator);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementShouldNotBeVisible(WebDriver driver, String locator) {
		elementShouldNotBeVisible(driver, locator, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> is not visible.<br>
	 * <br>
	 * Herein, visible means that the element is logically visible, not
	 * optically visible in the current browser viewport. For example, an
	 * element that carries display:none is not logically visible, so using this
	 * keyword on that element would fail.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE" })
	public void elementShouldNotBeVisible(WebDriver driver, String locator, String message) {
		logging.info(String.format("Verifying element '%s' is not visible.", locator));
		boolean visible = isVisible(driver, locator);

		if (visible) {
			if (message == null || message.equals("")) {
				message = String.format("Element '%s' should not be visible, but it is.", locator);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementShouldBeClickable(WebDriver driver, String locator) {
		elementShouldBeClickable(driver, locator, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> is clickable.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE" })
	public void elementShouldBeClickable(WebDriver driver, String locator, String message) {
		logging.info(String.format("Verifying element '%s' is clickable.", locator));
		boolean clickable = isClickable(driver, locator);

		if (!clickable) {
			if (message == null || message.equals("")) {
				message = String.format("Element '%s' should be clickable, but it is not.", locator);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementShouldNotBeClickable(WebDriver driver, String locator) {
		elementShouldNotBeClickable(driver, locator, "");
	}

	/**
	 * Verify the element identified by <b>locator</b> is not clickable.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE" })
	public void elementShouldNotBeClickable(WebDriver driver, String locator, String message) {
		logging.info(String.format("Verifying element '%s' is not clickable.", locator));
		boolean clickable = isClickable(driver, locator);

		if (clickable) {
			if (message == null || message.equals("")) {
				message = String.format("Element '%s' should not be clickable, but it is.", locator);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementTextShouldBe(WebDriver driver, String locator, String expected) {
		elementTextShouldBe(driver, locator, expected, "");
	}

	/**
	 * Verify the text of the element identified by <b>locator</b> is exactly
	 * <b>text</b>.<br>
	 * <br>
	 * In contrast to `Element Should Contain`, this keyword does not try a
	 * substring match but an exact match on the element identified by locator.
	 * <br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void elementTextShouldBe(WebDriver driver, String locator, String text, String message) {
		List<WebElement> elements = elementFind(driver, locator, true, true);
		String actual = elements.get(0).getText();

		if (!text.equals(actual)) {
			if (message == null || message.equals("")) {
				message = String.format("The text of element '%s' should have been '%s', but it was '%s'.", locator,
						text, actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	@RobotKeywordOverload
	public void elementTextShouldNotBe(WebDriver driver, String locator, String expected) {
		elementTextShouldNotBe(driver, locator, expected, "");
	}

	/**
	 * Verify the text of the element identified by <b>locator</b> is not
	 * exactly <b>text</b>.<br>
	 * <br>
	 * In contrast to `Element Should Not Contain`, this keyword does not try a
	 * substring match but an exact match on the element identified by locator.
	 * <br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void elementTextShouldNotBe(WebDriver driver, String locator, String text, String message) {
		List<WebElement> elements = elementFind(driver, locator, true, true);
		String actual = elements.get(0).getText();

		if (text.equals(actual)) {
			if (message == null || message.equals("")) {
				message = String.format("The text of element '%s' should have been '%s', but it was '%s'.", locator,
						text, actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
	}

	/**
	 * Returns the value of an element attribute.<br>
	 * <br>
	 * The <b>attribute_locator</b> consists of element locator followed by an @
	 * sign and attribute name. Example: element_id@class<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param attributeLocator
	 *            The attribute locator.
	 * @return The attribute value.
	 */
	@RobotKeyword
	@ArgumentNames({ "attributeLocator" })
	public String getElementAttribute(WebDriver driver, String attributeLocator) {
		String[] parts = parseAttributeLocator(attributeLocator);

		List<WebElement> elements = elementFind(driver, parts[0], true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(String.format("Element '%s' not found.", parts[0]));
		}

		return elements.get(0).getAttribute(parts[1]);
	}

	/**
	 * Clears the text from element identified by <b>locator</b>.<br>
	 * <br>
	 * This keyword does not execute any checks on whether or not the clear
	 * method has succeeded, so if any subsequent checks are needed, they should
	 * be executed using method `Element Text Should Be`.<br>
	 * <br>
	 * Also, this method will use WebDriver's internal _element.clear()_ method,
	 * i.e. it will not send any keypresses, and it will not have any effect
	 * whatsoever on elements other than input textfields or input textareas.
	 * Clients relying on keypresses should implement their own methods.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void clearElementText(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, true);

		elements.get(0).clear();
	}

	/**
	 * Returns inner element id by index<b></b> of element identified by
	 * <b>locator</b> which is matched by <b>matchid</b>.<br>
	 * <br>
	 * The position is returned in pixels off the left side of the page, as an
	 * integer. Fails if the matching element is not found.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 *
	 * @param locator
	 *            The locator to locate the element.
	 * @param matchid
	 *            partial inner element id to match
	 * @param index
	 *            position of the inner element to match
	 * @return The element id
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "matchid", "index" })
	public String getInnerElementId(WebDriver driver, String locator, String matchid, int index) {
		List<WebElement> elements = elementFind(driver, locator, true, true);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(String.format("get Inner element '%s' not found.", locator));
		}

		String xpathId = ".//*[contains(@id," + matchid + ")]";

		List<WebElement> tmpe = elements.get(0).findElements((By.xpath(xpathId)));
		if (tmpe.size() == 0) {
			throw new Selenium2LibraryNonFatalException(
					String.format("No Inner element '%s' not found by '%s'", locator, matchid));
		}
		String eId = tmpe.get(index).getAttribute("id");

		logging.info(String.format("Found element ID: '%s'.", eId));

		return eId;

	}

	/**
	 * Returns horizontal position of element identified by <b>locator</b>.<br>
	 * <br>
	 * The position is returned in pixels off the left side of the page, as an
	 * integer. Fails if the matching element is not found.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @return The horizontal position
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public int getHorizontalPosition(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(
					String.format("Could not determine position for '%s'.", locator));
		}

		return elements.get(0).getLocation().getX();
	}

	/**
	 * Returns the value attribute of the element identified by <b>locator</b>.
	 * <br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @return The value attribute of the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public String getValue(WebDriver driver, String locator) {
		return getValue(driver, locator, null);
	}

	protected String getValue(WebDriver driver, String locator, String tag) {
		List<WebElement> elements = elementFind(driver, locator, true, false, tag);

		if (elements.size() == 0) {
			return null;
		}

		return elements.get(0).getAttribute("value");
	}

	/**
	 * Returns the text of the element identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @return The text of the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public String getText(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, true);

		if (elements.size() == 0) {
			return null;
		}

		return elements.get(0).getText();
	}

	/**
	 * Returns vertical position of element identified by <b>locator</b>.<br>
	 * <br>
	 * The position is returned in pixels off the top of the page, as an
	 * integer. Fails if the matching element is not found.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @return The vertical position
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public int getVerticalPosition(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(
					String.format("Could not determine position for '%s'.", locator));
		}

		return elements.get(0).getLocation().getY();
	}

	// ##############################
	// KeywordsBrowserManagement - Mouse Input/Events
	// ##############################

	/**
	 * Click on the element identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void clickElement(WebDriver driver, String locator) {
		test.log(Status.INFO, String.format("Clicking element '%s'.", locator));
		List<WebElement> elements = elementFind(driver, locator, true, true);

		elements.get(0).click();
	}

	/**
	 * Click on the element identified by <b>locator</b> at the coordinates
	 * <b>xOffset</b> and <b>yOffset</b>.<br>
	 * <br>
	 * The cursor is moved at the center of the element and the to the given x/y
	 * offset from that point. Both offsets are specified as negative (left/up)
	 * or positive (right/down) number.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * <br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param xOffset
	 *            The horizontal offset in pixel. Negative means left, positive
	 *            right.
	 * @param yOffset
	 *            The vertical offset in pixel. Negative means up, positive
	 *            down.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "xOffset", "yOffset" })
	public void clickElementAtCoordinates(WebDriver driver, String locator, String xOffset, String yOffset) {
		logging.info(String.format("Clicking element '%s'in coordinates '%s', '%s'.", locator, xOffset, yOffset));
		List<WebElement> elements = elementFind(driver, locator, true, true);

		WebElement element = elements.get(0);
		Actions action = new Actions(driver);
		action.moveToElement(element).moveByOffset(Integer.parseInt(xOffset), Integer.parseInt(yOffset)).perform();
	}

	/**
	 * Double-Click on the element identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void doubleClickElement(WebDriver driver, String locator) {
		logging.info(String.format("Double clicking element '%s'.", locator));

		List<WebElement> elements = elementFind(driver, locator, true, true);
		Actions action = new Actions(browserManagement.getCurrentWebDriver());

		action.doubleClick(elements.get(0)).perform();
	}

	/**
	 * Set the focus to the element identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "driver", "locator" })
	public void focus(WebDriver driver, String locator) {
		test.log(Status.INFO, String.format("Setting focus to element '%s'", locator));
		List<WebElement> elements = elementFind(driver, locator, true, true);
		((JavascriptExecutor) driver).executeScript("arguments[0].focus();",
				elements.get(0));
	}

	/**
	 * Drag the element identified by the locator <b>source</b> and move it on
	 * top of the element identified by the locator <b>target</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Drag And Drop</td>
	 * <td>elem1</td>
	 * <td>elem2</td>
	 * <td># Move elem1 over elem2</td>
	 * </tr>
	 * </table>
	 * 
	 * @param source
	 *            The locator to locate the element to drag.
	 * @param target
	 *            The locator to locate the element where to drop the dragged
	 *            element.
	 */
	@RobotKeyword
	@ArgumentNames({ "source", "target" })
	public void dragAndDrop(WebDriver driver, String source, String target) {
		List<WebElement> sourceElements = elementFind(driver, source, true, true);
		List<WebElement> targetElements = elementFind(driver, target, true, true);

		Actions action = new Actions(browserManagement.getCurrentWebDriver());
		action.dragAndDrop(sourceElements.get(0), targetElements.get(0)).perform();
	}

	/**
	 * Drag the element identified by the locator <b>source</b> and move it by
	 * <b>xOffset</b> and <b>yOffset</b>.<br>
	 * <br>
	 * Both offsets are specified as negative (left/up) or positive (right/down)
	 * number.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Drag And Drop By Offset</td>
	 * <td>elem1</td>
	 * <td>50</td>
	 * <td>35</td>
	 * <td># Move elem1 50px right and 35px down.</td>
	 * </tr>
	 * </table>
	 * 
	 * @param source
	 *            The locator to locate the element to drag.
	 * @param xOffset
	 *            The horizontal offset in pixel. Negative means left, positive
	 *            right.
	 * @param yOffset
	 *            The vertical offset in pixel. Negative means up, positive
	 *            down.
	 */
	@RobotKeyword
	@ArgumentNames({ "source", "xOffset", "yOffset" })
	public void dragAndDropByOffset(WebDriver driver, String source, int xOffset, int yOffset) {
		List<WebElement> elements = elementFind(driver, source, true, true);

		Actions action = new Actions(driver);
		action.dragAndDropBy(elements.get(0), xOffset, yOffset).perform();
	}

	/**
	 * Simulates pressing the left mouse button on the element identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * The element is pressed without releasing the mouse button.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @see Element#mouseDownOnImage
	 * @see Element#mouseDownOnLink
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void mouseDown(WebDriver driver, String locator) {
		logging.info(String.format("Simulating Mouse Down on element '%s'.", locator));
		List<WebElement> elements = elementFind(driver, locator, true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(String.format("ERROR: Element %s not found.", locator));
		}

		Actions action = new Actions(driver);
		action.clickAndHold(elements.get(0)).perform();
	}

	/**
	 * Simulates moving the mouse away from the element identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void mouseOut(WebDriver driver, String locator) {
		logging.info(String.format("Simulating Mouse Out on element '%s'.", locator));
		List<WebElement> elements = elementFind(driver, locator, true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(String.format("ERROR: Element %s not found.", locator));
		}

		WebElement element = elements.get(0);
		Dimension size = element.getSize();
		int offsetX = size.getWidth() / 2 + 1;
		int offsetY = size.getHeight() / 2 + 1;

		Actions action = new Actions(driver);
		action.moveToElement(element).moveByOffset(offsetX, offsetY).perform();
	}

	/**
	 * Simulates moving the mouse over the element identified by <b>locator</b>.
	 * <br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void mouseOver(WebDriver driver, String locator) {
		logging.info(String.format("Simulating Mouse Over on element '%s'.", locator));
		List<WebElement> elements = elementFind(driver, locator, true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(String.format("ERROR: Element %s not found.", locator));
		}

		WebElement element = elements.get(0);
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * Simulates releasing the left mouse button on the element identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void mouseUp(WebDriver driver, String locator) {
		logging.info(String.format("Simulating Mouse Up on element '%s'.", locator));
		List<WebElement> elements = elementFind(driver, locator, true, false);

		if (elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(String.format("ERROR: Element %s not found.", locator));
		}

		WebElement element = elements.get(0);
		Actions action = new Actions(driver);
		action.release(element).perform();
	}

	/**
	 * Opens the context menu on the element identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void openContextMenu(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, true);

		Actions action = new Actions(driver);
		action.contextClick(elements.get(0)).perform();
	}

	/**
	 * Simulates the given <b>event</b> on the element identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * This keyword is especially useful, when the element has an OnEvent
	 * handler that needs to be explicitly invoked.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param event
	 *            The event to invoke.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "event" })
	public void simulate(WebDriver driver, String locator, String event) {
		List<WebElement> elements = elementFind(driver, locator, true, true);
		String script = "element = arguments[0];" + "eventName = arguments[1];" + "if (document.createEventObject) {"
				+ "return element.fireEvent('on' + eventName, document.createEventObject());" + "}"
				+ "var evt = document.createEvent(\"HTMLEvents\");" + "evt.initEvent(eventName, true, true);"
				+ "return !element.dispatchEvent(evt);";

		((JavascriptExecutor) browserManagement.getCurrentWebDriver()).executeScript(script, elements.get(0), event);
	}

	/**
	 * Simulates pressing <b>key</b> on the element identified by <b>locator</b>
	 * .<br>
	 * <br>
	 * Key is either a single character, or a numerical ASCII code of the key
	 * lead by '\\'.<br>
	 * <br>
	 * Key attributes for arbitrary elements are id and name. See `Introduction`
	 * for details about locators.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Press Key</td>
	 * <td>text_field</td>
	 * <td>q</td>
	 * <td># Press 'q'</td>
	 * </tr>
	 * <tr>
	 * <td>Press Key</td>
	 * <td>login_button</td>
	 * <td>\\13</td>
	 * <td># ASCII code for enter key</td>
	 * </tr>
	 * </table>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param key
	 *            The key to press.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "key" })
	public void pressKey(WebDriver driver, String locator, String key) {
		if (key.startsWith("\\") && key.length() > 1) {
			key = mapAsciiKeyCodeToKey(Integer.parseInt(key.substring(1))).toString();
		}
		List<WebElement> element = elementFind(driver, locator, true, true);
		element.get(0).sendKeys(key);
	}

	// ##############################
	// KeywordsBrowserManagement - Links
	// ##############################

	/**
	 * Click on the link identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for links are id, name, href and link text. See
	 * `Introduction` for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the link.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void clickLink(WebDriver driver, String locator) {
		logging.info(String.format("Clicking link '%s'.", locator));
		List<WebElement> elements = elementFind(driver, locator, true, true, "a");

		elements.get(0).click();
	}

	/**
	 * Returns a list containing ids of all links found in current page.<br>
	 * <br>
	 * If a link has no id, an empty string will be in the list instead.<br>
	 * 
	 * @return The list of link ids.
	 */
	@RobotKeyword
	public ArrayList<String> getAllLinks(WebDriver driver) {
		ArrayList<String> ret = new ArrayList<String>();

		List<WebElement> elements = elementFind(driver, "tag=a", false, false, "a");
		for (WebElement element : elements) {
			ret.add(element.getAttribute("id"));
		}

		return ret;
	}

	/**
	 * Simulates pressing the left mouse button on the link identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * The element is pressed without releasing the mouse button.<br>
	 * <br>
	 * Key attributes for links are id, name, href and link text. See
	 * `Introduction` for details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @see Element#mouseDown
	 * @see Element#mouseDownOnImage
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void mouseDownOnLink(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, true, "link");

		Actions action = new Actions(driver);
		action.clickAndHold(elements.get(0)).perform();
	}

	@RobotKeywordOverload
	@ArgumentNames({ "locator" })
	public void pageShouldContainLink(WebDriver driver, String locator) {
		pageShouldContainLink(driver, locator, "", GlobalVariables.INFO);
	}

	@RobotKeywordOverload
	@ArgumentNames({ "locator", "message=NONE" })
	public void pageShouldContainLink(WebDriver driver, String locator, String message) {
		pageShouldContainLink(driver, locator, message, GlobalVariables.INFO);
	}

	/**
	 * Verify the link identified by <b>locator</b> is found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for links are id, name, href and link text. See
	 * `Introduction` for details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the link.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainLink(WebDriver driver, String locator, String message, Level logLevel) {
		pageShouldContainElement(driver, locator, "link", message, logLevel);
	}

	@RobotKeywordOverload
	public void pageShouldNotContainLink(WebDriver driver, String locator) {
		pageShouldNotContainLink(driver, locator, "", GlobalVariables.INFO);
	}

	@RobotKeywordOverload
	public void pageShouldNotContainLink(WebDriver driver, String locator, String message) {
		pageShouldNotContainLink(driver, locator, message, GlobalVariables.INFO);
	}

	/**
	 * Verify the link identified by <b>locator</b> is not found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for links are id, name, href and link text. See
	 * `Introduction` for details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the link.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainLink(WebDriver driver, String locator, String message, Level logLevel) {
		pageShouldNotContainElement(driver, locator, "link", message, logLevel);
	}

	// ##############################
	// KeywordsBrowserManagement - Images
	// ##############################

	/**
	 * Click on the image identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for images are id, src and alt. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void clickImage(WebDriver driver, String locator) {
		logging.info(String.format("Clicking image '%s'.", locator));

		List<WebElement> elements = elementFind(driver, locator, true, false, "image");

		if (elements.size() == 0) {
			elements = elementFind(driver, locator, true, true, "input");
		}
		WebElement element = elements.get(0);
		element.click();
	}

	/**
	 * Simulates pressing the left mouse button on the image identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * The element is pressed without releasing the mouse button.<br>
	 * <br>
	 * Key attributes for images are id, src and alt. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @see Element#mouseDown
	 * @see Element#mouseDownOnLink
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void mouseDownOnImage(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, true, "image");

		Actions action = new Actions(driver);
		action.clickAndHold(elements.get(0)).perform();
	}

	@RobotKeywordOverload
	@ArgumentNames({ "locator" })
	public void pageShouldContainImage(WebDriver driver, String locator) {
		pageShouldContainImage(driver, locator, "", GlobalVariables.INFO);
	}

	@RobotKeywordOverload
	@ArgumentNames({ "locator", "message=NONE" })
	public void pageShouldContainImage(WebDriver driver, String locator, String message) {
		pageShouldContainImage(driver, locator, message, GlobalVariables.INFO);
	}

	/**
	 * Verify the image identified by <b>locator</b> is found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for images are id, src and alt. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the link.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainImage(WebDriver driver, String locator, String message, Level logLevel) {
		pageShouldContainElement(driver, locator, "image", message, logLevel);
	}

	@RobotKeywordOverload
	@ArgumentNames({ "locator" })
	public void pageShouldNotContainImage(WebDriver driver, String locator) {
		pageShouldNotContainImage(driver, locator, "", GlobalVariables.INFO);
	}

	@RobotKeywordOverload
	@ArgumentNames({ "locator", "message=NONE" })
	public void pageShouldNotContainImage(WebDriver driver, String locator, String message) {
		pageShouldNotContainImage(driver, locator, message, GlobalVariables.INFO);
	}

	/**
	 * Verify the image identified by <b>locator</b> is not found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for images are id, src and alt. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the link.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainImage(WebDriver driver, String locator, String message, Level logLevel) {
		pageShouldNotContainElement(driver, locator, "image", message, logLevel);
	}

	// ##############################
	// KeywordsBrowserManagement - Xpath
	// ##############################

	/**
	 * Returns the number of elements located the given <b>xpath</b>.<br>
	 * <br>
	 * If you wish to assert the number of located elements, use `Xpath Should
	 * Match X Times`.<br>
	 * 
	 * @param xpath
	 *            The XPath to match page elements
	 * @return The number of located elements
	 */
	@RobotKeyword
	@ArgumentNames({ "xpath" })
	public int getMatchingXpathCount(WebDriver driver, String xpath) {
		if (!xpath.startsWith("xpath=")) {
			xpath = "xpath=" + xpath;
		}
		List<WebElement> elements = elementFind(driver, xpath, false, false);

		return elements.size();
	}

	@RobotKeywordOverload
	@ArgumentNames({ "xpath", "expectedXpathCount" })
	public void xpathShouldMatchXTimes(WebDriver driver, String xpath, int expectedXpathCount) {
		xpathShouldMatchXTimes(driver, xpath, expectedXpathCount, "");
	}

	@RobotKeywordOverload
	@ArgumentNames({ "xpath", "expectedXpathCount", "message=NONE" })
	public void xpathShouldMatchXTimes(WebDriver driver, String xpath, int expectedXpathCount, String message) {
		xpathShouldMatchXTimes(driver, xpath, expectedXpathCount, message, GlobalVariables.INFO);
	}

	/**
	 * Verify that the page contains the <b>expectedXpathCount</b> of elements
	 * located by the given <b>xpath</b>.<br>
	 * 
	 * @param xpath
	 *            The XPath to match page elements
	 * @param expectedXpathCount
	 *            The expected number of located elements
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "xpath", "expectedXpathCount", "message=NONE", "logLevel=INFO" })
	public void xpathShouldMatchXTimes(WebDriver driver, String xpath, int expectedXpathCount, String message, Level logLevel) {
		if (!xpath.startsWith("xpath=")) {
			xpath = "xpath=" + xpath;
		}
		List<WebElement> elements = elementFind(driver, xpath, false, false);
		int actualXpathCount = elements.size();

		if (actualXpathCount != expectedXpathCount) {
			if (message == null || message.equals("")) {
				message = String.format("Xpath %s should have matched %s times but matched %s times.", xpath,
						expectedXpathCount, actualXpathCount);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}

		logging.log(logLevel, String.format("Current page contains %s elements matching '%s'.", actualXpathCount, xpath));
	}

	// ##############################
	// Internal Methods
	// ##############################

	protected List<WebElement> elementFind(WebDriver driver, String locator, boolean firstOnly, boolean required) {
		return elementFind(driver, locator, firstOnly, required, null);
	}

	protected List<WebElement> elementFind(WebDriver driver, String locator, boolean firstOnly, boolean required, String tag) {
		List<WebElement> elements = ElementFinder.find(driver, locator, tag);
		if (required && elements.size() == 0) {
			throw new Selenium2LibraryNonFatalException(
					String.format("Element locator '%s' did not match any elements.", locator));
		}

		if (firstOnly) {
			if (elements.size() > 1) {
				List<WebElement> tmp = new ArrayList<WebElement>();
				tmp.add(elements.get(0));
				elements = tmp;
			}
		}

		return elements;
	}

	protected boolean frameContains(WebDriver driver, String locator, String text) {
		//WebDriver current = browserManagement.getCurrentWebDriver();
		List<WebElement> elements = elementFind(driver, locator, true, true);

		driver.switchTo().frame(elements.get(0));
		logging.info(String.format("Searching for text from frame '%s'.", locator));
		boolean found = isTextPresent(driver, text);
		driver.switchTo().defaultContent();

		return found;
	}

	protected boolean isTextPresent(WebDriver driver, String text) {
		String locator = String.format("xpath=//*[contains(., %s)]", escapeXpathValue(text));

		return isElementPresent(driver, locator);
	}

	protected boolean isEnabled(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, true);
		WebElement element = elements.get(0);

		if (!formElement.isFormElement(element)) {
			throw new Selenium2LibraryNonFatalException(String.format("ERROR: Element %s is not an input.", locator));
		}
		if (!element.isEnabled()) {
			return false;
		}
		String readonly = element.getAttribute("readonly");
		if (readonly != null && (readonly.equals("readonly") || readonly.equals("true"))) {
			return false;
		}

		return true;
	}

	protected boolean isVisible(WebDriver driver, String locator) {
		List<WebElement> elements = elementFind(driver, locator, true, false);
		if (elements.size() == 0) {
			return false;
		}
		WebElement element = elements.get(0);
		
		// Added wait for Image visibility
    	WebDriverWait wait = new WebDriverWait(driver, GlobalVariables.WAIT_FOR_VISIBILITY);
    	wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	protected boolean isClickable(WebDriver driver, String locator) {
		List<WebElement> webElements = elementFind(driver, locator, true, false);
		if (webElements.size() == 0) {
			return false;
		}
		WebElement element = webElements.get(0);
		return element.isDisplayed() && element.isEnabled();
	}

	protected boolean isSelected(WebDriver driver, String locator) {
		List<WebElement> webElements = elementFind(driver, locator, true, false);
		if (webElements.size() == 0) {
			return false;
		}
		WebElement element = webElements.get(0);
		return element.isSelected();
	}

	protected String[] parseAttributeLocator(String attributeLocator) {
		int index = attributeLocator.lastIndexOf('@');
		if (index <= 0) {
			throw new Selenium2LibraryNonFatalException(
					String.format("Attribute locator '%s' does not contain an element locator.", attributeLocator));
		}
		if (index + 1 == attributeLocator.length()) {
			throw new Selenium2LibraryNonFatalException(
					String.format("Attribute locator '%s' does not contain an attribute name.", attributeLocator));
		}
		String[] parts = new String[2];
		parts[0] = attributeLocator.substring(0, index);
		parts[1] = attributeLocator.substring(index + 1);

		return parts;
	}

	protected boolean isElementPresent(WebDriver driver, String locator) {
		return isElementPresent(driver, locator, null);
	}

	protected boolean isElementPresent(WebDriver driver, String locator, String tag) {
		return elementFind(driver, locator, true, false, tag).size() != 0;
	}

	protected boolean pageContains(WebDriver driver, String text) {
		WebDriver current = browserManagement.getCurrentWebDriver();
		current.switchTo().defaultContent();

		if (isTextPresent(driver, text)) {
			return true;
		}

		List<WebElement> elements = elementFind(driver, "xpath=//frame|//iframe", false, false);
		Iterator<WebElement> it = elements.iterator();
		while (it.hasNext()) {
			current.switchTo().frame(it.next());
			boolean found = isTextPresent(driver, text);
			current.switchTo().defaultContent();
			if (found) {
				return true;
			}
		}

		return false;
	}

	protected CharSequence mapAsciiKeyCodeToKey(int keyCode) {
		switch (keyCode) {
		case 0:
			return Keys.NULL;
		case 8:
			return Keys.BACK_SPACE;
		case 9:
			return Keys.TAB;
		case 10:
			return Keys.RETURN;
		case 13:
			return Keys.ENTER;
		case 24:
			return Keys.CANCEL;
		case 27:
			return Keys.ESCAPE;
		case 32:
			return Keys.SPACE;
		case 42:
			return Keys.MULTIPLY;
		case 43:
			return Keys.ADD;
		case 44:
			return Keys.SEPARATOR;
		case 45:
			return Keys.SUBTRACT;
		case 56:
			return Keys.DECIMAL;
		case 57:
			return Keys.DIVIDE;
		case 59:
			return Keys.SEMICOLON;
		case 61:
			return Keys.EQUALS;
		case 127:
			return Keys.DELETE;
		default:
			return new StringBuffer((char) keyCode);
		}
	}

	public static String escapeXpathValue(String value) {
		if (value.contains("\"") && value.contains("'")) {
			String[] partsWoApos = value.split("'");
			return String.format("concat('%s')", Python.join("', \"'\", '", Arrays.asList(partsWoApos)));
		}
		if (value.contains("'")) {
			return String.format("\"%s\"", value);
		}
		return String.format("'%s'", value);
	}

}
