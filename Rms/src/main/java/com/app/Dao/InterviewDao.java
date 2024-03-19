package com.app.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.app.DaoInterface.InterviewDaoInterface;
import com.app.entity.Application;
import com.app.entity.Candidate;
import com.app.entity.Interview;
import com.app.entity.Job;

public class InterviewDao implements InterviewDaoInterface {
	
	private SessionFactory sessionFactory;
	private CandidateDao cDao;
	private JobDao jDao;

	public InterviewDao(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		this.cDao=new CandidateDao(sessionFactory);
		this.jDao=new JobDao(sessionFactory);
	}
	
	@Override
	public void getInterviewDetailsById(int id) {
		Session session= sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			transaction=session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria criteria=session.createCriteria(Interview.class);
			criteria.add(Restrictions.eq("iId", id));
			Interview interview=(Interview) criteria.uniqueResult();
			
			if(interview!=null) {
				System.out.format("%-5s %-20s %-20s %-20s %-20s  %-30s %-50s\n","Id","Date of Interview","Interviwer","Type","Candidate Name","Job Name","Feedback");
				System.out.format("%-5s %-20s %-20s %-20s %-20s  %-30s %-50s\n",interview.getiId(),interview.getDate(),interview.getInterviewer(),interview.getType(),interview.getCandidate().getName(),interview.getJob().getTitle(),interview.getFeedback());
//				System.out.println(interview);
			}
			else {
				System.out.println("no interview  details found against this id ");
			}
			
		}catch(Exception e) {
			if(transaction !=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	@Override
	public void updateInterviewType(int id,String type) {
		Session session =sessionFactory.openSession();
		Transaction transaction=null;
		try {
			Thread.sleep(1000);
			transaction=session.beginTransaction();
			Query<Interview> query=session.createQuery("update Interview set interview_Type=:mode where Interview_Id=:id");
			query.setParameter("id",id);
			query.setParameter("mode",type);
			query.executeUpdate();
			transaction.commit();
			System.out.println("Update interview type succesfull");
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	@Override
	public List<Interview>  getAllInterviewList(){
		Session session=sessionFactory.openSession();
		Query<Interview> query=session.createQuery("from Interview");
		return query.list();
	}
	@Override
public void interviewDateUpdate(int id ,String date) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			
			Thread.sleep(1000);
			transaction =session.beginTransaction();
			Query<Interview> query=session.createQuery("update Interview set interview_Date =:date where Interview_Id=:id");
			query.setParameter("date",date);
			query.setParameter("id",id);
			query.executeUpdate();
			transaction.commit();
			System.out.println(" update interview date status succesfully ");
			
		}
		catch(Exception e){
			
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	@Override
public void updateInterviewFeedback(int id ,String feedback) {
	
	Session session =sessionFactory.openSession();
	Transaction transaction=null;
	
	try {
		
		Thread.sleep(1000);
		transaction =session.beginTransaction();
		Query<Interview> query=session.createQuery("update Interview set feedback =:feedbac where iId=:id");
		query.setParameter("feedbac",feedback);
		query.setParameter("id",id);
		query.executeUpdate();
		transaction.commit();
		System.out.println(" update interview feedback succesfully ");
		
	}
	catch(Exception e){
		
		if(transaction!=null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
}


@Override
public void deleteInterviewById(int id) {
	
	Session session =sessionFactory.openSession();
	Transaction transaction =null;
	Query<Candidate> query = session.createQuery("Delete from Interview where iId =:id");
	query.setParameter("id",id);
	transaction=session.beginTransaction();
	query.executeUpdate();
	transaction.commit();
	session.close();
	System.out.println("delete successfull");

	
}

@Override
public void registerInterview(String date,String type,String interviewer,int candidateId,int jobId){
	Transaction transaction=null;
	Session session=sessionFactory.openSession();
	
	try {
		transaction=session.beginTransaction();
		Candidate candidate=cDao.returnCandidateDetailsById(candidateId);
		Job job=jDao.returnJobDetailsById(jobId);
		Interview interview=new Interview(date,type,interviewer,null,candidate,job);
		session.save(interview);
		transaction.commit();
		
		session.close();
		System.out.println("interview Register Successfull");
		
	}catch(Exception e) {
		if(transaction!=null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
	finally {
		session.close();
	}
}
@Override
public List<Interview> getInterviewlistByName(String interviewerName){
	Transaction transaction =null;
	Session session=sessionFactory.openSession();
	
	try {
		transaction=session.beginTransaction();
		Query<Interview> query=session.createQuery("from Interview where interviewer=:name");
		query.setParameter("name",interviewerName);
		return query.list();
		
				
	}catch(Exception e) {
		if(transaction!=null) {
			transaction.rollback();
		}
		e.printStackTrace();
		return null;
	}
	finally {
		session.close();
	}
}


	
}
