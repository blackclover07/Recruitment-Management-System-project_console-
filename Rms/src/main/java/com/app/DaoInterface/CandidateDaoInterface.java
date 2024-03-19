package com.app.DaoInterface;

import java.util.List;

import com.app.entity.Candidate;

public interface CandidateDaoInterface {
	
	public List<Candidate> getAllCandidateDetails();
	public void getCandiadteDetailsById(int id);
	public void candidateStatusUpdate(int id , String status);
	public void registerCandidate(Candidate candidate);
	public void deleteCandidateById(int id);
	public Candidate returnCandidateDetailsById(int id);
	public void candidateNameUpdate(int id , String name);
	public void candidateResumeUpdate(int id , String resume);
	public void candidateAddressUpdate(int id , String address);
	public void candidateMobileUpdate(int id , long mob);
	public void candidateEmailUpdate(int id,String email);
	public void getCandidateByEmail(String name,String email);

}
