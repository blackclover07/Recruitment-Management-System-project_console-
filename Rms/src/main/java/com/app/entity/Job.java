package com.app.entity;

//import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;



//import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Jobs")
public class Job{
	
	
	public Job(String title, String description, String requirements, String location, String salary, String status) {
		super();
		this.title = title;
		this.description = description;
		this.requirements = requirements;
		this.location = location;
		this.salary = salary;
		this.status = status;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jId;
	
	@Column(name = "job_tittle")
	private String title;
	
	@Column(name = "job_description")
	private String description;
	
	@Column(name = "job_requirements")
	private String requirements;
	
	@Column(name = "job_location")
	private String location;
	
	@Column(name = "job_salary")
	private String salary;
	
	@Column(name = "job_status")
	private String status;
	
	
	
	@CreationTimestamp
	@Column(name="CreatedAt",nullable=false , updatable=false )
	private LocalDateTime createdAt;
	
	
	@UpdateTimestamp
	@Column(name="UpdatedAt",nullable=false)
	private LocalDateTime updatedAt;
	
	
	
	
//	getters and setters 
	
	public Job() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Job [jId=" + jId + ", title=" + title + ", description=" + description + ", requirements="
				+ requirements + ", location=" + location + ", salary=" + salary + ", status=" + status + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
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
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
   /*
	private Date create_at;
	
	private Date updated_at;
	*/
}
