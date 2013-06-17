package com.voyagegames.java.tracking;

/**
 * A simple interface for converting class data to JSON
 */
public interface IToJSON {

	/**
	 * Converts the object data to well-formatted JSON
	 * @return The JSON representation of the object
	 */
	public String toJSON();

}
