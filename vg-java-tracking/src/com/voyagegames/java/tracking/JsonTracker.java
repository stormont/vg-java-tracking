package com.voyagegames.java.tracking;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


/**
 * A basic tracker for uploading data in a JSON format
 */
public class JsonTracker implements ITracker {
	
	/**
	 * A configuration class for JsonTracker objects to consume
	 */
	public class JsonTrackerConfig {

		/**
		 * A set of callbacks for a JsonTrack object to use.
		 * JsonTracker will throw an exception is this is not set.
		 */
		public ITrackerCallback callback = null;
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
		 * The maximum allowed number of tracked items.
		 * 0 indicates no limit.
		 */
		public int maxTrackedItems = 0;
		/**
		 * The maximum allowed upload size
		 */
		public int maxUploadSize = 1024 * 1024;  // defaults to 1 MB
		/**
		 * The URL to dispatch tracking data to
		 */
		public String url;
		
	}

	/**
	 * The configuration for the JsonTracker object
	 */
	protected final JsonTrackerConfig config;
	/**
	 * The current set of tracking data
	 */
	protected final List<KeyValueToJSON> trackingData;
	
	/**
	 * Creates a JsonTracker object
	 * @param config The configuration data for the JsonTracker object to consume
	 */
	public JsonTracker(final JsonTrackerConfig config) {
		if (config.callback == null) {
			throw new IllegalArgumentException("config");
		}
		
		this.config = config;
		this.trackingData = new ArrayList<KeyValueToJSON>();
	}
	
	/**
	 * Generates the JSON output to dispatch
	 * @return The JSON output to dispatch
	 */
	protected String generateOutput() {
		final StringBuilder sb = new StringBuilder();
		
		for (final KeyValueToJSON kv : config.trackingHeader){
			sb.append(kv.toJSON());
		}
		
		for (final KeyValueToJSON kv : trackingData) {
			sb.append(kv.toJSON());
		}
		
		return sb.toString();
	}
	
	/**
	 * Clears the current set of tracked data
	 */
	protected void resetTrackingData() {
		trackingData.clear();
	}
	
	/**
	 * Track a generic piece of data
	 * @param data The data to track
	 */
	protected void trackItem(final KeyValueToJSON data) {
		if (config.maxTrackedItems > 0 && trackingData.size() >= config.maxTrackedItems) {
			return;
		}
		
		synchronized (this) {
			trackingData.add(data);
		}
	}

	@Override
	public boolean dispatch() {
		if (trackingData.size() == 0) {
			// Nothing to upload
			return true;
		}
		
		if (config.url == null || config.url.length() == 0) {
			config.callback.error("JsonTracker config url is null or empty");
			return false;
		}

		try {
			synchronized (this) {
				final String output = generateOutput();
				final int contentLength = output.length();
				final ByteBuffer buffer =
						config.useCompression ?
								ByteBuffer.wrap(output.getBytes("UTF-8"), 0, contentLength) :
								null;
				final byte[] result =
						config.useCompression ?
								Utilities.compress(buffer.array()) :
								output.getBytes("UTF-8");
				
				if (result.length > config.maxUploadSize) {
					config.callback.error("JsonTracker dispatch data exceeds config maxUploadSize");
					return false;
				}
				
				if (config.debugMode) {
					config.callback.debugTrack(output);
				} else {
					final URL url = new URL(config.url);
					final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
					connection.setDoOutput(true);
					connection.setRequestMethod("POST");
			
					final OutputStream writer = connection.getOutputStream();
					
					writer.write(result, 0, result.length);
					writer.close();
					
					final int responseCode = connection.getResponseCode();
			
					if (responseCode != HttpURLConnection.HTTP_OK) {
						config.callback.error("JsonTracker dispatch did not succeed with response code " + responseCode);
						return false;
					}
				}

				resetTrackingData();
				return true;
			}
		} catch (final Exception exception) {
			config.callback.error(Utilities.exceptionToString(exception));
			return false;
		}
	}

	@Override
	public void trackAppEvent(final AppEvent data) {
		trackItem(data);
	}

	@Override
	public void trackAppSetting(final AppSetting data) {
		trackItem(data);
	}

	@Override
	public void trackCustomEvent(final CustomEvent data) {
		trackItem(data);
	}

	@Override
	public void trackCustomSetting(final CustomSetting data) {
		trackItem(data);
	}

	@Override
	public void trackUserAction(final UserAction data) {
		trackItem(data);
	}

	@Override
	public void trackUserView(final UserView data) {
		trackItem(data);
	}

}
