
package com.app.Entities;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name ="Candidates")
public class Candidate_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Candidates_ID;
	private String First_name;
	private String Last_Name;
	private String Email;
	@Column(length =10)
	private int Mobile;
	private String Address;
	private String Resume;
	private String Status;
	private Date Create_at;
	private Date Update_at;

}





 