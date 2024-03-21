package com.app.Entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name=" Interview")
public class Interviews_Entity {
	 
		 @Id
		@GeneratedValue(strategy = GenerationType.AUTO)

		 private int Interview_Id;
		 
		 @OneToOne
		 @JoinColumn(name = "Application_Id")
		 private Applications_Entity application;
		 private Date Interview_Date;
		 private String interview_Type;
		 private String Interviewer_name;
		 private String Feedback;
		 private Date created_at;
		 private Date updated_at;
		 
	 }

