package com.reveregroup.gwt.facebook4gwt.user;

import com.reveregroup.gwt.facebook4gwt.JsObject;

public class HighSchoolInfo {
	Integer gradYear;
	String highSchool1Id;
	String highSchool1Name;
	String highSchool2Id;
	String highSchool2Name;
	
	public HighSchoolInfo(JsObject data) {
		gradYear = data.getInt("grad_year");
		highSchool1Id = data.getString("hs1_id");
		highSchool1Name = data.getString("hs1_name");
		highSchool2Id = data.getString("hs2_id");
		highSchool2Name = data.getString("hs2_name");
		
		if ("0".equals(highSchool1Id))
			highSchool1Id = null;
		if ("".equals(highSchool1Name))
			highSchool1Name = null;
		if ("0".equals(highSchool2Id))
			highSchool2Id = null;
		if ("".equals(highSchool2Name))
			highSchool2Name = null;
	}

	public Integer getGradYear() {
		return gradYear;
	}

	public String getHighSchool1Id() {
		return highSchool1Id;
	}

	public String getHighSchool1Name() {
		return highSchool1Name;
	}

	public String getHighSchool2Id() {
		return highSchool2Id;
	}

	public String getHighSchool2Name() {
		return highSchool2Name;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (gradYear != null)
			sb.append("Graduated: ").append(gradYear);
		if (highSchool1Id != null || highSchool1Name != null) {
			sb.append(" [HS1: ").append(highSchool1Name).append(" (").append(highSchool1Id).append(")");
		}
		if (highSchool2Id != null || highSchool2Name != null) {
			if (highSchool1Id != null || highSchool1Name != null) {
				sb.append(", HS2: ");
			} else {
				sb.append(" [HS2: ");
			}
			sb.append("HS2: ").append(highSchool1Name).append(" (").append(highSchool1Id).append(")");
		}
		if (highSchool1Id != null || highSchool1Name != null || highSchool2Id != null || highSchool2Name != null)
			sb.append("]");
		return sb.toString();
	}

}
