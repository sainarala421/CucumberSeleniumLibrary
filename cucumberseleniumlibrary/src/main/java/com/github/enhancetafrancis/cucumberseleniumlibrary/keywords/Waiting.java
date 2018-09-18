package com.github.enhancetafrancis.cucumberseleniumlibrary.keywords;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.Robotframework;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.RunOnFailureKeywordsAdapter;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.Selenium2LibraryNonFatalException;

@RobotKeywords
public class Waiting extends RunOnFailureKeywordsAdapter {

	/**
	 * Instantiated BrowserManagement keyword bean
	 */
	@Autowired
	protected BrowserManagement browserManagement;

	/**
	 * Instantiated Element keyword bean
	 */
	@Autowired
	protected Element element;

	// ##############################
	// KeywordsBrowserManagement
	// ##############################

	@RobotKeywordOverload
	public void waitForCondition(WebDriver driver, String condition) {
		waitForCondition(driver, condition, null);
	}

	@RobotKeywordOverload
	public void waitForCondition(WebDriver driver, String condition, String timeout) {
		waitForCondition(driver, condition, timeout, null);
	}

	/**
	 * Waits until the given JavaScript <b>condition</b> is true.<br>
	 * <br>
	 * Fails, if the timeout expires, before the condition gets true. <br>
	 * <br>
	 * The condition may contain multiple JavaScript statements, but the last
	 * statement must return a boolean. Otherwise this keyword will always hit
	 * the timeout.<br>
	 * <br>
	 * Note that by default the code will be executed in the context of the
	 * Selenium object itself, so <b>this</b> will refer to the Selenium object.
	 * Use <b>window</b> to refer to the window of your application, e.g.
	 * <i>window.document.getElementById('foo')</i>.<br>
	 * <br>
	 * See `Introduction` for details about timeouts.<br>
	 * 
	 * @param condition
	 *            The JavaScript condition returning a boolean.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "condition", "timeout=NONE", "message=NONE" })
	public void waitForCondition(final WebDriver driver, final String condition, String timeout, String message) {
		if (message == null) {
			message = String.format("Condition '%s' did not become true in <TIMEOUT>", condition);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return Boolean.TRUE.equals(((JavascriptExecutor) driver)
						.executeScript(condition));
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilPageContains(WebDriver driver, String condition) {
		waitUntilPageContains(driver, condition, null);
	}

	@RobotKeywordOverload
	public void waitUntilPageContains(WebDriver driver, String condition, String timeout) {
		waitUntilPageContains(driver, condition, timeout, null);
	}

	/**
	 * Waits until the current page contains <b>text</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the text appears. <br>
	 * <br>
	 * See `Introduction` for details about timeouts.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "condition", "timeout=NONE", "message=NONE" })
	public void waitUntilPageContains(final WebDriver driver, final String text, String timeout, String message) {
		if (message == null) {
			message = String.format("Text '%s' did not appear in <TIMEOUT>", text);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return element.isTextPresent(driver, text);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilPageNotContains(final WebDriver driver, String condition, String timeout) {
		waitUntilPageNotContains(driver, condition, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilPageNotContains(final WebDriver driver, String condition) {
		waitUntilPageNotContains(driver, condition, null);
	}

	/**
	 * Waits until the current page does not contain <b>text</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the text disappears. <br>
	 * <br>
	 * See `Introduction` for details about timeouts.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "text", "timeout=NONE", "message=NONE" })
	public void waitUntilPageNotContains(final WebDriver driver, final String text, String timeout, String message) {
		if (message == null) {
			message = String.format("Text '%s' did not disappear in <TIMEOUT>", text);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return !element.isTextPresent(driver, text);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilPageDoesNotContain(final WebDriver driver, String condition, String timeout) {
		waitUntilPageDoesNotContain(driver, condition, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilPageDoesNotContain(final WebDriver driver, String condition) {
		waitUntilPageDoesNotContain(driver, condition, null);
	}

	/**
	 * Waits until the current page does not contain <b>text</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the text disappears. <br>
	 * <br>
	 * See `Introduction` for details about timeouts.<br>
	 * 
	 * @param text
	 *            The text to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "text", "timeout=NONE", "message=NONE" })
	public void waitUntilPageDoesNotContain(final WebDriver driver, final String text, String timeout, String message) {
		waitUntilPageNotContains(driver, text, timeout, message);
	}

	@RobotKeywordOverload
	public void waitUntilPageContainsElement(final WebDriver driver, String condition) {
		waitUntilPageContainsElement(driver, condition, null);
	}

	@RobotKeywordOverload
	public void waitUntilPageContainsElement(final WebDriver driver, String condition, String timeout) {
		waitUntilPageContainsElement(driver, condition, timeout, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is found on the
	 * current page.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element appears. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilPageContainsElement(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' did not appear in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return element.isElementPresent(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilPageNotContainsElement(final WebDriver driver, String locator) {
		waitUntilPageNotContainsElement(driver, locator, null);
	}

	@RobotKeywordOverload
	public void waitUntilPageNotContainsElement(final WebDriver driver, String locator, String timeout) {
		waitUntilPageNotContainsElement(driver, locator, timeout, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element disappears. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({"locator", "timeout=NONE", "message=NONE" })
	public void waitUntilPageNotContainsElement(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' did not disappear in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return !element.isElementPresent(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilPageDoesNotContainElement(final WebDriver driver, String locator) {
		waitUntilPageDoesNotContainElement(driver, locator, null);
	}

	@RobotKeywordOverload
	public void waitUntilPageDoesNotContainElement(final WebDriver driver, String locator, String timeout) {
		waitUntilPageDoesNotContainElement(driver, locator, timeout, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element disappears. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilPageDoesNotContainElement(final WebDriver driver, final String locator, String timeout, String message) {
	    waitUntilPageNotContainsElement(driver, locator, timeout, message);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsVisible(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsVisible(driver, locator, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsVisible(final WebDriver driver, String locator) {
		waitUntilElementIsVisible(driver, locator, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is visible.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets visible. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsVisible(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' not visible in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return element.isVisible(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilElementIsNotVisible(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsNotVisible(driver, locator, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsNotVisible(final WebDriver driver, String locator) {
		waitUntilElementIsNotVisible(driver, locator, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is not visible.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets invisible. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsNotVisible(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' still visible in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return !element.isVisible(driver,locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilElementIsClickable(final WebDriver driver, String locator) {
		waitUntilElementIsClickable(driver, locator, null, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsClickable(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsClickable(driver, locator, timeout, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is clbrowserinstance.getCurrentWebDriver()ickable.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets clickable. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsClickable(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' not clickable in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return element.isClickable(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilElementIsNotClickable(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsNotClickable(driver, locator, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsNotClickable(final WebDriver driver, String locator) {
		waitUntilElementIsNotClickable(driver, locator, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is not clickable.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets unclickable. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsNotClickable(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' still clickable in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return !element.isClickable(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilElementIsSuccessfullyClicked(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsSuccessfullyClicked(driver, locator, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsSuccessfullyClicked(final WebDriver driver, String locator) {
		waitUntilElementIsSuccessfullyClicked(driver, locator, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is sucessfully
	 * clicked on.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets clicked on. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsSuccessfullyClicked(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' not successfully clicked in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				element.clickElement(driver, locator);
				return true;
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilElementIsSelected(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsSelected(driver, locator, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsSelected(final WebDriver driver, String locator) {
		waitUntilElementIsSelected(driver, locator, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is selected.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets selected. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsSelected(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' not selected in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return element.isSelected(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilElementIsNotSelected(final WebDriver driver, String locator, String timeout) {
		waitUntilElementIsNotSelected(driver, locator, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilElementIsNotSelected(final WebDriver driver, String locator) {
		waitUntilElementIsNotSelected(driver, locator, null);
	}

	/**
	 * Waits until the element identified by <b>locator</b> is not selected.<br>
	 * <br>
	 * Fails, if the timeout expires, before the element gets unselected. <br>
	 * <br>
	 * See `Introduction` for details about locators and timeouts.<br>
	 * 
	 * @param locator
	 *            The locator to locate the element.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "timeout=NONE", "message=NONE" })
	public void waitUntilElementIsNotSelected(final WebDriver driver, final String locator, String timeout, String message) {
		if (message == null) {
			message = String.format("Element '%s' still selected in <TIMEOUT>", locator);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				return !element.isSelected(driver, locator);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilTitleContains(String title, String timeout) {
		waitUntilTitleContains(title, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilTitleContains(String title) {
		waitUntilTitleContains(title, null, null);
	}

	/**
	 * Waits until the current page title contains <b>title</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the page title contains the given
	 * title.<br>
	 * 
	 * @param title
	 *            The title to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "title", "timeout=NONE", "message=NONE" })
	public void waitUntilTitleContains(final String title, String timeout, String message) {
		if (message == null) {
			message = String.format("Title '%s' did not appear in <TIMEOUT>", title);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				String currentTitle = browserManagement.getTitle();
				return currentTitle != null && currentTitle.contains(title);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilTitleNotContains(String title, String timeout) {
		waitUntilTitleNotContains(title, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilTitleNotContains(String title) {
		waitUntilTitleNotContains(title, null, null);
	}

	/**
	 * Waits until the current page title does not contain <b>title</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the page title does not contain the
	 * given title.<br>
	 * 
	 * @param title
	 *            The title to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "title", "timeout=NONE", "message=NONE" })
	public void waitUntilTitleNotContains(final String title, String timeout, String message) {
		if (message == null) {
			message = String.format("Title '%s' did not appear in <TIMEOUT>", title);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				String currentTitle = browserManagement.getTitle();
				return currentTitle == null || !currentTitle.contains(title);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilTitleIs(String title, String timeout) {
		waitUntilTitleIs(title, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilTitleIs(String title) {
		waitUntilTitleIs(title, null);
	}

	/**
	 * Waits until the current page title is exactly <b>title</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the page title matches the given
	 * title.<br>
	 * 
	 * @param title
	 *            The title to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "title", "timeout=NONE", "message=NONE" })
	public void waitUntilTitleIs(final String title, String timeout, String message) {
		if (message == null) {
			message = String.format("Title '%s' did not appear in <TIMEOUT>", title);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				String currentTitle = browserManagement.getTitle();
				return currentTitle != null && currentTitle.equals(title);
			}
		});
	}

	@RobotKeywordOverload
	public void waitUntilTitleIsNot(String title, String timeout) {
		waitUntilTitleIsNot(title, timeout, null);
	}

	@RobotKeywordOverload
	public void waitUntilTitleIsNot(String title) {
		waitUntilTitleIsNot(title, null, null);
	}

	/**
	 * Waits until the current page title is not exactly <b>title</b>.<br>
	 * <br>
	 * Fails, if the timeout expires, before the page title does not match the
	 * given title.<br>
	 * 
	 * @param title
	 *            The title to verify.
	 * @param timeout
	 *            Default=NONE. Optional timeout interval.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 */
	@RobotKeyword
	@ArgumentNames({ "title", "timeout=NONE", "message=NONE" })
	public void waitUntilTitleIsNot(final String title, String timeout, String message) {
		if (message == null) {
			message = String.format("Title '%s' did not appear in <TIMEOUT>", title);
		}
		waitUntil(timeout, message, new WaitUntilFunction() {

			@Override
			public boolean isFinished() {
				String currentTitle = browserManagement.getTitle();
				return currentTitle == null || !currentTitle.equals(title);
			}
		});
	}

	// ##############################
	// Internal Methods
	// ##############################

	protected void waitUntil(String timestr, String message, WaitUntilFunction function) {
		double timeout = timestr != null ? Robotframework.timestrToSecs(timestr) : browserManagement.getTimeout();
		message = message.replace("<TIMEOUT>", Robotframework.secsToTimestr(timeout));
		long maxtime = System.currentTimeMillis() + (long) (timeout * 1000);
		for (;;) {
			try {
				if (function.isFinished()) {
					break;
				}
			} catch (Throwable t) {
			}
			if (System.currentTimeMillis() > maxtime) {
				throw new Selenium2LibraryNonFatalException(message);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}

	protected static interface WaitUntilFunction {

		boolean isFinished();
	}

}
