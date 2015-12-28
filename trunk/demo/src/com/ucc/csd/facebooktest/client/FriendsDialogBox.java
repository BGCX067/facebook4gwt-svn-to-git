package com.ucc.csd.facebooktest.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.reveregroup.gwt.facebook4gwt.Facebook;

public class FriendsDialogBox extends DialogBox {

	public FriendsDialogBox() {
		addDomHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		}, ClickEvent.getType());
	}

	public void show(String list) {
		setText("Friends");
		setWidget(loadingLabel);
		super.show();
		center();
		Facebook.APIClient().friends_get(callback);
	}

	private Label loadingLabel = new Label("Loading...");
	
	private AsyncCallback<String[]> callback = new AsyncCallback<String[]>() {
		public void onSuccess(String[] result) {
			VerticalPanel panel = new VerticalPanel();
			for (String s : result) {
				LinkButton link = new LinkButton();
				link.setText(s);
				link.addClickHandler(itemClickHandler);
				panel.add(link);
			}
			setWidget(panel);
			center();
		}

		public void onFailure(Throwable caught) {
			Window.alert(caught.getMessage());
		}
	};

	private UserInfoDialogBox userInfoDialog;
	
	private ClickHandler itemClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			String uid = ((LinkButton) event.getSource()).getText();
			if (userInfoDialog == null)
				userInfoDialog = new UserInfoDialogBox();
			userInfoDialog.show(uid);
		};
	};
}
