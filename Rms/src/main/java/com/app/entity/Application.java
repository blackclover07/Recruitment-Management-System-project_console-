package com.app.entity;

import java.time.LocalDateTime;


import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "Application")
public class Application {
	
	
	public Application( String status, Candidate candidate, Job job) {
		super();
		this.status = status;
		this.candidate = candidate;
		this.job = job;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private int aId; 
	
	
	@CreationTimestamp
	@Column(name = "Application_date")
	private LocalDateTime application_date;
	
	
	@Column(name = "Application_status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name =" Candidate_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Candidate candidate;
	
	
	@ManyToOne
	@JoinColumn(name =" Job_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Job job;

	
	
	public Application() {
	
		// TODO Auto-generated constructor stub
	}


	public int getaId() {
		return aId;
	}


	public void setaId(int aId) {
		this.aId = aId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Candidate getCandidate() {
		return candidate;
	}


	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	public LocalDateTime getApplication_date() {
		return application_date;
	}


	public void setApplication_date(LocalDateTime application_date) {
		this.application_date = application_date;
	}


	@Override
	public String toString() {
		return "Application [aId=" + aId + ", application_date=" + application_date + ", status=" + status
				+ ", candidate=" + candidate.getName() + ", job=" + job.getTitle() + "]";
	}
	

	
	
	

}
