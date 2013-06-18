package com.voyagegames.java.tracking;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic class for accumulating tracked data 
 */
public class BasicAccumulator implements IAccumulator {
	
	/**
	 * A configuration class for BasicTracker objects to consume
	 */
	public class BasicTrackerConfig {

		/**
		 * The maximum allowed number of tracked items.
		 * 0 indicates no limit.
		 */
		public int maxTrackedItems = 0;
		
	}

	/**
	 * The configuration for the tracker
	 */
	protected final BasicTrackerConfig config;
	/**
	 * The current set of tracking data
	 */
	protected final List<KeyValue> trackingData;
	
	/**
	 * Creates a BasicTracker object
	 * @param config The configuration data for the tracker
	 */
	public BasicAccumulator(final BasicTrackerConfig config) {
		this.config = config;
		this.trackingData = new ArrayList<KeyValue>();
	}
	
	/**
	 * Track a generic piece of data
	 * @param data The data to track
	 */
	protected void trackItem(final KeyValueToJSON data) {
		if (config.maxTrackedItems > 0 && trackingData.size() >= config.maxTrackedItems) {
			return;
		}
		
		trackingData.add(data);
	}
	
	@Override
	public List<KeyValue> getTrackingData() {
		return trackingData;
	}
	
	@Override
	public void resetTrackingData() {
		trackingData.clear();
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
