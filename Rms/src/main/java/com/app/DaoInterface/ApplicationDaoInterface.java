package com.app.DaoInterface;

import java.util.List;

import com.app.entity.Application;

public interface ApplicationDaoInterface {
	
	public void ApplicationStatusUpdate(int id ,String status);
	public List<Application> getAllApplicationDetails();
	public void getApplicationDetailsById(int id);
	public void registerApplication(Application application);
	public void deleteApplicationById(int id);
	public List<Application> getApplicationByJob(String jobName);
	public List<Application> getApplicationByCandidate(String candidateName);
	public void applyApplicationById(int candidateId,int jobId);
	

}
