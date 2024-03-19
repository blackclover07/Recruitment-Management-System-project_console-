package com.app.Dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.app.DaoInterface.CandidateDaoInterface;
import com.app.entity.Candidate;
import com.app.entity.Job;

public class CandidateDao implements CandidateDaoInterface{
	
	private SessionFactory sessionFactory;
	

	public CandidateDao(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}
	
	
//	get  all candidate  details 
	@Override
	public List<Candidate> getAllCandidateDetails()
	{
		Session session=sessionFactory.openSession();
		Query<Candidate> query=session.createQuery("from Candidate");
		return query.list();
	}
	
	
//	get candidate details by id 
	
	@Override
	public void getCandiadteDetailsById(int id)
	{
		Session session=sessionFactory.openSession();
		Query<Candidate> query=session.createQuery("from Candidate where cId=:id");
		query.setParameter("id",id);
		Candidate candidate=(Candidate)query.uniqueResult();
		if(candidate!=null) {
			System.out.format("%-5s  %-20s  %-30s  %-10s  %-50s %-80s %-10s\n" ,"Id "," Name ", "Email" ,"Mobile","Address","Resume","Status");
			System.out.format("%-5s  %-20s  %-30s  %-10s  %-50s %-80s %-10s\n" ,candidate.getcId(),candidate.getName(), candidate.getEmail(),candidate.getMobile(),candidate.getAddress(),candidate.getResume(),candidate.getStatus());
//			System.out.println(" Candidate Id : "+candidate.getcId()+" Name : "+candidate.getName()+" Email : "+candidate.getEmail()+" Address : "+candidate.getAddress()+" Status : "+candidate.getStatus());
		}
		else {
			System.out.println("no candidate found against this id");
		}
		
	}
	
// update the candidate status 
	@Override
	public void candidateStatusUpdate(int id , String status) {
		
		Session session=sessionFactory.openSession();
		LocalDateTime timeStamp=LocalDateTime.now();
		Query<Candidate> query=session.createQuery("update Candidate  set Status =: status ,updateddAt =:date where cId=:id ");
		query.setParameter("id",id);
		query.setParameter("status",status);
		query.setParameter("date",timeStamp);
		Transaction transaction=session.beginTransaction();
		int row=query.executeUpdate();
		transaction.commit();
		
		if(row!=0) {
		System.out.println("Update Succesfull");
		}
		else {
			System.out.println("no records found against id can't Update status");
		}
	}
	
	@Override
public void candidateNameUpdate(int id , String name) {
		
		Session session=sessionFactory.openSession();
		
		Query<Candidate> query=session.createQuery("update Candidate  set name =: name where cId=:id ");
		query.setParameter("id",id);
		query.setParameter("name",name);
		Transaction transaction=session.beginTransaction();
		query.executeUpdate();
		
		transaction.commit();
		System.out.println(" Name Update Succesfull");
	}
	@Override
	public void registerCandidate(Candidate candidate) {
		Transaction transaction=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(candidate);
			transaction.commit();
			
			session.close();
			System.out.println("Registeration Successfull");
			
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void deleteCandidateById(int id) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction =null;
		try {
		Query<Candidate> query = session.createQuery("Delete from Candidate where cId =:id");
		query.setParameter("id",id);
		transaction=session.beginTransaction();
		int row=query.executeUpdate();
		transaction.commit();
		session.close();
		if(row!=0) {
		System.out.println("delete successfull");
		}
		else {
			System.out.println("no records found agaains this id ");
		}
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.commit();
			}
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Candidate returnCandidateDetailsById(int id) {
		Session session= sessionFactory.openSession();
		Transaction transaction=null;
		
		try {
			transaction=session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria criteria=session.createCriteria(Candidate.class);
			criteria.add(Restrictions.eq("cId", id));
			Candidate candidate=(Candidate) criteria.uniqueResult();
			
			
			return candidate ;
			
		}catch(Exception e) {
			if(transaction !=null){
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
public void candidateResumeUpdate(int id , String resume) {
		
		Session session=sessionFactory.openSession();
		
		Query<Candidate> query=session.createQuery("update Candidate  set resume =: link where cId=:id ");
		query.setParameter("id",id);
		query.setParameter("link",resume);
		Transaction transaction=session.beginTransaction();
		query.executeUpdate();
		
		transaction.commit();
		System.out.println(" Resume Update Succesfull");
	}
	
	@Override
public void candidateAddressUpdate(int id , String address) {
		
		Session session=sessionFactory.openSession();
		
		Query<Candidate> query=session.createQuery("update Candidate  set address =: address where cId=:id ");
		query.setParameter("id",id);
		query.setParameter("address",address);
		Transaction transaction=session.beginTransaction();
		query.executeUpdate();
		
		transaction.commit();
		System.out.println(" Address Update Succesfull");
	}
	
	@Override
public void candidateMobileUpdate(int id , long mob) {
		
		Session session=sessionFactory.openSession();
		Transaction transaction=null;
		try {
		Query<Candidate> query=session.createQuery("update Candidate  set mobile =: mob where cId=:id ");
		query.setParameter("id",id);
		query.setParameter("mob",mob);
		transaction=session.beginTransaction();
		query.executeUpdate();
		
		transaction.commit();
		System.out.println(" Mobile no  Update Succesfull");
		}
		catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void getCandidateByEmail(String name, String email) {
	    Session session = sessionFactory.openSession();
	    try {
	        Query<Candidate> criteria = session.createQuery("from Candidate where email=:email and name=:name");
	        criteria.setParameter("name", name);
	        criteria.setParameter("email",email);
	        Candidate candidate = (Candidate) criteria.uniqueResult();
	        if (candidate != null) {
	            System.out.format("%-5s  %-20s  %-40s  %-10s  %-50s %-80s %-10s\n", "Id ", " Name ", "Email", "Mobile", "Address", "Resume", "Status");
	            System.out.format("%-5s  %-20s  %-40s  %-10s  %-50s %-80s %-10s\n", candidate.getcId(), candidate.getName(), candidate.getEmail(), candidate.getMobile(), candidate.getAddress(), candidate.getResume(), candidate.getStatus());
	        } else {
	            System.out.println("No candidate found with the provided name and email.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close(); // Close the session to release resources
	    }
	}


@Override
	public void candidateEmailUpdate(int id,String email) {
		Session session=sessionFactory.openSession();
		Transaction transaction =null;
		try {
			Query<Candidate> query=session.createQuery("update Candidate  set Email_Id =: email where cId=:id ");
			query.setParameter("email", email);
			query.setParameter("id",id);
			transaction=session.beginTransaction();
			int row=query.executeUpdate();
			transaction.commit();
			if(row!=0) {
				System.out.println(" Email update successfull");
			}
			if(row==0) {
				System.out.println(" No records found against  this id");
			}
		}
		catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
}
