package com.app.Entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Job")
public class Jobs {
	
		 @Id
		 @GeneratedValue(strategy = GenerationType.AUTO)
		private int Job_id;
		private String Job_title;
		private String Description; 
		private String Requirements;
		private String Location;
		private Long 	salary;
		private String status;
		private Date created_at;
		private Date updated_at;
		
	}


