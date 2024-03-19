package com.app.entity;

import javax.persistence.*;


import org.hibernate.annotations.Cascade;
@Entity
@Table(name = "Interview")
public class Interview {
	

	@Override
	public String toString() {
		return "Interview [iId=" + iId + ", date=" + date + ", type=" + type + ", interviewer=" + interviewer
				+ ", feedback=" + feedback + ", candidate=" + candidate.getName() + ", job=" + job.getTitle() + "]";
	}


	public Interview() {
		
		// TODO Auto-generated constructor stub
	}


	public Interview(String date, String type, String interviewer, String feedback, Candidate candidate, Job job) {
		super();
		this.date = date;
		this.type = type;
		this.interviewer = interviewer;
		this.feedback = feedback;
		this.candidate = candidate;
		this.job = job;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Interview_Id")
	private int iId;
	@Column(name = "interview_Date")
	private String date;
	@Column(name = "interview_Type")
	private String type ;
	@Column(name = "interviewer_Name")
	private String interviewer;
	@Column(name = "interview_Feedback")
	private String feedback;
	
	@ManyToOne
	@JoinColumn(name =" Candidate_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Candidate candidate;
	
	
	@ManyToOne
	@JoinColumn(name =" Job_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Job job;


	public int getiId() {
		return iId;
	}


	public void setiId(int iId) {
		this.iId = iId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getInterviewer() {
		return interviewer;
	}


	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
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


}
