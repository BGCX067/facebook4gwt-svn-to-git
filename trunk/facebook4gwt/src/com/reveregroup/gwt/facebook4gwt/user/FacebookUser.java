package com.reveregroup.gwt.facebook4gwt.user;

import java.util.HashMap;
import java.util.Map;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.reveregroup.gwt.facebook4gwt.JsObject;

/**
 * Object for carrying information about a Facebook user. Use the APIClient
 * calls to fetch this information.
 * 
 * @author David Wolverton
 */
public class FacebookUser {
	String uid;
	String aboutMe;
	String activities;
	NetworkAffiliation[] affiliations;
	String birthday;
	String books;
	Location currentLocation;
	EducationInfo[] educationHistory;
//	String emailHashes;
	FamilyRelationship[] family;
	String firstName;
	Location hometownLocation;
	HighSchoolInfo highSchoolInfo;
	String interests;
	Boolean isAppUser;
	String lastName;
	String locale;
	String[] meetingFor;
	String[] meetingSex;
	String movies;
	String music;
	String name;
	Integer notesCount;
	String pic;
	String picWithLogo;
	String picBig;
	String picBigWithLogo;
	String picSmall;
	String picSmallWithLogo;
	String picSquare;
	String picSquareWithLogo;
	String political;
	String profileBlurb;
	Long profileUpdateTime;
	String profileURL;
	String proxiedEmail;
	String quotes;
	String relationshipStatus;
	String religion;
	String sex;
	String significantOtherId;
	String status;
	String statusId;
	Long statusUpdateTime;
	Integer timezone;
	String tv;
	Integer wallCount;
	WorkInfo[] workHistory;

	public FacebookUser() {
	}

	public FacebookUser(JavaScriptObject data) {
		setData(data);
	}

	public void setData(JavaScriptObject data) {
		JsObject d = data.cast();
		uid = d.getString(UserField.UID.str);
		aboutMe = d.getString(UserField.ABOUT_ME.str);
		activities = d.getString(UserField.ACTIVITIES.str);
		birthday = d.getString(UserField.BIRTHDAY.str);
		books = d.getString(UserField.BOOKS.str);
//		emailHashes = d.getString(UserField.EMAIL_HASHES.str);
		firstName = d.getString(UserField.FIRST_NAME.str);
		interests = d.getString(UserField.INTERESTS.str);
		isAppUser = d.getBoolean(UserField.IS_APP_USER.str);
		lastName = d.getString(UserField.LAST_NAME.str);
		locale = d.getString(UserField.LOCALE.str);
		movies = d.getString(UserField.MOVIES.str);
		music = d.getString(UserField.MUSIC.str);
		name = d.getString(UserField.NAME.str);
		notesCount = d.getInt(UserField.NOTES_COUNT.str);
		pic = d.getString(UserField.PIC.str);
		picWithLogo = d.getString(UserField.PIC_WITH_LOGO.str);
		picBig = d.getString(UserField.PIC_BIG.str);
		picBigWithLogo = d.getString(UserField.PIC_BIG_WITH_LOGO.str);
		picSmall = d.getString(UserField.PIC_SMALL.str);
		picSmallWithLogo = d.getString(UserField.PIC_SMALL_WITH_LOGO.str);
		picSquare = d.getString(UserField.PIC_SQUARE.str);
		picSquareWithLogo = d.getString(UserField.PIC_SQUARE_WITH_LOGO.str);
		political = d.getString(UserField.POLITICAL.str);
		profileBlurb = d.getString(UserField.PROFILE_BLURB.str);
		profileUpdateTime = d.getLong(UserField.PROFILE_UPDATE_TIME.str);
		profileURL = d.getString(UserField.PROFILE_URL.str);
		proxiedEmail = d.getString(UserField.PROXIED_EMAIL.str);
		quotes = d.getString(UserField.QUOTES.str);
		relationshipStatus = d.getString(UserField.RELATIONSHIP_STATUS.str);
		religion = d.getString(UserField.RELIGION.str);
		sex = d.getString(UserField.SEX.str);
		significantOtherId = d.getString(UserField.SIGNIFICANT_OTHER_ID.str);
		timezone = d.getInt(UserField.TIMEZONE.str);
		tv = d.getString(UserField.TV.str);
		wallCount = d.getInt(UserField.WALL_COUNT.str);

		JsObject x;

		x = d.getJsObject(UserField.STATUS.str);
		if (x != null) {
			status = x.getString("message");
			statusId = x.getString("status_id");
			statusUpdateTime = x.getLong("time");
		}

		x = d.getJsObject(UserField.AFFILIATIONS.str);
		if (x != null) {
			affiliations = new NetworkAffiliation[x.length()];
			for (int i = 0; i < affiliations.length; i++) {
				JsObject o = x.getJsObject(i);
				if (o == null)
					affiliations[i] = null;
				else
					affiliations[i] = new NetworkAffiliation(o);
			}
		}

		x = d.getJsObject(UserField.CURRENT_LOCATION.str);
		if (x != null) {
			currentLocation = new Location(x);
		}

		x = d.getJsObject(UserField.EDUCATION_HISTORY.str);
		if (x != null) {
			educationHistory = new EducationInfo[x.length()];
			for (int i = 0; i < educationHistory.length; i++) {
				JsObject o = x.getJsObject(i);
				if (o == null)
					educationHistory[i] = null;
				else
					educationHistory[i] = new EducationInfo(o);
			}
		}

		x = d.getJsObject(UserField.FAMILY.str);
		if (x != null) {
			family = new FamilyRelationship[x.length()];
			for (int i = 0; i < family.length; i++) {
				JsObject o = x.getJsObject(i);
				if (o == null)
					family[i] = null;
				else
					family[i] = new FamilyRelationship(o);
			}
		}

		x = d.getJsObject(UserField.HOMETOWN_LOCATION.str);
		if (x != null) {
			hometownLocation = new Location(x);
		}

		x = d.getJsObject(UserField.HIGH_SCHOOL_INFO.str);
		if (x != null) {
			highSchoolInfo = new HighSchoolInfo(x);
		}

		JsArrayString a;
		a = d.getJsObject(UserField.MEETING_FOR.str).cast();
		if (a != null) {
			meetingFor = new String[a.length()];
			for (int i = 0; i < meetingFor.length; i++) {
				meetingFor[i] = a.get(i);
			}
		}

		a = d.getJsObject(UserField.MEETING_SEX.str).cast();
		if (a != null) {
			meetingSex = new String[a.length()];
			for (int i = 0; i < meetingSex.length; i++) {
				meetingSex[i] = a.get(i);
			}
		}

		x = d.getJsObject(UserField.WORK_HISTORY.str);
		if (x != null) {
			workHistory = new WorkInfo[x.length()];
			for (int i = 0; i < workHistory.length; i++) {
				JsObject o = x.getJsObject(i);
				if (o == null)
					workHistory[i] = null;
				else
					workHistory[i] = new WorkInfo(o);
			}
		}
	}

	public String getUID() {
		return uid;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public String getActivities() {
		return activities;
	}

	public NetworkAffiliation[] getAffiliations() {
		return affiliations;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBooks() {
		return books;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public EducationInfo[] getEducationHistory() {
		return educationHistory;
	}

//	public String getEmailHashes() {
//		return emailHashes;
//	}

	public FamilyRelationship[] getFamily() {
		return family;
	}

	public String getFirstName() {
		return firstName;
	}

	public Location getHometownLocation() {
		return hometownLocation;
	}

	public HighSchoolInfo getHighSchoolInfo() {
		return highSchoolInfo;
	}

	public String getInterests() {
		return interests;
	}

	public Boolean isAppUser() {
		return isAppUser;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLocale() {
		return locale;
	}

	public String[] getMeetingFor() {
		return meetingFor;
	}

	public String[] getMeetingSex() {
		return meetingSex;
	}

	public String getMovies() {
		return movies;
	}

	public String getMusic() {
		return music;
	}

	public String getName() {
		return name;
	}

	public Integer getNotesCount() {
		return notesCount;
	}

	public String getPic() {
		return pic;
	}

	public String getPicWithLogo() {
		return picWithLogo;
	}

	public String getPicBig() {
		return picBig;
	}

	public String getPicBigWithLogo() {
		return picBigWithLogo;
	}

	public String getPicSmall() {
		return picSmall;
	}

	public String getPicSmallWithLogo() {
		return picSmallWithLogo;
	}

	public String getPicSquare() {
		return picSquare;
	}

	public String getPicSquareWithLogo() {
		return picSquareWithLogo;
	}

	public String getPolitical() {
		return political;
	}

	public String getProfileBlurb() {
		return profileBlurb;
	}

	public Long getProfileUpdateTime() {
		return profileUpdateTime;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public String getProxiedEmail() {
		return proxiedEmail;
	}

	public String getQuotes() {
		return quotes;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public String getReligion() {
		return religion;
	}

	public String getSex() {
		return sex;
	}

	public String getSignificantOtherId() {
		return significantOtherId;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusId() {
		return statusId;
	}

	public Long getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public String getTv() {
		return tv;
	}

	public Integer getWallCount() {
		return wallCount;
	}

	public WorkInfo[] getWorkHistory() {
		return workHistory;
	}

}
