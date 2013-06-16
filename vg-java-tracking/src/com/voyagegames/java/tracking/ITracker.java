package com.voyagegames.java.tracking;

public interface ITracker {

	public void dispatch();
	public void trackAppEvent(AppEvent data);
	public void trackAppSetting(AppSetting data);
	public void trackCustomEvent(CustomEvent data);
	public void trackCustomSetting(CustomSetting data);
	public void trackUserAction(UserAction data);
	public void trackUserView(UserView data);

}
