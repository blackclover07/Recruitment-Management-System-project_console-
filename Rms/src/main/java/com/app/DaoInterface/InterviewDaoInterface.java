package com.app.DaoInterface;

import java.util.List;

import com.app.entity.Interview;

public interface InterviewDaoInterface {
	
	public void getInterviewDetailsById(int id);
	public void interviewDateUpdate(int id ,String date);
	public void updateInterviewFeedback(int id ,String feedback);
	public void deleteInterviewById(int id);
	public void registerInterview(String date,String type,String interviewer,int candidateId,int jobId);
	public List<Interview> getInterviewlistByName(String interviewerName);
	public List<Interview>  getAllInterviewList();
	public void updateInterviewType(int id,String type);

}
