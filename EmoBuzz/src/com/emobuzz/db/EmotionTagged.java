package com.emobuzz.db;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
@PersistenceCapable(detachable = "true")
public class EmotionTagged implements Serializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@Persistent
	private long emotionTagId;
	public long getEmotionTagId() {
		return emotionTagId;
	}
	public void setEmotionTagId(long emotionTagId) {
		this.emotionTagId = emotionTagId;
	}

	@Persistent
	private long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	@Persistent
	private long contentId;
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	@Persistent
	private long emoId;
	public long getEmoId() {
		return emoId;
	}
	public void setEmoId(long emoId) {
		this.emoId = emoId;
	}

	@Persistent
	private int categoryId;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	@Persistent
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Persistent
	private String titleToLowerCase;
	public String getTitleToLowerCase() {
		return titleToLowerCase;
	}
	public void setTitleToLowerCase(String titleToLowerCase) {
		this.titleToLowerCase = titleToLowerCase;
	}

	@Persistent
	private String contentUrl;
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}


	@Persistent
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


	@Persistent
	private String emotionName;

	public String getEmotionName() {
		return emotionName;
	}

	public void setEmotionName(String emotionName) {
		this.emotionName = emotionName;
	}
}
