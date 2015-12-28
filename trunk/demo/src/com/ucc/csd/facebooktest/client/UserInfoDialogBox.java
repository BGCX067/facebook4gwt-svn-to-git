package com.ucc.csd.facebooktest.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.reveregroup.gwt.facebook4gwt.Facebook;
import com.reveregroup.gwt.facebook4gwt.user.FacebookUser;
import com.reveregroup.gwt.facebook4gwt.user.UserField;

public class UserInfoDialogBox extends DialogBox {
	
	public UserInfoDialogBox() {
		addDomHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		}, ClickEvent.getType());
	}
	
	public void show(String userId) {
		setText("Info for user: " + userId + "   [X]");
		setPopupPosition(50, 50);
		setWidget(loadingLabel);
		super.show();
		Facebook.APIClient().users_getInfo(userId, callback, UserField.ALL);
	}

	public void show(FacebookUser user) {
		setText("Info for user: " + user.getUID() + "   [X]");
		setPopupPosition(50, 50);
		if (panel == null)
			panel = new UserInfoPanel();
		setWidget(panel);
		panel.setData(user);
		super.show();
	}
	
	private UserInfoPanel panel = new UserInfoPanel();

	private Label loadingLabel = new Label("Loading...");

	private AsyncCallback<FacebookUser> callback = new AsyncCallback<FacebookUser>() {
		public void onSuccess(FacebookUser result) {
			show(result);
		}

		public void onFailure(Throwable caught) {
			Window.alert(caught.getMessage());
		}
	};
}
