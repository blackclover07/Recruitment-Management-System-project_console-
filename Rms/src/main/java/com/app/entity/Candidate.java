package com.app.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "Candidates")
public class Candidate {
	
	public Candidate(String name, String email, long mobile, String address, String resume) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.resume = resume;
		//this.status = status;
	}
	
	
	public Candidate() {
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	
	@Column(name = "Candidate_Name")
	private String name;
	
	@Column (name ="Email_Id")
	private String email;
	
	@Column(name = "Mobile_number",length=10)
	private Long mobile;
	


	@Column(name ="Address")
	private String address;
	
	@Column(name="Resume")
	private String resume;
	
	@Column(name ="Status")
	private String status;
	
	
	@CreationTimestamp
	@Column(name="CreatedAt",nullable=false,updatable=false)
	private  LocalDateTime createdAt;
	
	
	@UpdateTimestamp
	@Column(name="UpdatedAt",nullable=false)
	private  LocalDateTime updateddAt;
	
	
	
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdateddAt() {
		return updateddAt;
	}


	public void setUpdateddAt(LocalDateTime updateddAt) {
		this.updateddAt = updateddAt;
	}

	@Override
	public String toString() {
		return "Candidate [cId=" + cId + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", address="
				+ address + ", resume=" + resume + ", status=" + status + ", createdAt=" + createdAt + ", updateddAt="
				+ updateddAt + "]";
	}
	
	
    
	//private  LocalDateTime create_at;
    
	//private LocalDateTime updated_at;

	

}
