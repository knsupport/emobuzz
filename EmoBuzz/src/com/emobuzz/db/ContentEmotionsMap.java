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
public class ContentEmotionsMap implements Serializable {

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
	private long contentEmotionMapId;

	public long getContentEmotionMapId() {
		return contentEmotionMapId;
	}

	public void setContentEmotionMapId(long contentEmotionMapId) {
		this.contentEmotionMapId = contentEmotionMapId;
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
	private long eid;

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
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
	private long totalEmotionCount = 0L;

	public long getTotalEmotionCount() {
		return totalEmotionCount;
	}

	public void setTotalEmotionCount(long totalEmotionCount) {
		this.totalEmotionCount = totalEmotionCount;
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