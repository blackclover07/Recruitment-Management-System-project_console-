package com.app.Dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.app.DaoInterface.ApplicationDaoInterface;
import com.app.entity.Application;
import com.app.entity.Candidate;
import com.app.entity.Job;

public class ApplicationDao implements ApplicationDaoInterface{
	private SessionFactory sessionFactory;
	
	private CandidateDao cDao; 
	private JobDao jDao;
	public ApplicationDao(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		this.cDao=new CandidateDao(sessionFactory);
		this.jDao=new JobDao(sessionFactory);
	}
@Override	
public void ApplicationStatusUpdate(int id ,String status) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			
			Thread.sleep(1000);
			transaction =session.beginTransaction();
			Query<Job> query=session.createQuery("update Application set Application_status=:status where application_id=:id");
			query.setParameter("status",status);
			query.setParameter("id",id);
			int row=query.executeUpdate();
			transaction.commit();
			
			if(row!=0) {
			System.out.println(" Application status succesfully update ");
			}
			if(row==0) {
				System.out.println("No application found against this id ");
			}
			
		}
		catch(Exception e){
			
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

@Override
	public List<Application> getAllApplicationDetails()
	{
		Session session=sessionFactory.openSession();
		Query<Application> query=session.createQuery("from Application");
		return query.list();
	}

	
@Override
	public void getApplicationDetailsById(int id) {
		Session session= sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			transaction=session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria criteria=session.createCriteria(Application.class);
			criteria.add(Restrictions.eq("aId", id));
			Application application=(Application) criteria.uniqueResult();
			
			
			if(application!=null) {
				System.out.format("%-5s %-50s %-30s %-30s %-30s\n","Id","Created At","Status","Candidate Name","Job Name");
				System.out.format("%-5s %-50s %-30s %-30s %-30s\n",application.getaId(),application.getApplication_date(),application.getStatus(),application.getCandidate().getName(),application.getJob().getTitle());
			}
			else {
				System.out.println("no Application  details found against this id ");
			}
			
		}catch(Exception e) {
			if(transaction !=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

@Override
	public void registerApplication(Application application) {
		Transaction transaction=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(application);
			transaction.commit();
			
			session.close();
			System.out.println("application Register Successfull");
			
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		
	}
	
	@Override
public void deleteApplicationById(int id) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction =null;
		Query<Candidate> query = session.createQuery("Delete from Application where aId =:id");
		query.setParameter("id",id);
		transaction=session.beginTransaction();
		query.executeUpdate();
		transaction.commit();
		session.close();
		System.out.println("delete successfull");
		
	}
	@Override
	public List<Application> getApplicationByJob(String jobName){
		try (Session session =sessionFactory.openSession();){
			Thread.sleep(1000);
		
		Criteria criteria=session.createCriteria(Application.class)
				.createAlias("job", "j")
				.add(Restrictions.eq("j.title", jobName));
		return criteria.list();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public List<Application> getApplicationByCandidate(String candidateName) {
		try(Session session =sessionFactory.openSession();) {
			
			 Criteria criteria = session.createCriteria(Application.class)
	                    .createAlias("candidate", "c")
	                    .add(Restrictions.eq("c.name", candidateName));
	            return criteria.list();
	
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void applyApplicationById(int candidateId,int jobId) {
		Transaction transaction=null;
		
		try {
			Thread.sleep(1000);
			Session session=sessionFactory.openSession();
			Candidate candidate=cDao.returnCandidateDetailsById(candidateId);
			Job job=jDao.returnJobDetailsById(jobId);
			Application application=new Application(null,candidate,job);
			transaction=session.beginTransaction();
			session.save(application);
			transaction.commit();
			
			session.close();
			System.out.println("application Register Successfull");
			
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		
	}
	
}
