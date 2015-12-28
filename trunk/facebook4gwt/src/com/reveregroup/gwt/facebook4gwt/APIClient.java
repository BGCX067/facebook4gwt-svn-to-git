package com.reveregroup.gwt.facebook4gwt;

import java.util.List;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.reveregroup.gwt.facebook4gwt.user.FacebookUser;
import com.reveregroup.gwt.facebook4gwt.user.UserField;

/**
 * This is a GWT wrapper for the standard Facebook API. (NOTE: do not use this
 * in server-side code.) This class can only be accessed through
 * {@link Facebook#APIClient()}.
 * 
 * <p>
 * To read more about the Facebook API that we're wrapping see:
 * <ul>
 * <li><a href="http://wiki.developers.facebook.com/index.php/API">http://wiki.
 * developers.facebook.com/index.php/API</a> - The core API documentation
 * <li><a
 * href="http://wiki.developers.facebook.com/index.php/JS_API_T_FB.ApiClient"
 * >http://wiki.developers.facebook.com/index.php/JS_API_T_FB.ApiClient</a> -
 * The JavaScript API client that we are actually wrapping.
 * </ul>
 * 
 * @author David Wolverton
 */
public class APIClient {

	APIClient() {
	}

	/**
	 * <p>
	 * Get information about the currently logged in user. Use the fields params
	 * to specify what information you will need.
	 * 
	 * <p>
	 * This is an asynchronous call. The results will be returned to the
	 * callback.
	 */
	public void users_getLoggedInUser(AsyncCallback<FacebookUser> callback, UserField... fields) {
		JsObject uids = JsObject.newArray();
		uids.push(Facebook.getLoggedInUserId());

		users_getInfo(uids, UserField.convertFields(fields), callback, false);
	}

	/**
	 * <p>
	 * Get information about the currently logged in user. Use the fields
	 * parameters to specify what information you will need.
	 * 
	 * <p>
	 * This is an asynchronous call. The results will be returned to the
	 * callback.
	 */
	public void users_getInfo(String userId, AsyncCallback<FacebookUser> callback, UserField... fields) {
		JsObject uids = JsObject.newArray();
		uids.push(userId);

		users_getInfo(uids, UserField.convertFields(fields), callback, false);
	}

	/**
	 * <p>
	 * Get information about the specified users. Use the fields parameters to
	 * specify what information you will need.
	 * 
	 * <p>
	 * This is an asynchronous call. The results will be returned to the
	 * callback.
	 */
	public void users_getInfo(String[] userIds, AsyncCallback<FacebookUser[]> callback, UserField... fields) {
		JsObject uids = JsObject.newArray();
		for (String userId : userIds) {
			uids.push(userId);
		}

		users_getInfo(uids, UserField.convertFields(fields), callback, true);
	}

	/**
	 * <p>
	 * Get information about the specified user. Use the fields parameters to
	 * specify what information you will need.
	 * 
	 * <p>
	 * This is an asynchronous call. The results will be returned to the
	 * callback.
	 */
	public void users_getInfo(List<String> userIds, AsyncCallback<FacebookUser[]> callback, UserField... fields) {
		JsObject uids = JsObject.newArray();
		for (String userId : userIds) {
			uids.push(userId);
		}

		users_getInfo(uids, UserField.convertFields(fields), callback, true);
	}

	@SuppressWarnings("unchecked")
	private native void users_getInfo(JavaScriptObject uids, JavaScriptObject fields, AsyncCallback callback,
			boolean multi) /*-{
		var self = this;
		$wnd.FB_RequireFeatures(["Api"], function(){
			$wnd.FB.Facebook.apiClient.users_getInfo(uids, fields, function(result, exception) {
				if (result)
					self.@com.reveregroup.gwt.facebook4gwt.APIClient::users_getInfo_success(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JsArray;Z)(callback, result, multi);
				else
					self.@com.reveregroup.gwt.facebook4gwt.APIClient::exception_callback(Lcom/google/gwt/user/client/rpc/AsyncCallback;Ljava/lang/String;)(callback, 'Facebook error ' + exception.error_code + ': ' + exception.error_msg);
			});
		});
	}-*/;

	@SuppressWarnings( { "unchecked" })
	private void users_getInfo_success(AsyncCallback callback, JsArray<JavaScriptObject> result, boolean multi) {
		FacebookUser[] array = new FacebookUser[result.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = new FacebookUser(result.get(i));
		}

		if (callback != null)
			callback.onSuccess(multi ? array : array[0]);
	}

	@SuppressWarnings( { "unchecked" })
	private void exception_callback(AsyncCallback callback, String message) {
		if (callback != null)
			callback.onFailure(new Exception(message));
	}
	
	public void friends_get(AsyncCallback<String[]> callback) {
		friends_get(null, callback);
	}
	
	private native void friends_get(String flid, AsyncCallback<String[]> callback) /*-{
		var self = this;
		$wnd.FB_RequireFeatures(["Api"], function(){
			$wnd.FB.Facebook.apiClient.friends_get(flid, function(result, exception) {
				if (result)
					self.@com.reveregroup.gwt.facebook4gwt.APIClient::friends_get_success(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/reveregroup/gwt/facebook4gwt/JsObject;)(callback, result);
				else
					self.@com.reveregroup.gwt.facebook4gwt.APIClient::exception_callback(Lcom/google/gwt/user/client/rpc/AsyncCallback;Ljava/lang/String;)(callback, 'Facebook error ' + exception.error_code + ': ' + exception.error_msg);
			});
		});
	}-*/;
	
	private void friends_get_success(AsyncCallback<String[]> callback, JsObject result) {
		String[] array = new String[result.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = result.getString(i);
		}

		if (callback != null)
			callback.onSuccess(array);		
	}
	
}
