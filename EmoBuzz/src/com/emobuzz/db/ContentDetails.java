package com.emobuzz.db;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@SuppressWarnings("serial")
@PersistenceCapable(detachable = "true")
public class ContentDetails implements Serializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	public final Key getKey() {
		return key;
	}

	public final void setKey(final Key key) {
		this.key = key;
	}

	@Persistent
	private long cid;
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	@Persistent
	private String url;

	public final String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	private long totalFeedbackCount = 0;

	public long getTotalFeedbackCount() {
		return totalFeedbackCount;
	}

	public void setTotalFeedbackCount(long totalFeedbackCount) {
		this.totalFeedbackCount = totalFeedbackCount;
	}



	@Persistent
	private long majorEmoId;

	public long getMajorEmoId() {
		return majorEmoId;
	}

	public void setMajorEmoId(long majorEmoId) {
		this.majorEmoId = majorEmoId;
	}

	@Persistent
	private long majorEmoCount;

	public long getMajorEmoCount() {
		return majorEmoCount;
	}

	public void setMajorEmoCount(long majorEmoCount) {
		this.majorEmoCount = majorEmoCount;
	}

	@Persistent
	private String majorEmoName;

	public String getMajorEmoName() {
		return majorEmoName;
	}

	public void setMajorEmoName(String majorEmoName) {
		this.majorEmoName = majorEmoName;
	}



	@Persistent
	private Text embedData = null;

	public String getEmbedData() {
		return embedData != null ? embedData.getValue() : null;
	}

	public void setEmbedData(String embedData) {
		this.embedData = embedData != null ? new Text(embedData) : null;
	}
}