package com.uhg.optum.ssmo.otnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name = "Payroll_Details")
public class Payroll_Details {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "networkID")
	private Employee empId;
	
	@OneToOne
	@JoinColumn(name = "id")
	private IncomeType incomeType;
	
	@Column(name = "prodHrsAmt")
	private String prodHrsAmt;

	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "createDate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate createDate;
	
	@OneToOne
	@JoinColumn(name = "id")
	private PayrollPeriod payrollPeriod;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmpId() {
		return empId;
	}
	public void setEmpId(Employee empId) {
		this.empId = empId;
	}
	public IncomeType getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}
	public String getProdHrsAmt() {
		return prodHrsAmt;
	}
	public void setProdHrsAmt(String prodHrsAmt) {
		this.prodHrsAmt = prodHrsAmt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public PayrollPeriod getPayrollPeriod() {
		return payrollPeriod;
	}
	public void setPayrollPeriod(PayrollPeriod payrollPeriod) {
		this.payrollPeriod = payrollPeriod;
	}
	
	

}
