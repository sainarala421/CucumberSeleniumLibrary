package com.github.enhancetafrancis.cucumberseleniumlibrary.utils;

import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.LoggingLog4j;

/**
 * A raised exception of this type ends all test executions.
 */
@SuppressWarnings("serial")
public class Selenium2LibraryFatalException extends RuntimeException {
	protected LoggingLog4j logging = new LoggingLog4j();
	/**
	 * Mark this exception as fatal
	 */
	public static final boolean ROBOT_EXIT_ON_FAILURE = true;

	public Selenium2LibraryFatalException() {
		super();
		logging.fatal("Fatal Error");
	}

	public Selenium2LibraryFatalException(String string) {
		super(string);
		logging.fatal(String.format("%s", string));
	}

	public Selenium2LibraryFatalException(Throwable t) {
		super(t);
		logging.fatal(String.format("%s", t));
	}

	public Selenium2LibraryFatalException(String string, Throwable t) {
		super(string, t);
		logging.fatal(String.format("%s, Exception Error: %s", string, t));
	}
}