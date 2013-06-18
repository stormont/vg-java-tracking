package com.voyagegames.java.tracking;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A basic tracker for uploading data in a JSON format
 */
public class JsonDispatcher implements IDispatcher {
	
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

	/**
	 * The configuration for the dispatcher
	 */
	protected final JsonDispatcherConfig config;
	
	/**
	 * Creates a JsonDispatcher object
	 * @param config The configuration data for the JsonDispatcher object to consume
	 */
	public JsonDispatcher(final JsonDispatcherConfig config) {
		if (config.callback == null) {
			throw new IllegalArgumentException("config");
		}
		
		this.config = config;
	}
	
	/**
	 * Generates the JSON output to dispatch
	 * @return The JSON output to dispatch
	 */
	protected String generateOutput(final IAccumulator accumulator) {
		final StringBuilder sb = new StringBuilder();
		
		for (final KeyValueToJSON kv : config.trackingHeader){
			sb.append(kv.toJSON());
		}
		
		final Collection<? extends KeyValue> trackedData = accumulator.getTrackingData();
		
		for (final KeyValue kv : trackedData) {
			sb.append(((KeyValueToJSON)kv).toJSON());
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean dispatch() {
		// JsonDispatcher does not respond to this method
		return false;
	}

	@Override
	public boolean dispatch(final IAccumulator accumulator) {
		if (accumulator.getTrackingData().size() == 0) {
			// Nothing to upload
			return true;
		}
		
		if (config.url == null || config.url.length() == 0) {
			config.callback.error("JsonDispatcher config url is null or empty");
			return false;
		}

		try {
			final String output = generateOutput(accumulator);
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
				config.callback.error("JsonDispatcher dispatch data exceeds config maxUploadSize");
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
					config.callback.error("JsonDispatcher dispatch did not succeed with response code " + responseCode);
					return false;
				}
			}

			return true;
		} catch (final Exception exception) {
			config.callback.error(Utilities.exceptionToString(exception));
			return false;
		}
	}

}
