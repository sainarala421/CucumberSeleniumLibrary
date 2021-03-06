package com.github.enhancetafrancis.cucumberseleniumlibrary.keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.Python;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.RunOnFailureKeywordsAdapter;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.Selenium2LibraryNonFatalException;

@RobotKeywords
public class JavaScript extends RunOnFailureKeywordsAdapter {

	protected boolean acceptOnNextConfirmationDefault = true;
	protected boolean acceptOnNextConfirmation = true;

	/**
	 * Instantiated BrowserManagement keyword bean
	 */
	@Autowired
	protected BrowserManagement browserManagement;

	/**
	 * Instantiated Logging keyword bean
	 */
	@Autowired
	protected LoggingLog4j logging = new LoggingLog4j();

	// ##############################
	// KeywordsBrowserManagement
	// ##############################

	@RobotKeywordOverload
	public void alertShouldBePresent() {
		alertShouldBePresent("");
	}

	/**
	 * Verify an alert is present and dismiss it.<br>
	 * <br>
	 * If <b>text</b> is a non-empty string, then it is also verified that the
	 * message of the alert equals to text.<br>
	 * <br>
	 * Will fail if no alert is present. Note that following keywords will fail
	 * unless the alert is confirmed by this keyword or another like `Confirm
	 * Action`.<br>
	 * 
	 * @param text
	 *            Default=NONE. The alert message to verify.
	 */
	@RobotKeyword
	@ArgumentNames({ "text=NONE" })
	public void alertShouldBePresent(String text) {
		String alertText = confirmAction();
		if (text != null && !alertText.equals(text)) {
			throw new Selenium2LibraryNonFatalException(String.format("Alert text should have been '%s' but was '%s'",
					text, alertText));
		}
	}

	/**
	 * Cancel will be selected the next time a confirmation dialog appears.<br>
	 * <br>
	 * Note that every time a confirmation comes up, it must be confirmed by the
	 * keywords 'Alert Should Be Present' or `Confirm Action`. Otherwise all
	 * following operations will fail.<br>
	 */
	@RobotKeyword
	public void chooseCancelOnNextConfirmation() {
		acceptOnNextConfirmation = false;
	}

	/**
	 * Ok will be selected the next time a confirmation dialog appears.<br>
	 * <br>
	 * Note that every time a confirmation comes up, it must be confirmed by the
	 * keywords 'Alert Should Be Present' or `Confirm Action`. Otherwise all
	 * following operations will fail.<br>
	 */
	@RobotKeyword
	public void chooseOkOnNextConfirmation() {
		acceptOnNextConfirmation = true;
	}

	/**
	 * Cancel will as default be selected from now on every time a confirmation
	 * dialog appears.<br>
	 * <br>
	 * Note that every time a confirmation comes up, it must be confirmed by the
	 * keywords 'Alert Should Be Present' or `Confirm Action`. Otherwise all
	 * following operations will fail.<br>
	 */
	@RobotKeyword
	public void chooseCancelOnConfirmation() {
		acceptOnNextConfirmationDefault = false;
		acceptOnNextConfirmation = false;
	}

	/**
	 * Ok will as default be selected from now on every time a confirmation
	 * dialog appears.<br>
	 * <br>
	 * Note that every time a confirmation comes up, it must be confirmed by the
	 * keywords 'Alert Should Be Present' or `Confirm Action`. Otherwise all
	 * following operations will fail.<br>
	 */
	@RobotKeyword
	public void chooseOkOnConfirmation() {
		acceptOnNextConfirmationDefault = true;
		acceptOnNextConfirmation = true;
	}

	/**
	 * Dismisses currently shown confirmation dialog and returns its message.<br>
	 * <br>
	 * By default, this keyword chooses 'OK' option from the dialog. If 'Cancel'
	 * needs to be chosen, keyword `Choose Cancel On Next Confirmation` must be
	 * called before the action that causes the confirmation dialog to be shown.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Click Button</td>
	 * <td>Send</td>
	 * <td></td>
	 * <td># Shows a confirmation dialog</td>
	 * </tr>
	 * <tr>
	 * <td>${message}=</td>
	 * <td>Confirm Action</td>
	 * <td></td>
	 * <td># Chooses Ok</td>
	 * </tr>
	 * <tr>
	 * <td>Should Be Equal</td>
	 * <td>${message}</td>
	 * <td>Are your sure?</td>
	 * <td># Check dialog message</td>
	 * </tr>
	 * <tr>
	 * <td>Choose Cancel On Next Confirmation</td>
	 * <td></td>
	 * <td></td>
	 * <td># Choose cancel on next `Confirm Action`</td>
	 * </tr>
	 * <tr>
	 * <td>Click Button</td>
	 * <td>Send</td>
	 * <td></td>
	 * <td># Shows a confirmation dialog</td>
	 * </tr>
	 * <tr>
	 * <td>Confirm Action</td>
	 * <td></td>
	 * <td></td>
	 * <td># Chooses Cancel</td>
	 * </tr>
	 * </table>
	 * 
	 * @return The dialog message.
	 */
	@RobotKeyword
	public String confirmAction() {
		try {
			Alert alert = browserManagement.getCurrentWebDriver().switchTo().alert();
			String text = alert.getText().replace("\n", "");
			if (acceptOnNextConfirmation) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			acceptOnNextConfirmation = acceptOnNextConfirmationDefault;
			return text;
		} catch (WebDriverException wde) {
			throw new Selenium2LibraryNonFatalException("There were no alerts");
		}
	}

	/**
	 * Execute the given JavaScript <b>code</b>.<br>
	 * <br>
	 * The given code may contain multiple lines of code, but must contain a
	 * return statement (with the value to be returned) at the end.<br>
	 * <br>
	 * The given code may be divided into multiple cells in the test data. In
	 * that case, the parts are concatenated together without adding spaces. If
	 * the given code is an absolute path to an existing file, the JavaScript to
	 * execute will be read from that file. Forward slashes work as a path
	 * separator on all operating systems.<br>
	 * <br>
	 * Note that by default the code will be executed in the context of the
	 * Selenium object itself, so <b>this</b> will refer to the Selenium object.
	 * Use <b>window</b> to refer to the window of your application, e.g.
	 * <i>window.document.getElementById('foo')</i>.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Execute JavaScript</td>
	 * <td>return window.my_js_function('arg1', 'arg2');</td>
	 * <td># Directly execute the JavaScript</td>
	 * </tr>
	 * <tr>
	 * <td>Execute JavaScript</td>
	 * <td>${CURDIR}/js_to_execute.js</td>
	 * <td># Load the JavaScript to execute from file</td>
	 * </tr>
	 * </table>
	 * 
	 * @param code
	 *            The JavaScript code or a file name.
	 * @return The return value of the executed code.
	 */
	@RobotKeyword
	@ArgumentNames({ "*code" })
	public Object executeJavascript(String... code) {
		String js = getJavascriptToExecute(Python.join("", Arrays.asList(code)));
		String.format("Executing JavaScript:\n%s", js);
		return ((JavascriptExecutor) browserManagement.getCurrentWebDriver()).executeScript(js);
	}

	/**
	 * Execute the given JavaScript <b>code</b> asynchronously.<br>
	 * <br>
	 * The given code may contain multiple lines of code, but must contain a
	 * return statement (with the value to be returned) at the end.<br>
	 * <br>
	 * The given code may be divided into multiple cells in the test data. In
	 * that case, the parts are concatenated together without adding spaces. If
	 * the given code is an absolute path to an existing file, the JavaScript to
	 * execute will be read from that file. Forward slashes work as a path
	 * separator on all operating systems.<br>
	 * <br>
	 * Note that by default the code will be executed in the context of the
	 * Selenium object itself, so <b>this</b> will refer to the Selenium object.
	 * Use <b>window</b> to refer to the window of your application, e.g.
	 * <i>window.document.getElementById('foo')</i>.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Execute Async JavaScript</td>
	 * <td>return window.my_js_function('arg1', 'arg2');</td>
	 * <td># Directly execute the JavaScript</td>
	 * </tr>
	 * <tr>
	 * <td>Execute Async JavaScript</td>
	 * <td>${CURDIR}/js_to_execute.js</td>
	 * <td># Load the JavaScript to execute from file</td>
	 * </tr>
	 * </table>
	 * 
	 * @param code
	 *            The JavaScript code or a file name.
	 * @return The return value of the executed code.
	 */
	@RobotKeyword
	@ArgumentNames({ "*code" })
	public Object executeAsyncJavascript(String... code) {
		String js = getJavascriptToExecute(Python.join("", Arrays.asList(code)));
		String.format("Executing JavaScript:\n%s", js);
		return ((JavascriptExecutor) browserManagement.getCurrentWebDriver()).executeAsyncScript(js);
	}

	/**
	 * Returns the text of current JavaScript alert.<br>
	 * <br>
	 * This keyword will fail if no alert is present. Note that following
	 * keywords will fail unless the alert is confirmed by the keywords 'Alert
	 * Should Be Present' or `Confirm Action`.
	 * 
	 * @return The alert message.
	 */
	@RobotKeyword
	public String getAlertMessage() {
		try {
			Alert alert = browserManagement.getCurrentWebDriver().switchTo().alert();
			String text = alert.getText().replace("\n", "");
			return text;
		} catch (WebDriverException wde) {
			throw new Selenium2LibraryNonFatalException("There were no alerts");
		}
	}

	// ##############################
	// Internal Methods
	// ##############################

	protected static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	protected String getJavascriptToExecute(String code) {
		String codepath = code.replace('/', File.separatorChar);
		if (!new File(codepath).isFile()) {
			return code;
		}
		logging.info(String.format("Reading JavaScript from file <a href=\"file://%s\">%s</a>.",
				codepath.replace(File.separatorChar, '/'), codepath));
		try {
			return readFile(codepath);
		} catch (IOException e) {
			throw new Selenium2LibraryNonFatalException("Cannot read JavaScript file: " + codepath);
		}
	}

}
