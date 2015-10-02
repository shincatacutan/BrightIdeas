package com.optum.operations.momlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name = "InquiryReply")
public class InquiryReply {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inquiryId")
	private Inquiry inqId;
	
	@Column(name = "reply", nullable = false)
	private String reply;
	
	@Column(name = "replyDate", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate replyDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Inquiry getInqId() {
		return inqId;
	}

	public void setInqId(Inquiry inqId) {
		this.inqId = inqId;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public LocalDate getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(LocalDate replyDate) {
		this.replyDate = replyDate;
	}

	
}
