package com.emobuzz.dto;

import java.io.Serializable;

import com.google.appengine.api.datastore.Text;

public class ContentDetailsDTO implements Serializable{
	
	private long cid;
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	private String url;

	public final String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private long totalFeedbackCount = 0;

	public long getTotalFeedbackCount() {
		return totalFeedbackCount;
	}

	public void setTotalFeedbackCount(long totalFeedbackCount) {
		this.totalFeedbackCount = totalFeedbackCount;
	}



	private long majorEmoId;
	public long getMajorEmoId() {
		return majorEmoId;
	}
	public void setMajorEmoId(long majorEmoId) {
		this.majorEmoId = majorEmoId;
	}

	
	private long majorEmoCount;
	public long getMajorEmoCount() {
		return majorEmoCount;
	}
	public void setMajorEmoCount(long majorEmoCount) {
		this.majorEmoCount = majorEmoCount;
	}

	
	private String majorEmoName;
	public String getMajorEmoName() {
		return majorEmoName;
	}
	public void setMajorEmoName(String majorEmoName) {
		this.majorEmoName = majorEmoName;
	}

	private String embedData = null;
	public String getEmbedData() {
		return embedData;
	}

	public void setEmbedData(String embedData) {
		this.embedData = embedData;
	}
	
}
