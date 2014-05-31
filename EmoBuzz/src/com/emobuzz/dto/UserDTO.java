package com.emobuzz.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	private long uid;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}

	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}