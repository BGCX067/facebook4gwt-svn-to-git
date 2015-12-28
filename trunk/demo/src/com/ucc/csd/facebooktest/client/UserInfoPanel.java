package com.ucc.csd.facebooktest.client;

import java.util.Date;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.reveregroup.gwt.facebook4gwt.Facebook;
import com.reveregroup.gwt.facebook4gwt.user.FacebookUser;
import com.reveregroup.gwt.facebook4gwt.user.UserField;

public class UserInfoPanel extends Composite {

	Grid grid = new Grid(48, 2);

	public UserInfoPanel() {
		int row = 0;
		grid.setText(row++, 0, UserField.UID.name());
		grid.setText(row++, 0, UserField.NAME.name());
		grid.setText(row++, 0, UserField.FIRST_NAME.name());
		grid.setText(row++, 0, UserField.LAST_NAME.name());
		grid.setText(row++, 0, UserField.ABOUT_ME.name());
		grid.setText(row++, 0, UserField.ACTIVITIES.name());
		grid.setText(row++, 0, UserField.AFFILIATIONS.name());
		grid.setText(row++, 0, UserField.BIRTHDAY.name());
		grid.setText(row++, 0, UserField.BOOKS.name());
		grid.setText(row++, 0, UserField.CURRENT_LOCATION.name());
		grid.setText(row++, 0, UserField.EDUCATION_HISTORY.name());
		// grid.setText(row++, 0, UserField.EMAIL_HASHES.name());
		grid.setText(row++, 0, UserField.FAMILY.name());
		grid.setText(row++, 0, UserField.HIGH_SCHOOL_INFO.name());
		grid.setText(row++, 0, UserField.HOMETOWN_LOCATION.name());
		grid.setText(row++, 0, UserField.INTERESTS.name());
		grid.setText(row++, 0, UserField.IS_APP_USER.name());
		grid.setText(row++, 0, UserField.LOCALE.name());
		grid.setText(row++, 0, UserField.MEETING_FOR.name());
		grid.setText(row++, 0, UserField.MEETING_SEX.name());
		grid.setText(row++, 0, UserField.MOVIES.name());
		grid.setText(row++, 0, UserField.MUSIC.name());
		grid.setText(row++, 0, UserField.NOTES_COUNT.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_WITH_LOGO.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_BIG.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_BIG_WITH_LOGO.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_SMALL.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_SMALL_WITH_LOGO.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_SQUARE.name());
		grid.setWidget(row, 1, new Image(Images.NONE));
		grid.setText(row++, 0, UserField.PIC_SQUARE_WITH_LOGO.name());
		grid.setText(row++, 0, UserField.POLITICAL.name());
		grid.setText(row++, 0, UserField.PROFILE_BLURB.name());
		grid.setText(row++, 0, UserField.PROFILE_UPDATE_TIME.name());
		grid.setText(row++, 0, UserField.PROFILE_URL.name());
		grid.setText(row++, 0, UserField.PROXIED_EMAIL.name());
		grid.setText(row++, 0, UserField.QUOTES.name());
		grid.setText(row++, 0, UserField.RELATIONSHIP_STATUS.name());
		grid.setText(row++, 0, UserField.RELIGION.name());
		grid.setText(row++, 0, UserField.SEX.name());
		grid.setText(row++, 0, UserField.SIGNIFICANT_OTHER_ID.name());
		grid.setText(row++, 0, UserField.STATUS.name());
		grid.setText(row++, 0, "STATUS_ID");
		grid.setText(row++, 0, "STATUS_UPDATE_TIME");
		grid.setText(row++, 0, UserField.TIMEZONE.name());
		grid.setText(row++, 0, UserField.TV.name());
		grid.setText(row++, 0, UserField.WALL_COUNT.name());
		grid.setText(row++, 0, UserField.WORK_HISTORY.name());
		grid.setText(row++, 0, "Friends");
		initWidget(grid);
	}

	public void setData(FacebookUser data) {
		int row = 0;
		grid.setText(row++, 1, data.getUID());
		grid.setText(row++, 1, data.getName());
		grid.setText(row++, 1, data.getFirstName());
		grid.setText(row++, 1, data.getLastName());
		grid.setText(row++, 1, data.getAboutMe());
		grid.setText(row++, 1, data.getActivities());
		setArray(row++, data.getAffiliations());
		grid.setText(row++, 1, data.getBirthday());
		grid.setText(row++, 1, data.getBooks());
		grid.setText(row++, 1, data.getCurrentLocation() == null ? "" : data.getCurrentLocation().toString());
		setArray(row++, data.getEducationHistory());
		// grid.setText(row++, 1, data.getEmailHashes());
		setArray(row++, data.getFamily());
		grid.setText(row++, 1, data.getHighSchoolInfo() == null ? "" : data.getHighSchoolInfo().toString());
		grid.setText(row++, 1, data.getHometownLocation() == null ? "" : data.getHometownLocation().toString());
		grid.setText(row++, 1, data.getInterests());
		grid.setText(row++, 1, data.isAppUser() == null ? "" : data.isAppUser().toString());
		grid.setText(row++, 1, data.getLocale());
		setArray(row++, data.getMeetingFor());
		setArray(row++, data.getMeetingSex());
		grid.setText(row++, 1, data.getMovies());
		grid.setText(row++, 1, data.getMusic());
		grid.setText(row++, 1, data.getNotesCount() == null ? "" : data.getNotesCount().toString());
		setImageUrl(row++, data.getPic());
		setImageUrl(row++, data.getPicWithLogo());
		setImageUrl(row++, data.getPicBig());
		setImageUrl(row++, data.getPicBigWithLogo());
		setImageUrl(row++, data.getPicSmall());
		setImageUrl(row++, data.getPicSmallWithLogo());
		setImageUrl(row++, data.getPicSquare());
		setImageUrl(row++, data.getPicSquareWithLogo());
		grid.setText(row++, 1, data.getPolitical());
		grid.setText(row++, 1, data.getProfileBlurb());
		grid.setText(row++, 1, data.getProfileUpdateTime() == null ? "" : new Date(data.getProfileUpdateTime() * 1000)
				.toString());
		grid.setText(row++, 1, data.getProfileURL());
		grid.setText(row++, 1, data.getProxiedEmail());
		grid.setText(row++, 1, data.getQuotes());
		grid.setText(row++, 1, data.getRelationshipStatus());
		grid.setText(row++, 1, data.getReligion());
		grid.setText(row++, 1, data.getSex());
		grid.setText(row++, 1, data.getSignificantOtherId());
		grid.setText(row++, 1, data.getStatus());
		grid.setText(row++, 1, data.getStatusId());
		grid.setText(row++, 1, data.getStatusUpdateTime() == null ? "" : new Date(data.getStatusUpdateTime() * 1000)
				.toString());
		grid.setText(row++, 1, data.getTimezone() == null ? "" : data.getTimezone().toString());
		grid.setText(row++, 1, data.getTv());
		grid.setText(row++, 1, data.getWallCount() == null ? "" : data.getWallCount().toString());
		setArray(row++, data.getWorkHistory());
		grid.setText(row, 1, "");
		if (data.getUID().trim().equals(Facebook.getLoggedInUserId().trim())) {
			fillFriends(row);
		}
	}
	
	private void fillFriends(final int row) {
		Facebook.log("go");
		Facebook.APIClient().friends_get(new AsyncCallback<String[]>() {
			public void onSuccess(String[] result) {
				Facebook.log(result);
				Facebook.APIClient().users_getInfo(result, new AsyncCallback<FacebookUser[]>() {
					public void onSuccess(FacebookUser[] result) {
						Facebook.log(result);
						VerticalPanel panel = new VerticalPanel();
						for (FacebookUser usr : result) {
							LinkButton link = new LinkButton();
							link.setText(usr.getName() + " (" + usr.getUID() + ")");
							link.setTitle(usr.getUID());
							link.addClickHandler(friendClickHandler);
							panel.add(link);
						}
						grid.setWidget(row, 1, panel);
					}
					public void onFailure(Throwable caught) {
						Window.alert("Unable to fetch friends' names.");
					}
				}, UserField.UID, UserField.NAME);
			}
			public void onFailure(Throwable caught) {
				Window.alert("Unable to fetch friends.");
			}
		});
	}
	
	private UserInfoDialogBox friendInfoDialog;
	
	private ClickHandler friendClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			String uid = ((LinkButton) event.getSource()).getTitle();
			if (friendInfoDialog == null)
				friendInfoDialog = new UserInfoDialogBox();
			friendInfoDialog.show(uid);
		};
	};

	private void setImageUrl(int row, String url) {
		((Image) grid.getWidget(row, 1)).setUrl(url == null ? Images.NONE : url);
	}

	private void setArray(int row, Object[] array) {
		if (array == null || array.length == 0) {
			grid.setWidget(row, 1, null);
			return;
		}
		VerticalPanel vp = new VerticalPanel();
		for (Object o : array) {
			if (o == null)
				vp.add(new HTML("-- - --"));
			else
				vp.add(new Label(o.toString()));
		}
		grid.setWidget(row, 1, vp);
	}
}
