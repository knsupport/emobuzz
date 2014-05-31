package com.emobuzz.dto;

import java.util.Date;

public class EmotionTagDTO {

	
	private long emotionTagId;
	public long getEmotionTagId() {
		return emotionTagId;
	}
	public void setEmotionTagId(long emotionTagId) {
		this.emotionTagId = emotionTagId;
	}

	private long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	private long contentId;
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	private long emoId;
	public long getEmoId() {
		return emoId;
	}
	public void setEmoId(long emoId) {
		this.emoId = emoId;
	}

	private int categoryId;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	private String titleToLowerCase;
	public String getTitleToLowerCase() {
		return titleToLowerCase;
	}
	public void setTitleToLowerCase(String titleToLowerCase) {
		this.titleToLowerCase = titleToLowerCase;
	}

	private String contentUrl;
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}


	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	private String emotionName;
	public String getEmotionName() {
		return emotionName;
	}
	public void setEmotionName(String emotionName) {
		this.emotionName = emotionName;
	}
}
