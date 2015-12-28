package com.reveregroup.gwt.facebook4gwt.user;

import com.reveregroup.gwt.facebook4gwt.JsObject;

public class FamilyRelationship {
	private String relationship;
	private String name;
	
	public FamilyRelationship(JsObject data) {
		relationship = data.getString("relationship");
		name = data.getString("name");
	}
	
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return relationship + " > " + name;
	}
}
