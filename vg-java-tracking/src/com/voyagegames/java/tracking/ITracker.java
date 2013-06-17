package com.voyagegames.java.tracking;

/**
 * An interface for managing and dispatching trackable data
 */
public interface ITracker {

	/**
	 * Attempts to dispatch the tracked data so far
	 * @return Whether the dispatch was successful
	 */
	public boolean dispatch();
	/**
	 * Track a lifecycle app event
	 * @param data The data to track
	 */
	public void trackAppEvent(AppEvent data);
	/**
	 * Track a global and/or permanent app setting
	 * @param data The data to track
	 */
	public void trackAppSetting(AppSetting data);
	/**
	 * Track a custom-defined event
	 * @param data The data to track
	 */
	public void trackCustomEvent(CustomEvent data);
	/**
	 * Track a custom-defined setting
	 * @param data The data to track
	 */
	public void trackCustomSetting(CustomSetting data);
	/**
	 * Track a user-driven action
	 * @param data The data to track
	 */
	public void trackUserAction(UserAction data);
	/**
	 * Track a user's view of some element
	 * @param data The data to track
	 */
	public void trackUserView(UserView data);

}
