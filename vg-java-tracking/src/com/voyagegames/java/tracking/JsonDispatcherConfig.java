package com.voyagegames.java.tracking;

import java.util.ArrayList;
import java.util.List;

/**
 * A configuration class for JsonDispatcher objects to consume
 */
public class JsonDispatcherConfig {

	/**
	 * A set of callbacks for a JsonDispatcher object to use.
	 * JsonDispatcher will throw an exception is this is not set.
	 */
	public IDispatchCallback callback = null;
	/**
	 * A set of tracking values to track in the header of every dispatch
	 */
	public final List<KeyValueToJSON> trackingHeader = new ArrayList<KeyValueToJSON>();
	/**
	 * Whether to use "debug" tracking mode.
	 * This does not upload and uses the callback debugTrack() method instead.
	 */
	public boolean debugMode = false;
	/**
	 * Whether to compress the dispatched data
	 */
	public boolean useCompression = true;
	/**
	 * The maximum allowed upload size
	 */
	public int maxUploadSize = 1024 * 1024;  // defaults to 1 MB
	/**
	 * The URL to dispatch tracking data to
	 */
	public String url;
	
}
