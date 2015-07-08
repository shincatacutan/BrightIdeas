package com.optum.operations.momlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_")
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

	@OneToOne
	@JoinColumn(name = "roleType")
	private Role access;

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

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public Role getAccess() {
		return access;
	}

	public void setAccess(Role access) {
		this.access = access;
	}
}
