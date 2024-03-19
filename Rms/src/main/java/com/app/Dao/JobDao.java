package com.app.Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.app.DaoInterface.JobDaoInterface;
import com.app.entity.Candidate;
import com.app.entity.Job;

public class JobDao implements JobDaoInterface{
 private SessionFactory sessionFactory;
	
 
 
	public JobDao(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}


	@Override
	public void getJobDetailsById(int id) {
		Session session= sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			transaction=session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria criteria=session.createCriteria(Job.class);
			criteria.add(Restrictions.eq("jId", id));
			Job job=(Job) criteria.uniqueResult();
			
			
			if(job!=null) {
				System.out.println(job);
			}
			else {
				System.out.println("no job details found against this id ");
			}
			
		}catch(Exception e) {
			if(transaction !=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void registerJob(Job job) {
		Transaction transaction=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(job);
			transaction.commit();
			
			session.close();
			System.out.println("Job Register Successfull");
			
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void jobStatusUpdate(int id ,String status) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			
			Thread.sleep(1000);
			transaction =session.beginTransaction();
			LocalDateTime timeStamp=LocalDateTime.now();
			Query<Job> query=session.createQuery("update Job set job_status=:status ,updatedAt=:date  where jId=:id");
			query.setParameter("status",status);
			query.setParameter("id",id);
			query.setParameter("date", timeStamp);
			query.executeUpdate();
			transaction.commit();
			System.out.println(" Update Job status succesfully ");
			
		}
		catch(Exception e){
			
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Job> getAllJobeDetails()
	{
		Session session=sessionFactory.openSession();
		Query<Job> query=session.createQuery("from Job");
		return query.list();
	}
	
	@Override
public void deleteJobById(int id) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction =null;
		try {
		Thread.sleep(1000);
		Query<Candidate> query = session.createQuery("Delete from Job where jId =:id");
		query.setParameter("id",id);
		transaction=session.beginTransaction();
		int row=query.executeUpdate();
		transaction.commit();
		session.close();
		if(row!=0) {
		System.out.println("delete successfull");
		}
		if(row==0) {
			System.out.println(id +" " +"not exists in the job table");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
public Job returnJobDetailsById(int id) {
	Session session= sessionFactory.openSession();
	Transaction transaction=null;
	
	try {
		transaction=session.beginTransaction();
		
		@SuppressWarnings("deprecation")
		Criteria criteria=session.createCriteria(Job.class);
		criteria.add(Restrictions.eq("jId", id));
		Job job=(Job) criteria.uniqueResult();
		
		
		return job;
		
	}catch(Exception e) {
		if(transaction !=null){
			transaction.rollback();
		}
		e.printStackTrace();
		return null;
	}
	
}
}
