package com.reveregroup.gwt.facebook4gwt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * Use this class to post information to the current user's wall. Before making
 * a post, you will need to set up a template. This can be done on Facebook's
 * website: <a
 * href="http://developers.facebook.com/tools.php?feed">http://developers
 * .facebook.com/tools.php?feed</a>. Once that's done, follow these steps to
 * make a post:
 * 
 * <ol>
 * <li>Create an instance of {@link FacebookStory} and set the template bundle
 * id. You should be able to find the this id with the template you created on
 * Facebook's site.
 * <li>Set the content for your post:
 * <ul>
 * <li>Set any key-value pairs of data that your template needs using the
 * {@link #putData(String, String)} method.
 * <li>Add the images, video, mp3 or flash data you want (if any).
 * <li>Set bodyGeneral if desired.
 * </ul>
 * <li>Optionally, configure the dialog with {@link #setPrompt(String)} and
 * {@link #setDefaultUserMessage(String)}.
 * <li>Finally, call {@link #showFeedDialog()}. This will display a dialog
 * window for your user to confirm the message and possibly add their own
 * comments. If they approve, the message will be posted to their wall and to
 * their friends news feeds.
 * </ol>
 * 
 * <p>
 * References:<br/> <a href="http://wiki.developers.facebook.com/index.php/JS_API_M_FB.Connect.ShowFeedDialog"
 * > http://wiki.developers.facebook.com/index.php/JS_API_M_FB.Connect.
 * ShowFeedDialog</a><br/> <a
 * href="http://wiki.developers.facebook.com/index.php/Template_Data">http://
 * wiki.developers.facebook.com/index.php/Template_Data</a>
 * 
 * @author David Wolverton
 * 
 */
public class FacebookStory
{

    public enum RequireConnect
    {
	DO_NOT_REQUIRE("doNotRequire"), REQUIRE("require"), PROMPT_CONNECT("promptConnect");

	RequireConnect(String jsName)
	{
	    this.jsName = jsName;
	}

	private String jsName;

	String getJSName()
	{
	    return jsName;
	}
    }

    private String templateBundleId;

    private String userMessage;

    private List<Image> images = new ArrayList<Image>();

    private Flash flash;

    private MP3 mp3;

    private Video video;

    private Map<String, String> data = new HashMap<String, String>();

    private String actionLinkText;

    private String actionLinkHref;

    private RequireConnect requireConnect;

    private List<Long> targets = new ArrayList<Long>();

    private String bodyGeneral;

    private String prompt;

    private String defaultUserMessage;

    private boolean autoPublish = false;

    private String actorId;

    public FacebookStory()
    {
    }

    public FacebookStory(String templateBundleId)
    {
	this.templateBundleId = templateBundleId;
    }

    public void ui_streamPublish()
    {
	if (Facebook.init())
	    ui_streamPublish(getStreamPublishDataAsJavaScriptObject());
    }

    private native void ui_streamPublish(JavaScriptObject attachment) /*-{
        $wnd.FB.ui(attachment);
    }-*/;

    private static String jsSafe(String s)
    {
	if (s == null)
	    return null;
	else
	    return s.replace("\\", "\\\\").replace("'", "\\'");
    }

    public JavaScriptObject getStreamPublishDataAsJavaScriptObject()
    {
	String streamPublishDataAsJSON = getStreamPublishDataAsJSON();
	return parseJSON(streamPublishDataAsJSON);
    }

    private String getStreamPublishDataAsJSON()
    {
	StringBuilder json = new StringBuilder("{");

	json.append("method:'stream.publish',");

	if (userMessage != null)
	    json.append("message:'").append(jsSafe(userMessage)).append("',");

	String attachment = getAttachment();
	if (attachment != null)
	    json.append("attachment:").append(attachment).append(",");

	String actionLinks = getActionLinks();
	if (actionLinks != null)
	    json.append("action_links:").append(actionLinks).append(",");

	if (prompt != null)
	    json.append("user_message_prompt:'").append(jsSafe(prompt)).append("'");
	else
	    json.deleteCharAt(json.length() - 1);

	json.append("}");

	return json.toString();
    }

    private String getActionLinks()
    {
	if (actionLinkText != null && actionLinkHref != null)
	{
	    StringBuilder json = new StringBuilder("[{");
	    json.append("text:'").append(jsSafe(actionLinkText));
	    json.append("',href:'").append(jsSafe(actionLinkHref));
	    json.append("'}]");
	    return json.toString();
	}
	return null;
    }

    private String getAttachment()
    {
	StringBuilder json = new StringBuilder("{");
	boolean hasData = false;
	for (Map.Entry<String, String> entry : data.entrySet())
	{
	    hasData = true;
	    json.append(jsSafe(entry.getKey())).append(":'").append(jsSafe(entry.getValue())).append("'");
	    json.append(",");
	}

	String media = getMedia();
	if (media != null)
	    json.append("media:" + media);
	else if (hasData)
	    json.deleteCharAt(json.length() - 1);

	json.append("}");
	if (json.length() < 3)
	    return null;
	return json.toString();
    }

    private String getMedia()
    {
	StringBuilder json = new StringBuilder();

	json.append("[");
	if (images.size() > 0)
	{

	    boolean first = true;
	    for (Image image : images)
	    {
		if (first)
		{
		    first = false;
		} else
		{
		    json.append(",");
		}
		json.append("{type:'image'");
		json.append(",src:'").append(jsSafe(image.source));
		json.append("',href:'").append(jsSafe(image.href));
		json.append("'}");
	    }

	}
	if (flash != null)
	{
	    if (json.length() > 10)
		json.append(",");
	    json.append("{type:'flash',swfsrc: '").append(jsSafe(flash.source)).append("',imgsrc: '").append(jsSafe(flash.previewImage)).append("'");
	    if (flash.width != null)
	    {
		json.append(",width:").append("'").append(flash.width).append("'");
	    }
	    if (flash.height != null)
	    {
		json.append(",height:").append("'").append(flash.height).append("'");
	    }
	    if (flash.expandedWidth != null)
	    {
		json.append(",expandedWidth:").append("'").append(flash.expandedWidth).append("'");
	    }
	    if (flash.expandedHeight != null)
	    {
		json.append(",expandedHeight:").append("'").append(flash.expandedHeight).append("'");
	    }
	    json.append("}");
	}
	if (mp3 != null)
	{
	    if (json.length() > 10)
		json.append(",");
	    json.append("{type:'mp3',src:'").append(jsSafe(mp3.source));
	    json.append("',album:'").append(jsSafe(mp3.album));
	    json.append("',title:'").append(jsSafe(mp3.title));
	    json.append("',artist:'").append(jsSafe(mp3.artist));
	    json.append("'}");
	}
	if (video != null)
	{
	    if (json.length() != 1)
		json.append(",");
	    json.append("{type:'video',video_src:'").append(jsSafe(video.source));
	    json.append("',preview_img:'").append(jsSafe(video.previewImage));
	    json.append("'}");
	}
	json.append("]");

	if (json.length() < 3)
	    return null;

	return json.toString();
    }

    public JsArrayString getTargetsAsJSArray()
    {
	if (targets.size() == 0)
	    return null;

	JsArrayString array = JavaScriptObject.createArray().cast();
	int i = 0;
	NumberFormat format = NumberFormat.getFormat("#");
	for (Long target : targets)
	{
	    if (target != null)
		array.set(i++, format.format(target.doubleValue()));
	}
	return array;
    }

    public String getTarget()
    {
	if (targets.size() == 0)
	    return null;
	NumberFormat format = NumberFormat.getFormat("#");

	if (targets.get(0) != null)
	    return format.format(targets.get(0));

	return null;
    }

    private native JavaScriptObject parseJSON(String json) /*-{
        return eval('(' + json + ')');
    }-*/;

    public void putData(String key, String value)
    {
	data.put(key, value);
    }

    public String getData(String key)
    {
	return data.get(key);
    }

    public void removeData(String key)
    {
	data.remove(key);
    }

    public void addImage(String source, String href)
    {
	images.add(new Image(source, href));
    }

    public void addTarget(Long id)
    {
	if (id == null)
	    return;
	targets.add(id);
    }

    public void setVideo(String source, String previewImage)
    {
	video = new Video(source, previewImage);
    }

    public void setMP3(String source, String album, String title, String artist)
    {
	mp3 = new MP3(source, album, title, artist);
    }

    public void setFlash(String source, String previewImage)
    {
	flash = new Flash(source, previewImage);
    }

    public void setFlash(String source, String previewImage, Integer width, Integer height)
    {
	flash = new Flash(source, previewImage, width, height);
    }

    public void setFlash(String source, String previewImage, Integer width, Integer height, Integer expandedWidth, Integer expandedHeight)
    {
	flash = new Flash(source, previewImage, width, height, expandedWidth, expandedHeight);
    }

    public Flash getFlash()
    {
	return flash;
    }

    public void setFlash(Flash flash)
    {
	this.flash = flash;
    }

    public MP3 getMP3()
    {
	return mp3;
    }

    public void setMP3(MP3 mp3)
    {
	this.mp3 = mp3;
    }

    public String getTemplateBundleId()
    {
	return templateBundleId;
    }

    public void setTemplateBundleId(String templateBundleId)
    {
	this.templateBundleId = templateBundleId;
    }

    public RequireConnect getRequireConnect()
    {
	return requireConnect;
    }

    public void setRequireConnect(RequireConnect requireConnect)
    {
	this.requireConnect = requireConnect;
    }

    public List<Long> getTargets()
    {
	return targets;
    }

    public void setTargets(List<Long> targets)
    {
	this.targets = targets;
    }

    public String getBodyGeneral()
    {
	return bodyGeneral;
    }

    public void setBodyGeneral(String bodyGeneral)
    {
	this.bodyGeneral = bodyGeneral;
    }

    public String getPrompt()
    {
	return prompt;
    }

    public void setPrompt(String prompt)
    {
	this.prompt = prompt;
    }

    public List<Image> getImages()
    {
	return images;
    }

    public void setImages(List<Image> images)
    {
	this.images = images;
    }

    public Video getVideo()
    {
	return video;
    }

    public void setVideo(Video video)
    {
	this.video = video;
    }

    public String getDefaultUserMessage()
    {
	return defaultUserMessage;
    }

    public void setDefaultUserMessage(String defaultUserMessage)
    {
	this.defaultUserMessage = defaultUserMessage;
    }

    public static class Image
    {
	public Image()
	{
	}

	public Image(String source, String href)
	{
	    this.source = source;
	    this.href = href;
	}

	public String source;

	public String href;
    }

    public static class Flash
    {
	public Flash()
	{
	}

	public Flash(String source, String previewImage)
	{
	    this.source = source;
	    this.previewImage = previewImage;
	}

	public Flash(String source, String previewImage, Integer width, Integer height)
	{
	    this.source = source;
	    this.previewImage = previewImage;
	    this.width = width;
	    this.height = height;
	}

	public Flash(String source, String previewImage, Integer width, Integer height, Integer expandedHeight, Integer expandedWidth)
	{
	    this.source = source;
	    this.previewImage = previewImage;
	    this.width = width;
	    this.height = height;
	    this.expandedWidth = expandedWidth;
	    this.expandedHeight = expandedHeight;
	}

	public String source;

	public String previewImage;

	public Integer width;

	public Integer height;

	public Integer expandedWidth;

	public Integer expandedHeight;
    }

    public static class MP3
    {
	public MP3()
	{
	}

	public MP3(String source, String album, String title, String artist)
	{
	    this.source = source;
	    this.album = album;
	    this.title = title;
	    this.artist = artist;
	}

	public String source;

	public String album;

	public String title;

	public String artist;
    }

    public static class Video
    {
	public Video()
	{
	}

	public Video(String source, String previewImage)
	{
	    this.source = source;
	    this.previewImage = previewImage;
	}

	public String source;

	public String previewImage;
    }

    public String getUserMessage()
    {
	return userMessage;
    }

    public void setUserMessage(String userMessage)
    {
	this.userMessage = userMessage;
    }

    public boolean isAutoPublish()
    {
	return autoPublish;
    }

    public void setAutoPublish(boolean autoPublish)
    {
	this.autoPublish = autoPublish;
    }

    //    public JsFunction getCallBackFunction()
    //    {
    //	return callBackFunction;
    //    }
    //
    //    public void setCallBackFunction(JsFunction callBackFunction)
    //    {
    //	this.callBackFunction = callBackFunction;
    //    }

    public String getActorId()
    {
	return actorId;
    }

    public void setActorId(String actorId)
    {
	this.actorId = actorId;
    }

    public String getActionLinkText()
    {
	return actionLinkText;
    }

    public void setActionLinkText(String actionLinkText)
    {
	this.actionLinkText = actionLinkText;
    }

    public String getActionLinkHref()
    {
	return actionLinkHref;
    }

    public void setActionLinkHref(String actionLinkHref)
    {
	this.actionLinkHref = actionLinkHref;
    }
}
