package com.reveregroup.gwt.facebook4gwt;


import com.google.gwt.user.client.ui.Image;
import com.reveregroup.gwt.facebook4gwt.LoginButton.Background;
import com.reveregroup.gwt.facebook4gwt.LoginButton.Length;
import com.reveregroup.gwt.facebook4gwt.LoginButton.Size;

public class FacebookImage extends Image {
	public static enum Type {
		SHARE_ICON, LOGIN_BUTTON, LOGOUT_BUTTON
	}
	
	private Type type;
	
	private Size size;
	
	private Background background;
	
	private Length length;
	
	public FacebookImage() {
		setType(null);
	}
	
	public FacebookImage(Type type) {
		setType(type);
	}
	public FacebookImage(Type type, Size size, Background background, Length length) {
		this.size = size;
		this.background = background;
		this.length = length;
		setType(type);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
		
		setUrl(getURL(type, size, background, length));
	}
	
	public static String getURL(Type type) {
		return getURL(type, null, null, null);
	}
	
	public static String getURL(Type type, Size size, Background background, Length length) {
		if (type == null) type = Type.SHARE_ICON;
		String sz, bg, lg;
		
		switch(type) {
		case LOGIN_BUTTON:
			if (size == Size.SMALL && length == Length.LONG)
				return null;
			
			sz = size != null ? size.name().toLowerCase() : "large";
			bg = background != null ? background.name().toLowerCase() : "light";
			lg = length != null ? length.name().toLowerCase() : "short";
			
			return "http://static.ak.fbcdn.net/images/fbconnect/login-buttons/connect_" + bg + "_" + sz + "_" + lg + ".gif";
		case LOGOUT_BUTTON:
			sz = size != null ? size.name().toLowerCase() : "large";
			return "http://b.static.ak.fbcdn.net/images/fbconnect/logout-buttons/logout_" + sz + ".gif";
		case SHARE_ICON:
		default:
			return "http://static.ak.fbcdn.net/images/share/facebook_share_icon.gif";
		}
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
		setType(type);
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
		setType(type);
	}

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
		setType(type);
	}
}
