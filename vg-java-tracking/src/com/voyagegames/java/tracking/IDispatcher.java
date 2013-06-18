package com.voyagegames.java.tracking;

/**
 * An interface for dispatching trackable data
 */
public interface IDispatcher {

	/**
	 * Attempts to dispatch the tracked data so far
	 * @return Whether the dispatch was successful
	 */
	public boolean dispatch();
	/**
	 * Attempts to dispatch the tracked data so far
	 * @param accumulator The accumulator of the tracked data
	 * @return Whether the dispatch was successful
	 */
	public boolean dispatch(final IAccumulator accumulator);

}
