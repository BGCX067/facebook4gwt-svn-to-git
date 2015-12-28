package com.reveregroup.gwt.facebook4gwt;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * A Login button widget for Facebook. See <a
 * href="http://wiki.developers.facebook.com/index.php/Fb:login-button"
 * >http://wiki.developers.facebook.com/index.php/Fb:login-button</a>
 * 
 * @author David Wolverton
 */
public class LoginButton extends Widget {
	public LoginButton() {
		setElement(DOM.createDiv());
		addElement(getElement());
	}

	/**
	 * @param autologoutlink
	 *            - if true, the button will act as logout button whenever a
	 *            user is logged in.
	 */
	public LoginButton(boolean autologoutlink) {
		setElement(DOM.createDiv());
		if (autologoutlink) {
			getElement().setAttribute("autologoutlink", "true");
		}
		addElement(getElement());
	}

	/**
	 * 
	 * @param alsoLogout
	 *            - if true, the button will act as logout button whenever a
	 *            user is logged in.
	 * @param size
	 *            - customize the look of the button by selecting SMALL, MEDIUM
	 *            or LARGE. (Default LARGE)
	 * @param background
	 *            - background color of your site so that the edges of the
	 *            button can be matched (WHITE, LIGHT or DARK). (Default LIGHT)
	 * @param length
	 *            - specifies the length of text to use for the button (SHORT:
	 *            "Connect" or LONG: "Connect with Facebook"). (Default SHORT)
	 */
	public LoginButton(boolean alsoLogout, Size size, Background background, Length length) {
		setElement(DOM.createDiv());
		if (size != null)
			getElement().setAttribute("size", size.toString().toLowerCase());
		if (background != null)
			getElement().setAttribute("background", background.toString().toLowerCase());
		if (length != null)
			getElement().setAttribute("length", length.toString().toLowerCase());
		if (alsoLogout) {
			getElement().setAttribute("autologoutlink", "true");
		}
		addElement(getElement());
	}

	private native void addElement(Element element) /*-{
		$wnd.FB_RequireFeatures(["XFBML"], function() {
			$wnd.FB.XFBML.Host.addElement(new $wnd.FB.XFBML.LoginButton(element));
		});
	}-*/;

	public static enum Size {
		SMALL, MEDIUM, LARGE
	}

	public static enum Background {
		DARK, LIGHT, WHITE
	}

	public static enum Length {
		SHORT, LONG
	}
}
