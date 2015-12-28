package com.reveregroup.gwt.facebook4gwt;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A wrapper with methods for interacting with native JavaScript objects and
 * arrays.
 * 
 * @author David Wolverton
 */
public final class JsObject extends JavaScriptObject {
	protected JsObject() {
	};

	public static native JsObject parseJSON(String value) /*-{
		return eval('(' + value + ')');
	}-*/;

	public native void set(String key, Object value) /*-{
		this[key] = value;
	}-*/;

	public void set(String key, Long value) {
		if (value == null)
			set(key, null);
		else
			set(key, parseJSON(value.toString()));
	}

	public void setJSON(String key, String value) {
		set(key, parseJSON(value));
	}

	public native JsObject getJsObject(String key) /*-{
		return this[key];
	}-*/;

	public native Number getNumber(String key) /*-{
		return this[key];
	}-*/;

	public native Integer getInt(String key) /*-{
		return this[key];
	}-*/;

	public native Double getDouble(String key) /*-{
		return this[key];
	}-*/;

	public native Boolean getBoolean(String key) /*-{
		var value = this[key];
		if (value == null)
			return null;
		return value ? true : false;
	}-*/;

	public native String getString(String key) /*-{
		if (typeof this[key] == 'string')
			return this[key];
		else if (this[key])
			return this[key].toString();
		else
			return null;
	}-*/;

	public Long getLong(String key) {
		String s = getString(key);
		if (s == null)
			return null;
		return new Long(s);
	}

	public native JsObject getJsObject(int index) /*-{
		return this[index];
	}-*/;

	public native <T> T get(int index) /*-{
		return this[index];
	}-*/;

	public native String getString(int index) /*-{
		return String( this[index] );
	}-*/;

	public Long getLong(int index) {
		String s = getString(index);
		if (s == null)
			return null;
		return new Long(s);
	}

	public native void push(Object value) /*-{
		this.push(value);
	}-*/;

	public void push(Long value) {
		if (value == null)
			push(null);
		else
			push(parseJSON(value.toString()));
	}

	public void pushJSON(String value) {
		push(parseJSON(value));
	}

	public native int length() /*-{
		return this.length;
	}-*/;

	public String[] getKeys() {
		JsObject keys = getKeysJs();
		String[] result = new String[keys.length()];
		for (int i = 0; i < result.length; i++) {
			result[i] = keys.get(i);
		}
		return result;
	}

	private native JsObject getKeysJs() /*-{
		var array = [];
		for (key in this) {
			array.push(key);
		}
		return array;
	}-*/;
	
	public String[] toStringArray() {
		String[] javaArray = new String[this.length()];
		for (int i = 0; i < javaArray.length; i++) {
			javaArray[i] = this.getString(i);
		}
		return javaArray;
	}

	public native void remove(String key) /*-{
		delete this[key];
	}-*/;
	
	public native void remove(int index) /*-{
		this.splice(index, 1);
	}-*/;


	public static JsObject newArray() {
		return (JsObject) JavaScriptObject.createArray();
	}

	public static JsObject newObject() {
		return (JsObject) JavaScriptObject.createObject();
	}
	
	public static JsObject newArray(Object[] javaArray) {
		JsObject jsArray = newArray();
		for (Object item : javaArray) {
			jsArray.push(item);
		}
		return jsArray;
	}
	
	public static JsObject newArray(Long[] javaArray) {
		JsObject jsArray = newArray();
		for (Long item : javaArray) {
			jsArray.push(item);
		}
		return jsArray;
	}
}
