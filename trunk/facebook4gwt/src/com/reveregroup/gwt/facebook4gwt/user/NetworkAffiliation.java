package com.reveregroup.gwt.facebook4gwt.user;

import com.reveregroup.gwt.facebook4gwt.JsObject;

public class NetworkAffiliation {
	public static enum Type {
		COLLEGE, HIGH_SCHOOL, WORK, REGION
	}
	
	public NetworkAffiliation(JsObject data) {
		year = data.getInt("year");
		networkId = data.getString("nid");
		name = data.getString("name");
		status = data.getString("status");
		
		String typ = data.getString("type");
		if ("college".equals(typ)) {
			type = Type.COLLEGE;
		} else if ("high school".equals(typ)) {
			type = Type.HIGH_SCHOOL;
		} else if ("work".equals(typ)) {
			type = Type.WORK;
		} else if ("region".equals(typ)) {
			type = Type.REGION;
		}
	}

	Type type;
	Integer year;
	String name;
	String networkId;
	String status;

	public Type getType() {
		return type;
	}

	public Integer getYear() {
		return year;
	}

	public String getName() {
		return name;
	}

	public String getNetworkId() {
		return networkId;
	}

	public String getStatus() {
		return status;
	}
	
	public String toString() {
		String s = ((type == null) ? "NO TYPE" : type.name()) + ": " + name + " (" + networkId + ")";
		if (year != null)s += ", " + year;
		if (status != null) s+= " [" + status + "]";
		return s;
	}

}
