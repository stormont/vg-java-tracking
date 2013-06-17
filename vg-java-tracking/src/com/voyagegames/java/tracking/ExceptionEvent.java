package com.voyagegames.java.tracking;



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
		super(key, Utilities.exceptionToString(exception));
	}
	
}
