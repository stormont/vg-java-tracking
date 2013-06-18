package com.voyagegames.java.tracking;

import java.util.Collection;

/**
 * A class for managing an accumulator and a dispatcher for tracking
 * @param <TIAccumulator> A type that implements IAccumulator for the tracked data
 * @param <TIDispatcher>  A type that implements IDispatcher for the tracked data
 */
public class Tracker
		<TIAccumulator extends IAccumulator, TIDispatcher extends IDispatcher>
		implements IAccumulator, IDispatcher {

	/**
	 * The accumulator for tracked data
	 */
	protected final TIAccumulator accumulator;
	/**
	 * The dispatcher of tracked data
	 */
	protected final TIDispatcher dispatcher;
	
	/**
	 * Creates the Tracker object
	 * @param accumulator The accumulator of tracking data
	 * @param dispatcher  The dispatcher of tracking data
	 */
	public Tracker(final TIAccumulator accumulator, final TIDispatcher dispatcher) {
		this.accumulator = accumulator;
		this.dispatcher = dispatcher;
	}
	
	@Override
	public boolean dispatch() {
		synchronized (accumulator) {
			if (dispatcher.dispatch(accumulator)) {
				accumulator.resetTrackingData();
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean dispatch(final IAccumulator accumulator) {
		// Tracker does not respond to this method
		return false;
	}

	@Override
	public Collection<? extends KeyValue> getTrackingData() {
		return accumulator.getTrackingData();
	}

	@Override
	public void resetTrackingData() {
		synchronized (accumulator) {
			accumulator.resetTrackingData();
		}
	}

	@Override
	public void trackAppEvent(final AppEvent data) {
		synchronized (accumulator) {
			accumulator.trackAppEvent(data);
		}
	}

	@Override
	public void trackAppSetting(final AppSetting data) {
		synchronized (accumulator) {
			accumulator.trackAppSetting(data);
		}
	}

	@Override
	public void trackCustomEvent(final CustomEvent data) {
		synchronized (accumulator) {
			accumulator.trackCustomEvent(data);
		}
	}

	@Override
	public void trackCustomSetting(final CustomSetting data) {
		synchronized (accumulator) {
			accumulator.trackCustomSetting(data);
		}
	}

	@Override
	public void trackUserAction(final UserAction data) {
		synchronized (accumulator) {
			accumulator.trackUserAction(data);
		}
	}

	@Override
	public void trackUserView(final UserView data) {
		synchronized (accumulator) {
			accumulator.trackUserView(data);
		}
	}

}
