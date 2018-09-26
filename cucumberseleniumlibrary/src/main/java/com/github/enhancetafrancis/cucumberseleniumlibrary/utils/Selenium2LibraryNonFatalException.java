package com.github.enhancetafrancis.cucumberseleniumlibrary.utils;

import com.github.enhancetafrancis.cucumberseleniumlibrary.keywords.LoggingLog4j;

/**
 * A raised exception of this type marks the step as failed, but does not end
 * all test executions.
 */
@SuppressWarnings("serial")
public class Selenium2LibraryNonFatalException extends RuntimeException {
	protected LoggingLog4j logging = new LoggingLog4j();
	/**
	 * Mark this exception as non fatal
	 */
	public static final boolean ROBOT_EXIT_ON_FAILURE = false;

	public Selenium2LibraryNonFatalException() {
		super();
		logging.error("Error");
	}

	public Selenium2LibraryNonFatalException(String string) {
		super(string);
		logging.error(String.format("Non-Fatal Error: %s", string));
	}

	public Selenium2LibraryNonFatalException(Throwable t) {
		super(t);
		logging.error(String.format("Non-Fatal Error: %s", t));
	}

	public Selenium2LibraryNonFatalException(String string, Throwable t) {
		super(string, t);
		logging.error(String.format("Non-Fatal Error: %s, %s", string, t));
	}
}
