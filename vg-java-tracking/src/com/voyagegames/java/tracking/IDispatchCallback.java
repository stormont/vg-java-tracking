package com.voyagegames.java.tracking;

/**
 * An interface for defining callbacks used by an ITracker
 */
public interface IDispatchCallback {
	
	/**
	 * Output tracked data to a debug callback
	 * @param trackingOutput The tracked data output
	 */
	public void debugTrack(String trackingOutput);
	/**
	 * Output an error message
	 * @param error The message to output
	 */
	public void error(String error);

}
