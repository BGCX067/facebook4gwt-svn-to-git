package com.reveregroup.gwt.facebook4gwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Demo implements EntryPoint
{

    public void onModuleLoad()
    {
	Facebook.init("0424feebf69d865612c6bbbfd6d72f0b");
	
	VerticalPanel panel = new VerticalPanel();
	panel.setHeight("100px");
	panel.setWidth("100px");
	panel.getElement().getStyle().setProperty("background", "yellow");
	RootPanel.get("page").add(panel);

	Button button1 = new Button("post test 1 for facebook");
	panel.add(button1);
	button1.addClickHandler(new ClickHandler()
	{

	    public void onClick(ClickEvent event)
	    {
		FacebookStory story = new FacebookStory();

		story.setUserMessage("This is the user message");

		story.putData("name", "This is the name(title)");
		story.putData("caption", "This is the caption {*actor*}");
		story.putData("description", "This is the description");
		story.putData("href", "http://www.google.com");

		story.addImage("http://www.google.com/intl/en_ALL/images/logo.gif", "http://www.google.com");
		story.addImage("http://l.yimg.com/a/i/mntl/ww/events/p.gif", "http://www.yahoo.com");

		story.setActionLinkHref("http://www.yahoo.com");
		story.setActionLinkText("action link Test");

		story.setPrompt("what's up?");

		story.ui_streamPublish();
	    }
	});

	Button button2 = new Button("post test 2 for facebook");
	panel.add(button2);
	button2.addClickHandler(new ClickHandler()
	{

	    public void onClick(ClickEvent event)
	    {
		FacebookStory story = new FacebookStory();

		story.setUserMessage("This is the user message");

		story.putData("name", "This is the name(title)");
		story.putData("caption", "This is the caption {*actor*}");
		story.putData("description", "This is the description");
		story.putData("href", "http://www.google.com");

		story.setActionLinkHref("http://www.yahoo.com");
		story.setActionLinkText("action link Test");

		story.setPrompt("what's up?");

		story.ui_streamPublish();
	    }
	});

	Button button3 = new Button("post test 3 for facebook");
	panel.add(button3);
	button3.addClickHandler(new ClickHandler()
	{

	    public void onClick(ClickEvent event)
	    {
		FacebookStory story = new FacebookStory();

		story.setUserMessage("This is the user message");

		story.putData("name", "This is the name(title)");
		story.putData("caption", "This is the caption {*actor*}");
		story.putData("description", "This is the description");
		story.putData("href", "http://www.google.com");

		story.addImage("http://www.google.com/intl/en_ALL/images/logo.gif", "http://www.google.com");
		story.addImage("http://l.yimg.com/a/i/mntl/ww/events/p.gif", "http://www.yahoo.com");

		story.setPrompt("what's up?");

		story.ui_streamPublish();
	    }
	});

	Button button4 = new Button("post test 4 for facebook");
	panel.add(button4);
	button4.addClickHandler(new ClickHandler()
	{

	    public void onClick(ClickEvent event)
	    {
		FacebookStory story = new FacebookStory();

		story.setUserMessage("This is the user message");

		story.setActionLinkHref("http://www.yahoo.com");
		story.setActionLinkText("action link Test");

		story.setPrompt("what's up?");

		story.ui_streamPublish();
	    }
	});

    }

}
