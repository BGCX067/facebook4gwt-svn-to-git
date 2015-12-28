package com.ucc.csd.facebooktest.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.reveregroup.gwt.facebook4gwt.Facebook;
import com.reveregroup.gwt.facebook4gwt.FacebookImage;
import com.reveregroup.gwt.facebook4gwt.FacebookStory;
import com.reveregroup.gwt.facebook4gwt.LoginButton;
import com.reveregroup.gwt.facebook4gwt.FacebookImage.Type;
import com.reveregroup.gwt.facebook4gwt.events.FacebookLoginEvent;
import com.reveregroup.gwt.facebook4gwt.events.FacebookLoginHandler;
import com.reveregroup.gwt.facebook4gwt.user.FacebookUser;
import com.reveregroup.gwt.facebook4gwt.user.UserField;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FacebookTest implements EntryPoint {
	private Label statusLabel = new Label("Connecting to Facebook...");
	private static List<NewsArticle> news;
	private LoginButton loginButton;

	private LinkButton postButton1 = new LinkButton();
	private LinkButton postButton2 = new LinkButton();
	private LinkButton postButton3 = new LinkButton();

	private FlowPanel userInfoPanel = new FlowPanel();
	private Label userName = new Label();
	private Label userStatus = new Label();
	private Image userImage = new Image();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Initialize the Facebook Connect API with our API Key
		Facebook.init("766389cf0f4ec2cbd2d568e6f78ae170");

		// **** Login and Logout ****

		// Create a Facebook login/logout button
		boolean alsoUseForLogout = true;
		loginButton = new LoginButton(alsoUseForLogout);

		// Handle login and logout events
		Facebook.addLoginHandler(new FacebookLoginHandler() {
			public void loginStatusChanged(FacebookLoginEvent event) {
				if (event.isLoggedIn()) {
					statusLabel.setText("Logged in");
				} else {
					statusLabel.setText("No user logged in");
				}
				refreshUserInfo();
			}
		});

		// **** Post news stories to facebook ***

		postButton1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NewsArticle article = getNews().get(0);
				postArticleToFacebook(article.getHeadline(), article.getContent(), article.getImageURL());
			}
		});

		postButton2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NewsArticle article = getNews().get(1);
				postArticleToFacebook(article.getHeadline(), article.getContent(), article.getImageURL());
			}
		});

		postButton3.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NewsArticle article = getNews().get(2);
				postArticleToFacebook(article.getHeadline(), article.getContent(), article.getImageURL());
			}
		});

		// **** Build the GWT UI ****

		RootPanel.get("facebookLogin").add(loginButton);
		RootPanel.get("facebookLogin").add(statusLabel);

		userName.getElement().getStyle().setProperty("fontWeight", "bold");
		userInfoPanel.add(userName);
		userInfoPanel.add(userStatus);
		userImage.addClickHandler(new ClickHandler() {
			private UserInfoDialogBox dg;
			
			public void onClick(ClickEvent event) {
				String uid = Facebook.getLoggedInUserId();
				if (uid != null) {
					if (dg == null)
						dg = new UserInfoDialogBox();
					dg.show(uid);
				}
			}
		});
		userInfoPanel.add(userImage);
		userInfoPanel.setVisible(false);
		RootPanel.get("facebookLogin").add(userInfoPanel);

		String btnHTML = "<img src=\"" + FacebookImage.getURL(Type.SHARE_ICON) + "\"/> Post this Story to Facebook.";
		postButton1.setHTML(btnHTML);
		postButton1.setStyleName("facebookPost");
		RootPanel.get("facebookPostButton1").add(postButton1);

		postButton2.setHTML(btnHTML);
		postButton2.setStyleName("facebookPost");
		RootPanel.get("facebookPostButton2").add(postButton2);

		postButton3.setHTML(btnHTML);
		postButton3.setStyleName("facebookPost");
		RootPanel.get("facebookPostButton3").add(postButton3);
	}

	/**
	 * If a user is logged in, display his or her name, status and profile
	 * image. If not, do not display this information.
	 */
	private void refreshUserInfo() {
		if (Facebook.isLoggedIn()) {
			AsyncCallback<FacebookUser>
			callback = new AsyncCallback<FacebookUser>() {
				public void onSuccess(FacebookUser result) {
					userName.setText(result.getName());
					userStatus.setText(result.getStatus());
					userImage.setUrl(result.getPic());
					userInfoPanel.setVisible(true);
				}

				public void onFailure(Throwable caught) {
					userInfoPanel.setVisible(false);
				}
			};
			Facebook.APIClient().users_getLoggedInUser(callback,
													   UserField.NAME,
													   UserField.STATUS,
													   UserField.PIC);
		} else {
			userInfoPanel.setVisible(false);
		}
	}

	/**
	 * Post a news article to Facebook wall and news feed.
	 */
	private void postArticleToFacebook(String headline, String articleContent, String imageURL) {
		FacebookStory fbStory = new FacebookStory();
		fbStory.setTemplateBundleId(Constants.FEED_TEMPLATE_ID);
		fbStory.setPrompt("Add your thoughts if you like...");

		fbStory.putData("headline", headline);
		fbStory.putData("story_url", "http://chicago-gtug.appspot.com");
		fbStory.setBodyGeneral(articleContent);
		fbStory.addImage(imageURL, "http://chicago-gtug.appspot.com");

		fbStory.showFeedDialog();
	}

	/**
	 * Generate some news.
	 */
	private static List<NewsArticle> getNews() {
		if (news == null) {
			news = new ArrayList<NewsArticle>(3);
			NewsArticle article;

			article = new NewsArticle();
			article.setHeadline("Americans Value Science, but Not All of It: Survey");
			article
					.setContent("CHICAGO (Reuters) - Many Americans still value the nation's scientific achievements, but unlike most scientists, they often pick and choose which scientific findings they agree with, especially in the areas of climate change and evolution, according to a survey released on Thursday.");
			article.setImageURL("http://chicago-gtug.appspot.com/images/science.jpg");
			news.add(article);

			article = new NewsArticle();
			article.setHeadline("NASA preps space shuttle Endeavour for Saturday launch");
			article
					.setContent("Houston (TX) - NASA has confirmed that launch countdown operations for the space shuttle Endeavour remain on schedule. The shuttle is expected to lift off from Florida's Kennedy Space Center, on Saturday, 7:39 p.m. EDT.");
			article.setImageURL("http://chicago-gtug.appspot.com/images/nasa.jpg");
			news.add(article);

			article = new NewsArticle();
			article.setHeadline("Amazon's Kindle to Sell Law Books");
			article.setContent("A nonprofit group that provides continuing education for lawyers is making its books available for sale on Amazon.com Inc.'s Kindle, underscoring the widening appeal of the digital reader.");
			article.setImageURL("http://chicago-gtug.appspot.com/images/kindle.jpg");
			news.add(article);
		}

		return news;
	}
}
