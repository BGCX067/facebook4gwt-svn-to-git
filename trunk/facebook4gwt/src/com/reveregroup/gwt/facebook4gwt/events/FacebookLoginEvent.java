package com.reveregroup.gwt.facebook4gwt.events;


import com.google.gwt.event.shared.GwtEvent;
import com.reveregroup.gwt.facebook4gwt.Facebook.ConnectState;

public class FacebookLoginEvent extends GwtEvent<FacebookLoginHandler> {
	private static final Type<FacebookLoginHandler> TYPE = new Type<FacebookLoginHandler>();

	public FacebookLoginEvent(ConnectState status) {
		this.status = status;
		this.loggedIn = status == ConnectState.CONNECTED;
	}
	
	private boolean loggedIn;
	private ConnectState status;

	/**
	 * Is a user now logged in?
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * The new status of the Facebook connection.
	 */
	public ConnectState getConnectionStatus() {
		return status;
	}

	@Override
	protected void dispatch(FacebookLoginHandler handler) {
		handler.loginStatusChanged(this);
	}

	@Override
	public Type<FacebookLoginHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<FacebookLoginHandler> getType() {
		return TYPE;
	}
		
}
