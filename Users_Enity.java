package com.app.Entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class Users_Enity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)

		 private int User_Id;
		 private String UserName;
		 private String Password;
		 private String Email_Id;
		 private String Roll;
		 private Date Create_at;
		 private Date Update_at;
	 }

