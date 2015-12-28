package com.reveregroup.gwt.facebook4gwt.user;

import com.reveregroup.gwt.facebook4gwt.JsObject;

public enum UserField {
		UID("uid", true), ABOUT_ME("about_me"), ACTIVITIES("activities"), AFFILIATIONS("affiliations"), BIRTHDAY("birthday"), BOOKS(
				"books"), CURRENT_LOCATION("current_location"), EDUCATION_HISTORY("education_history"), /*EMAIL_HASHES(
				"email_hashes"),*/ FAMILY("family"), FIRST_NAME("first_name"), HOMETOWN_LOCATION("hometown_location"), HIGH_SCHOOL_INFO(
				"hs_info"), INTERESTS("interests"), IS_APP_USER("is_app_user"), LAST_NAME("last_name"), LOCALE("locale"), MEETING_FOR(
				"meeting_for"), MEETING_SEX("meeting_sex"), MOVIES("movies"), MUSIC("music"), NAME("name", true), NOTES_COUNT(
				"notes_count"), PIC("pic"), PIC_WITH_LOGO("pic_with_logo"), PIC_BIG("pic_big"), PIC_BIG_WITH_LOGO(
				"pic_big_with_logo"), PIC_SMALL("pic_small"), PIC_SMALL_WITH_LOGO("pic_small_with_logo"), PIC_SQUARE(
				"pic_square"), PIC_SQUARE_WITH_LOGO("pic_square_with_logo"), POLITICAL("political"), PROFILE_BLURB(
				"profile_blurb"), PROFILE_UPDATE_TIME("profile_update_time"), PROFILE_URL("profile_url"), PROXIED_EMAIL(
				"proxied_email"), QUOTES("quotes"), RELATIONSHIP_STATUS("relationship_status"), RELIGION("religion"), SEX(
				"sex"), SIGNIFICANT_OTHER_ID("significant_other_id"), STATUS("status"), TIMEZONE("timezone"), TV("tv"), WALL_COUNT(
				"wall_count"), WORK_HISTORY("work_history"), ALL(null), DEFAULT(null);

		String str;

		boolean isDefault = false;

		private UserField(String str) {
			this.str = str;
		}

		private UserField(String str, boolean isDefault) {
			this.str = str;
			this.isDefault = isDefault;
		}
		
		public static JsObject convertFields(UserField[] fields) {
			if (fields == null || fields.length == 0) {
				fields = new UserField[] { UserField.DEFAULT };
			}

			JsObject array = JsObject.newArray();
			for (UserField f : fields) {
				if (f != null) {
					if (f == UserField.ALL) {
						for (UserField ff : UserField.values()) {
							if (ff.str != null)
								array.push(ff.str);
						}
					} else if (f == UserField.DEFAULT) {
						for (UserField ff : UserField.values()) {
							if (ff.isDefault)
								array.push(ff.str);
						}
					} else {
						array.push(f.str);
					}
				}
			}

			return array;
		}
	}