package com.emobuzz.dto;

import java.io.Serializable;
import java.util.Date;

public class ContentEmotionsMapDTO implements Serializable {

	private long contentEmotionMapId;
	public long getContentEmotionMapId() {
		return contentEmotionMapId;
	}
	public void setContentEmotionMapId(long contentEmotionMapId) {
		this.contentEmotionMapId = contentEmotionMapId;
	}

	
	private long cid;
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}

	private long eid;
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}

	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	private long totalEmotionCount = 0L;
	public long getTotalEmotionCount() {
		return totalEmotionCount;
	}
	public void setTotalEmotionCount(long totalEmotionCount) {
		this.totalEmotionCount = totalEmotionCount;
	}


	private String emotionName;
	public String getEmotionName() {
		return emotionName;
	}
	public void setEmotionName(String emotionName) {
		this.emotionName = emotionName;
	}
}