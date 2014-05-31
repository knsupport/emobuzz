package com.emobuzz.db;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
@PersistenceCapable
public class Followers implements Serializable {

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
	private long fId;

	public long getfId() {
		return fId;
	}

	public void setfId(long fId) {
		this.fId = fId;
	}

	@Persistent
	private long uid;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	@Persistent
	private long followerId;
	public long getFollowerId() {
		return followerId;
	}
	public void setFollowerId(long followerId) {
		this.followerId = followerId;
	}


}
