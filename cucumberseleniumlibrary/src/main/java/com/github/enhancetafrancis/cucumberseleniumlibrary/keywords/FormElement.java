package com.github.enhancetafrancis.cucumberseleniumlibrary.keywords;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.GlobalConstants;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.RunOnFailureKeywordsAdapter;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.Selenium2LibraryNonFatalException;

@RobotKeywords
public class FormElement extends RunOnFailureKeywordsAdapter {

	/**
	 * Instantiated Element keyword bean
	 */
	@Autowired
	protected Element element = new Element();

	/**
	 * Instantiated Logging keyword bean
	 */
	@Autowired
	private static final Logger logging = LogManager.getLogger(FormElement.class.getName());

	// ##############################
	// KeywordsBrowserManagement
	// ##############################

	@RobotKeywordOverload
	public void submitForm(WebDriver driver) {
		submitForm(driver, null);
	}

	/**
	 * Submit the form identified by <b>locator</b>.<br>
	 * <br>
	 * If the locator is empty, the first form in the page will be submitted.<br>
	 * <br>
	 * Key attributes for forms are id and name. See `Introduction` for details
	 * about locators.<br>
	 * 
	 * @param locator
	 *            Default=NONE. The locator to locate the form.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator=NONE" })
	public void submitForm(WebDriver driver, String locator) {
		logging.info(String.format("Submitting form '%s'.", locator));
		if (locator == null) {
			locator = "xpath=//form";
		}
		List<WebElement> webElements = element.elementFind(driver, locator, true, true, "form");
		webElements.get(0).submit();
	}

	/**
	 * Verify the checkbox identified by <b>locator</b> is selected/checked.<br>
	 * <br>
	 * Key attributes for checkboxes are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the checkbox.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void checkboxShouldBeSelected(WebDriver driver, String locator) {
		logging.info(String.format("Verifying checkbox '%s' is selected.", locator));
		WebElement element = getCheckbox(driver, locator);
		if (!element.isSelected()) {
			throw new Selenium2LibraryNonFatalException(String.format("Checkbox '%s' should have been selected.",
					locator));
		}
	}

	/**
	 * Verify the checkbox identified by <b>locator</b> is not selected/checked.<br>
	 * <br>
	 * Key attributes for checkboxes are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the checkbox.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void checkboxShouldNotBeSelected(WebDriver driver, String locator) {
		logging.info(String.format("Verifying checkbox '%s' is selected.", locator));
		WebElement element = getCheckbox(driver, locator);
		if (element.isSelected()) {
			throw new Selenium2LibraryNonFatalException(String.format("Checkbox '%s' should not have been selected.",
					locator));
		}
	}

	@RobotKeywordOverload
	public void pageShouldContainCheckbox(WebDriver driver, String locator) {
		pageShouldContainCheckbox(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldContainCheckbox(WebDriver driver, String locator, String message) {
		pageShouldContainCheckbox(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the checkbox identified by <b>locator</b> is found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for checkboxes are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the checkbox.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainCheckbox(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldContainElement(driver, locator, "checkbox", message, logLevel);
	}

	@RobotKeywordOverload
	public void pageShouldNotContainCheckbox(WebDriver driver, String locator) {
		pageShouldNotContainCheckbox(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldNotContainCheckbox(WebDriver driver, String locator, String message) {
		pageShouldNotContainCheckbox(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the checkbox identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for checkboxes are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the checkbox.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainCheckbox(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldNotContainElement(driver, locator, "checkbox", message, logLevel);
	}

	/**
	 * Select the checkbox identified by <b>locator</b>.<br>
	 * <br>
	 * Does nothing, if the checkbox is already selected.<br>
	 * <br>
	 * Key attributes for checkboxes are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the checkbox.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void selectCheckbox(WebDriver driver, String locator) {
		logging.info(String.format("Selecting checkbox '%s'.", locator));
		WebElement element = getCheckbox(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Unselect the checkbox identified by <b>locator</b>.<br>
	 * <br>
	 * Does nothing, if the checkbox is not selected.<br>
	 * <br>
	 * Key attributes for checkboxes are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the checkbox.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void unselectCheckbox(WebDriver driver, String locator) {
		logging.info(String.format("Selecting checkbox '%s'.", locator));
		WebElement element = getCheckbox(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	@RobotKeywordOverload
	public void pageShouldContainRadioButton(WebDriver driver, String locator) {
		pageShouldContainRadioButton(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldContainRadioButton(WebDriver driver, String locator, String message) {
		pageShouldContainRadioButton(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the radio button identified by <b>locator</b> is found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for radio buttons are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the radio button.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainRadioButton(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldContainElement(driver, locator, "radio button", message, logLevel);
	}

	@RobotKeywordOverload
	public void pageShouldNotContainRadioButton(WebDriver driver, String locator) {
		pageShouldNotContainRadioButton(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldNotContainRadioButton(WebDriver driver, String locator, String message) {
		pageShouldNotContainRadioButton(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the radio button identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for radio buttons are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the radio button.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainRadioButton(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldNotContainElement(driver, locator, "radio button", message, logLevel);
	}

	/**
	 * Verify the radio button group identified by <b>groupName</b> has its
	 * selection set to <b>value</b>.<br>
	 * <br>
	 * See `Select Radio Button` for details about locating radio buttons.<br>
	 * 
	 * @param groupName
	 *            The radio button group name.
	 * @param value
	 *            The expected value.
	 */
	@RobotKeyword
	@ArgumentNames({ "groupName", "value" })
	public void radioButtonShouldBeSetTo(WebDriver driver, String groupName, String value) {
		logging.info(String.format("Verifying radio button '%s' has selection '%s'.", groupName, value));
		List<WebElement> elements = getRadioButtons(driver, groupName);
		String actualValue = getValueFromRadioButtons(elements);
		if (actualValue == null || !actualValue.equals(value)) {
			throw new Selenium2LibraryNonFatalException(String.format(
					"Selection of radio button '%s' should have been '%s' but was '%s'", groupName, value, actualValue));
		}
	}

	/**
	 * Verify the radio button group identified by <b>groupName</b> has no
	 * selection.<br>
	 * <br>
	 * See `Select Radio Button` for details about locating radio buttons.<br>
	 * 
	 * @param groupName
	 *            The radio button group name.
	 */
	@RobotKeyword
	@ArgumentNames({ "groupName" })
	public void radioButtonShouldNotBeSelected(WebDriver driver, String groupName) {
		logging.info(String.format("Verifying radio button '%s' has no selection.", groupName));
		List<WebElement> elements = getRadioButtons(driver, groupName);
		String actualValue = getValueFromRadioButtons(elements);
		if (actualValue != null) {
			throw new Selenium2LibraryNonFatalException(String.format(
					"Radio button group '%s' should not have had selection, but '%s' was selected", groupName,
					actualValue));
		}
	}

	/**
	 * Sets the selection of the radio button group identified by
	 * <b>groupName</b> to <b>value</b>.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Select Radio Button</td>
	 * <td>size</td>
	 * <td>XL</td>
	 * <td># Matches HTML like &lt;input type="radio" name="size"
	 * value="XL"&gt;XL&lt;/input&gt;</td>
	 * </tr>
	 * <tr>
	 * <td>Select Radio Button</td>
	 * <td>size</td>
	 * <td>sizeXL</td>
	 * <td># Matches HTML like &lt;input type="radio" name="size" value="XL"
	 * id="sizeXL"&gt;XL&lt;/input&gt;</td>
	 * </tr>
	 * </table>
	 * 
	 * @param groupName
	 *            The radio button group name.
	 * @param value
	 *            The value or id attribute of the radio button to set.
	 */
	@RobotKeyword
	@ArgumentNames({ "groupName", "value" })
	public void selectRadioButton(WebDriver driver, String groupName, String value) {
		logging.info(String.format("Selecting '%s' from radio button '%s'.", value, groupName));
		WebElement element = getRadioButtonWithValue(driver, groupName, value);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Types the given <b>filePath</b> into the input field identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * This keyword is most often used to input files into upload forms. The
	 * file specified with filePath must be available on the same host where the
	 * Selenium Server is running.<br>
	 * <br>
	 * Example:
	 * <table border="1" cellspacing="0" summary="">
	 * <tr>
	 * <td>Choose File</td>
	 * <td>my_upload_field</td>
	 * <td>/home/user/files/trades.csv</td>
	 * </tr>
	 * </table>
	 * Key attributes for input fields are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the input field.
	 * @param filePath
	 *            The file path to input
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "filePath" })
	public void chooseFile(WebDriver driver, String locator, String filePath) {
		if (!new File(filePath).isFile()) {
			logging.info(String.format("File '%s' does not exist on the local file system", filePath));
		}
		element.elementFind(driver, locator, true, true).get(0).sendKeys(filePath);
	}

	/**
	 * Types the given <b>text</b> into the password field identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * Key attributes for input fields are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the password field.
	 * @param text
	 *            The password to input
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text" })
	public void inputPassword(WebDriver driver, String locator, String text) {
		//logging.info(String.format("Typing password into text field '%s'", locator));
		inputTextIntoTextField(driver, locator, text);
	}

	/**
	 * Types the given <b>text</b> into the text field identified by
	 * <b>locator</b>.<br>
	 * <br>
	 * Key attributes for input fields are id and name. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param text
	 *            The password to input
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text" })
	public void inputText(WebDriver driver, String locator, String text) {
		//logging.info(String.format("Typing text '%s' into text field '%s'", text, locator));
		inputTextIntoTextField(driver, locator, text);
	}

	@RobotKeywordOverload
	public void pageShouldContainTextfield(WebDriver driver, String locator) {
		pageShouldContainTextfield(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldContainTextfield(WebDriver driver, String locator, String message) {
		pageShouldContainTextfield(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the text field identified by <b>locator</b> is found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for text field are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainTextfield(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldContainElement(driver, locator, "text field", message, logLevel);
	}

	@RobotKeywordOverload
	public void pageShouldNotContainTextfield(WebDriver driver, String locator) {
		pageShouldNotContainTextfield(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldNotContainTextfield(WebDriver driver, String locator, String message) {
		pageShouldNotContainTextfield(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the text field identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for text field are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainTextfield(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldNotContainElement(driver, locator, "text field", message, logLevel);
	}

	@RobotKeywordOverload
	public void textfieldValueShouldBe(WebDriver driver, String locator, String text) {
		textfieldValueShouldBe(driver, locator, text, "");
	}

	/**
	 * Verify the text field identified by <b>locator</b> is exactly
	 * <b>text</b>.<br>
	 * <br>
	 * Key attributes for text field are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textfieldShouldContain
	 * @see FormElement#textfieldShouldNotContain
	 * @see FormElement#textfieldValueShouldNotBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textfieldValueShouldBe(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text field");
		if (!actual.contains(text)) {
			if (message == null) {
				message = String.format("Value of text field '%s' should have been '%s' but was '%s'", locator, text,
						actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Content of text field '%s' is '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textfieldValueShouldNotBe(WebDriver driver, String locator, String text) {
		textfieldValueShouldNotBe(driver, locator, text, "");
	}

	/**
	 * Verify the text field identified by <b>locator</b> is not exactly
	 * <b>text</b>.<br>
	 * <br>
	 * Key attributes for text field are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textfieldShouldContain
	 * @see FormElement#textfieldShouldNotContain
	 * @see FormElement#textfieldValueShouldBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textfieldValueShouldNotBe(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text field");
		if (actual.contains(text)) {
			if (message == null) {
				message = String.format("Value of text field '%s' should not have been '%s' but was '%s'", locator,
						text, actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Content of text field '%s' is '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textfieldShouldContain(WebDriver driver, String locator, String text) {
		textfieldShouldContain(driver, locator, text, "");
	}

	/**
	 * Verify the text field identified by <b>locator</b> contains <b>text</b>.<br>
	 * <br>
	 * Key attributes for text field are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textfieldShouldNotContain
	 * @see FormElement#textfieldValueShouldBe
	 * @see FormElement#textfieldValueShouldNotBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textfieldShouldContain(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text field");
		if (!actual.contains(text)) {
			if (message == null) {
				message = String.format("Text field '%s' should have contained text '%s', but was '%s'", locator, text,
						actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Text field '%s' contains text '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textfieldShouldNotContain(WebDriver driver, String locator, String text) {
		textfieldShouldNotContain(driver, locator, text, "");
	}

	/**
	 * Verify the text field identified by <b>locator</b> does not contain
	 * <b>text</b>.<br>
	 * <br>
	 * Key attributes for text field are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text field.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textfieldShouldContain
	 * @see FormElement#textfieldValueShouldBe
	 * @see FormElement#textfieldValueShouldNotBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textfieldShouldNotContain(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text field");
		if (actual.contains(text)) {
			if (message == null) {
				message = String.format("Text field '%s' should not have contained text '%s', but was '%s'", locator,
						text, actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Text field '%s' contains text '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textareaShouldContain(WebDriver driver, String locator, String text) {
		textareaShouldContain(driver, locator, text, "");
	}

	/**
	 * Verify the text area identified by <b>locator</b> contains <b>text</b>.<br>
	 * <br>
	 * Key attributes for text areas are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text area.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textareaShouldNotContain
	 * @see FormElement#textareaValueShouldBe
	 * @see FormElement#textareaValueShouldNotBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textareaShouldContain(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text area");
		if (!actual.contains(text)) {
			if (message == null) {
				message = String.format("Text area '%s' should have contained text '%s', but was '%s'", locator, text,
						actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Text field '%s' contains text '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textareaShouldNotContain(WebDriver driver, String locator, String text) {
		textareaShouldNotContain(driver, locator, text, "");
	}

	/**
	 * Verify the text area identified by <b>locator</b> does not contain <b>text</b>.<br>
	 * <br>
	 * Key attributes for text areas are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text area.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textareaShouldContain
	 * @see FormElement#textareaValueShouldBe
	 * @see FormElement#textareaValueShouldNotBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textareaShouldNotContain(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text area");
		if (!actual.contains(text)) {
			if (message == null) {
				message = String.format("Text area '%s' should not have contained text '%s', but was '%s'", locator, text,
						actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Text field '%s' contains text '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textareaValueShouldBe(WebDriver driver, String locator, String text) {
		textareaValueShouldBe(driver, locator, text, "");
	}

	/**
	 * Verify the text area identified by <b>locator</b> is exactly
	 * <b>text</b>.<br>
	 * <br>
	 * Key attributes for text area are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text area.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textareaShouldContain
	 * @see FormElement#textareaShouldNotContain
	 * @see FormElement#textareaValueShouldNotBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textareaValueShouldBe(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text area");
		if (!actual.contains(text)) {
			if (message == null) {
				message = String.format("Value of text area '%s' should have been '%s' but was '%s'", locator, text,
						actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Content of text area '%s' is '%s'.", locator, text));
	}

	@RobotKeywordOverload
	public void textareaValueShouldNotBe(WebDriver driver, String locator, String text) {
		textareaValueShouldNotBe(driver, locator, text, "");
	}

	/**
	 * Verify the text area identified by <b>locator</b> is not exactly
	 * <b>text</b>.<br>
	 * <br>
	 * Key attributes for text area are id and name. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the text area.
	 * @param text
	 *            The text to verify.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * 
	 * @see FormElement#textareaShouldContain
	 * @see FormElement#textareaShouldNotContain
	 * @see FormElement#textareaValueShouldBe
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "text", "message=NONE" })
	public void textareaValueShouldNotBe(WebDriver driver, String locator, String text, String message) {
		String actual = element.getValue(driver, locator, "text area");
		if (actual.contains(text)) {
			if (message == null) {
				message = String.format("Value of text area '%s' should not have been '%s' but was '%s'", locator,
						text, actual);
			}
			throw new Selenium2LibraryNonFatalException(message);
		}
		logging.info(String.format("Content of text area '%s' is '%s'.", locator, text));
	}

	/**
	 * Click on the button identified by <b>locator</b>.<br>
	 * <br>
	 * Key attributes for buttons are id, name and value. See `Introduction` for
	 * details about locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the link.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator" })
	public void clickButton(WebDriver driver, String locator) {
		logging.info(String.format("Clicking button '%s'.", locator));
		List<WebElement> elements = element.elementFind(driver, locator, true, false, "input");
		if (elements.size() == 0) {
			elements = element.elementFind(driver, locator, true, true, "button");
		}
		elements.get(0).click();
	}

	@RobotKeywordOverload
	public void pageShouldContainButton(WebDriver driver, String locator) {
		pageShouldContainButton(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldContainButton(WebDriver driver, String locator, String message) {
		pageShouldContainButton(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the button identified by <b>locator</b> is found on the current
	 * page.<br>
	 * <br>
	 * Key attributes for buttons are id, name and value. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the button.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldContainButton(WebDriver driver, String locator, String message, Level logLevel) {
		try {
			element.pageShouldContainElement(driver, locator, "input", message, logLevel);
		} catch (Selenium2LibraryNonFatalException e) {
			element.pageShouldContainElement(driver, locator, "button", message, logLevel);
		}
	}

	@RobotKeywordOverload
	public void pageShouldNotContainButton(WebDriver driver, String locator) {
		pageShouldNotContainButton(driver, locator, "");
	}

	@RobotKeywordOverload
	public void pageShouldNotContainButton(WebDriver driver, String locator, String message) {
		pageShouldNotContainButton(driver, locator, message, GlobalConstants.INFO);
	}

	/**
	 * Verify the button identified by <b>locator</b> is not found on the
	 * current page.<br>
	 * <br>
	 * Key attributes for buttons are id, name and value. See `Introduction` for
	 * details about log levels and locators.<br>
	 * 
	 * @param locator
	 *            The locator to locate the button.
	 * @param message
	 *            Default=NONE. Optional custom error message.
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 */
	@RobotKeyword
	@ArgumentNames({ "locator", "message=NONE", "logLevel=INFO" })
	public void pageShouldNotContainButton(WebDriver driver, String locator, String message, Level logLevel) {
		element.pageShouldNotContainElement(driver, locator, "input", message, logLevel);
		element.pageShouldNotContainElement(driver, locator, "button", message, logLevel);
	}

	// ##############################
	// Internal Methods
	// ##############################

	protected WebElement getCheckbox(WebDriver driver, String locator) {
		return element.elementFind(driver, locator, true, true, "input").get(0);
	}

	protected List<WebElement> getRadioButtons(WebDriver driver, String groupName) {
		String xpath = String.format("xpath=//input[@type='radio' and @name='%s']", groupName);
		logging.debug("Radio group locator: " + xpath);
		return element.elementFind(driver, xpath, false, true);
	}

	protected WebElement getRadioButtonWithValue(WebDriver driver, String groupName, String value) {
		String xpath = String.format("xpath=//input[@type='radio' and @name='%s' and (@value='%s' or @id='%s')]",
				groupName, value, value);
		logging.debug("Radio group locator: " + xpath);
		return element.elementFind(driver, xpath, true, true).get(0);
	}

	protected String getValueFromRadioButtons(List<WebElement> elements) {
		for (WebElement element : elements) {
			if (element.isSelected()) {
				return element.getAttribute("value");
			}
		}
		return null;
	}

	protected void inputTextIntoTextField(WebDriver driver, String locator, String text) {
		WebElement webElement = element.elementFind(driver, locator, true, true).get(0);
		webElement.clear();
		webElement.sendKeys(text);
	}

	protected boolean isFormElement(WebElement element) {
		if (element == null) {
			return false;
		}
		String tag = element.getTagName().toLowerCase();
		return "input".equals(tag) || "select".equals(tag) || "textarea".equals(tag) || "button".equals(tag) || "option".equals(tag);
	}

}
