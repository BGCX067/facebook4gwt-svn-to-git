package com.reveregroup.gwt.facebook4gwt.user;


import com.google.gwt.core.client.JsArrayString;
import com.reveregroup.gwt.facebook4gwt.JsObject;

public class EducationInfo {
	String name;
	Integer year;
	String degree;
	String[] concentrations;
	
	public EducationInfo(JsObject data) {
		name = data.getString("name");
		year = data.getInt("year");
		degree = data.getString("degree");
		
		JsArrayString a = data.getJsObject("concentrations").cast();
		concentrations = new String[a.length()];
		for (int i = 0; i < concentrations.length; i++) {
			concentrations[i] = a.get(i);
		}
	}

	public String getName() {
		return name;
	}

	public Integer getYear() {
		return year;
	}

	public String[] getConcentrations() {
		return concentrations;
	}

	public String getDegree() {
		return degree;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (degree != null)
			sb.append(degree).append(", ");
		if (name != null)
			sb.append(name);
		if (year != null)
			sb.append(" ").append(year);
		if (concentrations != null && concentrations.length != 0) {
			sb.append(" (");
			for (String s : concentrations) {
				sb.append(s).append(", ");
			}
			sb.replace(sb.length() - 2, sb.length(), ")");
		}
		return sb.toString();
	}

}
