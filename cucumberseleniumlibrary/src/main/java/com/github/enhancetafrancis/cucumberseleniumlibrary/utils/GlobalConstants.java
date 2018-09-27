package com.github.enhancetafrancis.cucumberseleniumlibrary.utils;
import org.apache.logging.log4j.Level;

public class GlobalConstants{
    public static final int WAIT_FOR_VISIBILITY = 60;
	public static final Level OFF = Level.forName("OFF", 0);
	public static final Level FATAL = Level.forName("FATAL", 100);
	public static final Level ERROR = Level.forName("ERROR", 200);
	public static final Level WARN = Level.forName("WARN", 300);
	public static final Level INFO = Level.forName("INFO", 400);
	public static final Level DEBUG = Level.forName("DEBUG", 500);
	public static final Level TRACE = Level.forName("TRACE", 600);
}