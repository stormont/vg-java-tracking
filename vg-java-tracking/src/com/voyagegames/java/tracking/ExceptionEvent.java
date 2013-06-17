package com.voyagegames.java.tracking;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * A class for tracking program exceptions
 */
public class ExceptionEvent extends AppEvent {

	/**
	* Creates the ExceptionEvent object
	* @param key       A uniquely identifiable key for tracked data
	* @param exception The exception to track
	*/
	public ExceptionEvent(final String key, final Exception exception) {
		super(key, exceptionToString(exception));
	}
	
	/**
	 * Converts an exception stack track to a String
	 * @param exception The exception to convert to a readable String
	 * @return The readable String representation of the exception
	 */
	public static String exceptionToString(final Exception exception) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		
		exception.printStackTrace(pw);
		return sw.toString();
	}

}
