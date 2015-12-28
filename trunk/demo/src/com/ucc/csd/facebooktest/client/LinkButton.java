package com.ucc.csd.facebooktest.client;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ButtonBase;

public class LinkButton extends ButtonBase {
	public LinkButton() {
		super(DOM.createAnchor());
		AnchorElement.as(getElement()).setHref("#");
		addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				event.preventDefault();
			}
		});
	}
}
