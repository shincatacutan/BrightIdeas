package com.optum.operations.momlibrary.model;

public class User {
	private String lanID;
	private String firstName;
	private String lastName;
	private String access;
	private String manager;
	public String getLanID() {
		return lanID;
	}
	public void setLanID(String lanID) {
		this.lanID = lanID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
}
