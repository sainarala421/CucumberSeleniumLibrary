package com.github.enhancetafrancis.cucumberseleniumlibrary.keywords;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.GlobalConstants;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.RunOnFailureKeywordsAdapter;
import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.Selenium2LibraryNonFatalException;

@RobotKeywords
public class LoggingLog4j extends RunOnFailureKeywordsAdapter {
	@Autowired
	protected BrowserManagement browserManagement;

	// ##############################
	// KeywordsBrowserManagement
	// ##############################
	//protected Logger logging = LogManager.getLogger();
	Logger logmanager = LogManager.getLogger(LoggingLog4j.class.getName());
	
	@RobotKeywordOverload
	public List<String> logWindowIdentifiers() {
		return logWindowIdentifiers(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the id attributes of all windows known to the current
	 * browser instance.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return List of window id attributes.
	 * 
	 * @see BrowserManagement#getWindowIdentifiers
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public List<String> logWindowIdentifiers(Level logLevel) {
		List<String> windowIdentifiers = browserManagement.getWindowIdentifiers();
		for (String windowIdentifier : windowIdentifiers) {
			log(windowIdentifier, logLevel);
		}
		return windowIdentifiers;
	}

	@RobotKeywordOverload
	public List<String> logWindowNames() {
		return logWindowNames(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the names of all windows known to the current browser
	 * instance.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return List of windows names.
	 * 
	 * @see BrowserManagement#getWindowNames
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public List<String> logWindowNames(Level logLevel) {
		List<String> windowIdentifiers = browserManagement.getWindowNames();
		for (String windowIdentifier : windowIdentifiers) {
			log(windowIdentifier, logLevel);
		}
		return windowIdentifiers;
	}

	@RobotKeywordOverload
	public List<String> logWindowTitles() {
		return logWindowTitles(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the titles of all windows known to the current browser
	 * instance.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return List of window titles.
	 * 
	 * @see BrowserManagement#getWindowTitles
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public List<String> logWindowTitles(Level logLevel) {
		List<String> windowIdentifiers = browserManagement.getWindowTitles();
		for (String windowIdentifier : windowIdentifiers) {
			log(windowIdentifier, logLevel);
		}
		return windowIdentifiers;
	}

	@RobotKeywordOverload
	public String logLocation() {
		return logLocation(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the location of the current browser instance.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return The current location.
	 * 
	 * @see BrowserManagement#getLocation
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public String logLocation(Level logLevel) {
		String actual = browserManagement.getLocation();
		log(actual, logLevel);
		return actual;
	}

	@RobotKeywordOverload
	public String logSource() {
		return logSource(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the entire html source of the current page or frame.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return The entire html source.
	 * 
	 * @see BrowserManagement#getSource
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public String logSource(Level logLevel) {
		String actual = browserManagement.getSource();
		log(actual, logLevel);
		return actual;
	}

	@RobotKeywordOverload
	public String logTitle() {
		return logTitle(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the title of current page.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return The page title.
	 * 
	 * @see BrowserManagement#getSource
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public String logTitle(Level logLevel) {
		String actual = browserManagement.getTitle();
		log(actual, logLevel);
		return actual;
	}

	@RobotKeywordOverload
	public String logSystemInfo() {
		return logSystemInfo(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns basic system information about the execution
	 * environment.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return System information.
	 * 
	 * @see BrowserManagement#getSystemInfo
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public String logSystemInfo(Level logLevel) {
		String actual = browserManagement.getSystemInfo();
		log(actual, logLevel);
		return actual;
	}

	@RobotKeywordOverload
	public String logRemoteCapabilities() {
		return logRemoteCapabilities(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the actually supported capabilities of the remote
	 * browser instance.<br>
	 * <br>
	 * Not all server implementations will support every WebDriver feature.
	 * Therefore, the client and server should use JSON objects with the
	 * properties listed below when describing which features a user requests
	 * that a session support. <b>If a session cannot support a capability that
	 * is requested in the desired capabilities, no error is thrown;</b> a
	 * read-only capabilities object is returned that indicates the capabilities
	 * the session actually supports. For more information see: <a href=
	 * "http://code.google.com/p/selenium/wiki/DesiredCapabilities"
	 * >DesiredCapabilities</a><br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return The capabilities of the remote node.
	 * 
	 * @see BrowserManagement#getRemoteCapabilities
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public String logRemoteCapabilities(Level logLevel) {
		String actual = browserManagement.getRemoteCapabilities();
		log(actual, logLevel);
		return actual;
	}

	@RobotKeywordOverload
	public String logRemoteSessionId() {
		return logRemoteSessionId(GlobalConstants.INFO);
	}

	/**
	 * Logs and returns the session id of the remote browser instance.<br>
	 * <br>
	 * See `Introduction` for details about the <b>logLevel</b>.<br>
	 * 
	 * @param logLevel
	 *            Default=INFO. Optional log level.
	 * @return The remote session id.
	 * 
	 * @see BrowserManagement#getRemoteSessionId
	 */
	@RobotKeyword
	@ArgumentNames({ "logLevel=INFO" })
	public String logRemoteSessionId(Level logLevel) {
		String actual = browserManagement.getRemoteSessionId();
		log(actual, logLevel);
		return actual;
	}

	/**
	 * Set the <b>logDirectory</b>, where captured screenshots are stored, to
	 * some custom path.<br>
	 * <br>
	 * Fails, if either the given path does not exist, is no directory or is not
	 * writable.<br>
	 * 
	 * @param logDirectory
	 *            The directory to log to.
	 * @throws Exception
	 *             - if anything goes wrong.
	 */
	@RobotKeyword
	@ArgumentNames({ "logDirectory" })
	public void setLogDirectory(String logDirectory) throws Exception {
		File file = new File(logDirectory);

		if (file.exists() && file.isDirectory() && file.canWrite()) {
			Logging.setLogDir(file.getAbsolutePath());
		} else {
			throw new Exception("Location given as parameter: " + logDirectory
					+ " must exist and must be a writeable directory!");
		}
	}

	// ##############################
	// Internal Methods
	// ##############################

	protected void trace(String msg) {
		log(msg, GlobalConstants.TRACE);
	}

	protected void debug(String msg) {
		log(msg, GlobalConstants.DEBUG);
	}

	protected void info(String msg) {
		log(msg, GlobalConstants.INFO);
	}

	public void warn(String msg) {
		log(msg, GlobalConstants.WARN);
	}

	protected void log(String msg, Level logLevel) {
		if (logLevel != null) {
			//LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
			logmanager.log(logLevel, msg);
		} else {
			throw new Selenium2LibraryNonFatalException(String.format("Given log level %s is invalid.", logLevel));
		}
	}
}