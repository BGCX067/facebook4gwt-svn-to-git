package com.reveregroup.gwt.facebook4gwt.user;

import com.reveregroup.gwt.facebook4gwt.JsObject;

public class WorkInfo {
	Location location;
	String companyName;
	String position;
	String description;
	String startDate;
	String endDate;
	
	public WorkInfo(JsObject data) {
		JsObject x = data.getJsObject("location");
		if (x != null) {
			location = new Location(x);
		}
		companyName = data.getString("company_name");
		position = data.getString("position");
		description = data.getString("description");
		startDate = data.getString("startDate");
		endDate = data.getString("endDate");
	}

	public Location getLocation() {
		return location;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPosition() {
		return position;
	}

	public String getDescription() {
		return description;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (position != null)
			sb.append(position);
		if (companyName != null)
			sb.append(" at ").append(companyName);
		if (location != null)
			sb.append(" [").append(location.toString()).append("]");
		if (startDate != null || endDate != null) {
			sb.append("(").append(startDate == null ? "" : startDate).append(" - ").append(endDate == null ? "" : endDate).append(")");
		}
		if (description != null && description.length() != 0)
			sb.append(" ~ ").append(description);
		return sb.toString();
	}
}
