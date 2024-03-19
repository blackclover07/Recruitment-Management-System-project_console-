package com.app.DaoInterface;

import java.util.List;

import com.app.entity.Job;

public interface JobDaoInterface {
	
	public void getJobDetailsById(int id);
	public void registerJob(Job job);
	public void jobStatusUpdate(int id ,String status);
	public List<Job> getAllJobeDetails();
	public void deleteJobById(int id);
	public Job returnJobDetailsById(int id);
	

}
