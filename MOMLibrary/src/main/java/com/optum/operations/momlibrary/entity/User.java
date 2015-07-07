package com.optum.operations.momlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@Column(name = "userID")
	private String empID;
	@Column(name = "userName", nullable = false)
	private String lanID;
	@Column(name = "firstName", nullable = false)
	private String firstName;
	@Column(name = "lastName", nullable = false)
	private String lastName;
	@Column(name = "roleType", nullable = false)
	private String access;

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

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
}
