package com.app.Entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Application")
public class Applications_Entity {
	
	
		 @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		 private int Application_Id;
		 
		 @ManyToOne
		 @JoinColumn(name = "Candidates_ID")
		 private Candidate_Entity candidate;
		 
		 @ManyToOne
		 @JoinColumn(name = "Job_ID")
		 private  Jobs job;
		 
		 private Date Application_Date;
		 private String Status;
		 private String Notes;
		 private Date created_at;
		 private Date updated_at; 

}
