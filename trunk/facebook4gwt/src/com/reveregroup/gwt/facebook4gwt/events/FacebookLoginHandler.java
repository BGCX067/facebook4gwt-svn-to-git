package com.reveregroup.gwt.facebook4gwt.events;

import com.google.gwt.event.shared.EventHandler;

public interface FacebookLoginHandler extends EventHandler {
	/**
	 * The status of the Facebook connection has changed. For example the user
	 * has logged in or logged out.
	 */
	public void loginStatusChanged(FacebookLoginEvent event);
}
