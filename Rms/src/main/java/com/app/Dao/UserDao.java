package com.app.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;

import com.app.entity.Application;
import com.app.entity.Candidate;
import com.app.entity.Interview;
import com.app.entity.Job;
import com.app.users.UserRole;
import com.app.users.Users;


public class UserDao {
	Scanner sc;
	Scanner space=new Scanner(System.in);
	private SessionFactory sessionFactory;
	CandidateDao cdao;
	JobDao jdao;
	ApplicationDao aDao;
	InterviewDao iDao;
	public UserDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		this.cdao=new CandidateDao(sessionFactory);
		this.jdao=new JobDao(sessionFactory);
		this.aDao=new ApplicationDao(sessionFactory);
		this.iDao=new InterviewDao(sessionFactory);
	}
	
	
	public void addUser(Users user) {
		Session session=sessionFactory.openSession();
		
		Transaction transaction =null;
		
		try {
			transaction =session.beginTransaction();
			session.save(user);
			transaction.commit();
			System.out.println(" User register successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void login() {
		sc=new Scanner(System.in);
		System.out.println("Enter UserName");
		String username=sc.nextLine();
		System.out.println("Enter Password");
		String password=sc.nextLine();
		
		Users user=authenticateUser(username,password);
	
		if(user!=null) {
			System.out.println("login successfull");
			if(user.getRole()==UserRole.ADMIN) {
				System.out.println("Welcome Admin");
			adminLogged2();
			}
			else if(user.getRole()==UserRole.RECUITER) {
				System.out.println(" Welcome Recruiter ");
				recruiterLogged();
				
			}
			else if(user.getRole()==UserRole.CANDIDATE) {
				candidateLogged();
			}
			else {
				System.out.println(" Opps !! it seems that higher authority cant set your role ");
				System.out.println("Please wait until you can get the role");
			}
		}
		else {
			System.out.println("invalid user");
		}
	}
	
	public Users authenticateUser(String username, String password) {
	    try (Session session = sessionFactory.openSession()) {
	        Users user = (Users) session.createCriteria(Users.class)
	                                   .add(Restrictions.eq("userName", username))
	                                   .add(Restrictions.eq("password", password))
	                                   .uniqueResult();
	        return user ;// Return true if user is not null
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
public void deleteUserById(int id) {
		
		Session session =sessionFactory.openSession();
		Transaction transaction =null;
		Query<Users> query = session.createQuery("Delete from Users where id =:id");
		query.setParameter("id",id);
		transaction=session.beginTransaction();
		query.executeUpdate();
		transaction.commit();
		session.close();
		System.out.println("delete user successfull");
		
	}


//public void adminLogged() {
//	int choice =0;
//	Scanner sc=new Scanner(System.in);
//	boolean exit=false;
//	boolean cexit=false;
//	
//	while(exit==false) {
//		System.out.println(" 1.Show candidate details");
//		System.out.println(" 2.Show all candidate details");
//		System.out.println(" 3.Exit");
//		System.out.println(" Enter your choice ");
//		choice=sc.nextInt();
//		switch(choice) {
//			case 1:
//				System.out.println(" Enter candidate id :");
//				int id=sc.nextInt();
//				cdao.getCandiadteDetailsById(id);
//				break;
//			case 2:
//				System.out.println(" Candidate Details ");
//				List<Candidate> candidates=cdao.getAllCandidateDetails();
//				System.out.format("%-5s  %-20s  %-30s  %-10s  %-50s %-80s %-10s\n" ,"Id "," Name ", "Email" ,"Mobile","Address","Resume","Status");
//				for(Candidate c: candidates) {
//					System.out.format("%-5s  %-20s  %-30s  %-10s  %-50s %-80s %-10s\n" ,c.getcId(),c.getName(), c.getEmail(),c.getMobile(),c.getAddress(),c.getResume(),c.getStatus());
//				}
//				break; 
//			case 3:
//				System.out.println("Bye !! exiting ");
//				exit=true;
//				break;
//			default:
//				System.out.println("not a valid input ");
//				break;
//		}
//	}
//}

public void jobSection() {
		int choice=0;
		boolean jobExit=false;
		Scanner sc=new Scanner (System.in);
		System.out.println(" Choose options to perform Operation ");
		while(jobExit==false) {
			System.out.println(" 1.Show Job details");
			System.out.println(" 2.Show all Jobs posts");
			System.out.println(" 3.Job Status Update");
			System.out.println(" 4.Delete Job ");
			System.out.println(" 5.Exit");
			System.out.println(" Enter your choice ");
			choice=sc.nextInt();
				switch(choice) {
				case 1:
					System.out.println(" Enter Job Id : ");
					int jid=sc.nextInt();
					jdao.getJobDetailsById(jid);
					break;
				case 2:
					System.out.println(" Printing Job posts");
					List<Job> jobList=jdao.getAllJobeDetails();
					System.out.format("%-5s %-20s %-60s %-50s %-70s %-30s %-30s %-30s %-30s\n","Id ","Title","Description","Requirements","Location","Salary","Status","Created At","Updated At");
					for(Job job:jobList) {
						System.out.format("%-5s %-20s %-60s %-50s %-70s %-30s %-30s %-30s %-30s\n",job.getjId(),job.getTitle(),job.getDescription(),job.getRequirements(),job.getLocation(),job.getSalary(),job.getStatus(),job.getCreatedAt(),job.getUpdatedAt());
					}
					break;
				case 3:
					System.out.println("Enter Job Id : ");
					int id=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter status : ");
					String status=sc.nextLine();
					jdao.jobStatusUpdate(id, status);
					break;
				case 4:
					System.out.println("Enter Job Id : ");
					int did=sc.nextInt();
					jdao.deleteJobById(did);
					break;
				case 5:
					System.out.println("Exitting from job section");
					jobExit=true;
					break;
					
				default:
					System.out.println(" I think some misunderstanding here , please  enter a valid choice");
					break;
				}
		}
}


public void applicationSection() {
	int choice=0;
	boolean appExit=false;
	Scanner sc=new Scanner (System.in);
	System.out.println(" Choose options to perform Operation ");
	
	while(appExit==false) {
		System.out.println(" 1.Show Application details");
		System.out.println(" 2.Show all Application posts");
		System.out.println(" 3.Application Status Update");
		System.out.println(" 4.Delete Application ");
		System.out.println(" 5.Exit");
		System.out.println(" Enter your choice ");
		choice=sc.nextInt();
		
		switch(choice) {
			case 1:
				System.out.println("Enter  Application Id : ");
				int aid=sc.nextInt();
				aDao.getApplicationDetailsById(aid);
				break;
			case 2:
				System.out.println(" Printing all Application details");
				List<Application> applications=aDao.getAllApplicationDetails();
				System.out.format("%-5s %-50s %-30s %-30s %-30s\n","Id","Created At","Status","Candidate Name","Job Name");
				for(Application application:applications) {
					System.out.format("%-5s %-50s %-30s %-30s %-30s\n",application.getaId(),application.getApplication_date(),application.getStatus(),application.getCandidate().getName(),application.getJob().getTitle());
				}
				break;
			case 3:
				System.out.println("Enter Application Id : ");
				int sid=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Status : ");
				String status=sc.nextLine();
				aDao.ApplicationStatusUpdate(sid, status);
				break;
			case 4:
				System.out.println("Enter Application Id : ");
				int did=sc.nextInt();
				aDao.deleteApplicationById(did);
				break;
			case 5:
				System.out.println("Bye !! exitting from Application Section");
				appExit=true;
				break;
			default:
				System.out.println("I think some misunderstanding here , please  enter a valid choice");
				break;
		}
	}
	
}
public void showAllUsers() {
	Session session=sessionFactory.openSession();
	try {
		Thread.sleep(1000);
	Query<Users> query=session.createQuery("from Users");
	List<Users> userList=query.list();
	
	System.out.format("%-5s %-10s %-10s %-10s\n","Id","User Name","Password","Role");
	for(Users u:userList) {
		System.out.format("%-5s %-10s %-10s %-10s\n",u.getId(),u.getUserName(),u.getPassword(),u.getRole());
	}
	
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

public Users getUserById(int id) {
	Session session=sessionFactory.openSession();
	try {
		Thread.sleep(1000);
		Query<Users> query=session.createQuery("from Users where id=:id");
		query.setParameter("id", id);
		Users user=query.uniqueResult();
		return user;
	}
	catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}
public void createUser() {
	Scanner sc=new Scanner(System.in);
	int role=0;
	Session session=sessionFactory.openSession();
	Transaction transaction=session.beginTransaction();
	Users user=new Users();
	System.out.println(" Select Role from below");
	System.out.println(" 1.ADMIN");
	System.out.println(" 2.RECRUITER");
	System.out.println(" 3.CANDIDATE");
	System.out.println("Enter choice");
	role=sc.nextInt();
	sc.nextLine();
	switch(role) {
		case 1:
			user.setRole(UserRole.ADMIN);
			break;
			
		case 2:
			user.setRole(UserRole.RECUITER);
			break;
		case 3:
			user.setRole(UserRole.CANDIDATE);
			break;
		default:
			System.out.println("Enter a valid choice ");
			break;	
	}
	System.out.println("Enter UserName : ");
	String username=sc.nextLine();
	
	System.out.println("Enter Password :");
	String password=sc.nextLine();
	user.setUserName(username);
	user.setPassword(password);
	session.save(user);
	transaction.commit();
	System.out.println("Create  User successfull");
}

public void interviewSection() {
	int choice=0;
	boolean appExit=false;
	Scanner sc=new Scanner (System.in);
	System.out.println(" Choose options to perform Operation ");
	
	while(appExit==false) {
		System.out.println(" 1.Show Interview details");
		System.out.println(" 2.Show all Interview posts");
		System.out.println(" 3.Interview Type Update");
		System.out.println(" 4.Delete Interview ");
		System.out.println(" 5.Exit");
		System.out.println(" Enter your choice ");
		choice=sc.nextInt();
		
		switch(choice) {
			case 1:
				System.out.println("Enter  Interview Id : ");
				int id=sc.nextInt();
				iDao.getInterviewDetailsById(id);
				break;
			case 2:
				System.out.println(" Printing all Interview details");
				List<Interview> interviews=iDao.getAllInterviewList();
				System.out.format("%-5s %-20s %-50s %-30s %-30s\n","Id","Interview Date","Feedback","Interviewer","Type");
				for(Interview interview:interviews) {
					System.out.format("%-5s %-20s %-50s %-30s %-30s\n",interview.getiId(),interview.getDate(),interview.getFeedback(),interview.getInterviewer(),interview.getType());
				}
				break;
			case 3:
				System.out.println("Enter Application Id : ");
				int sid=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Type : ");
				String type=sc.nextLine();
				iDao.updateInterviewType(sid, type);
				break;
			case 4:
				System.out.println("Enter Interview Id : ");
				int did=sc.nextInt();
				iDao.deleteInterviewById(did);
				break;
			case 5:
				System.out.println("Bye !! exitting from Application Section");
				appExit=true;
				break;
			default:
				System.out.println("I think some misunderstanding here , please  enter a valid choice");
				break;
		}
	}
	
}

public void updateUserRole() {
	Session session=sessionFactory.openSession();
	Transaction transaction=null;
	try {
	transaction=session.beginTransaction();
	Scanner sc=new Scanner(System.in);
	int choice=0;
	System.out.println("Enter User Id");
	int id=sc.nextInt();
	sc.nextLine();
	System.out.println(" 1.ADMIN");
	System.out.println(" 2.RECRUITER");
	System.out.println(" 3.CANDIDATE");
	System.out.println("Enter Choice : ");
	choice=sc.nextInt();
	Users user=getUserById(id);
	switch(choice) {
	 	case 1:
	 		user.setRole(UserRole.ADMIN);
	 		break;
	 	case 2:
	 		user.setRole(UserRole.RECUITER);
	 		break;
	 	case 3:
	 		user.setRole(UserRole.CANDIDATE);
	 		break;
	 	default:
	 		System.out.println("invalid choice");
	}
	session.update(user);
	transaction.commit();
	System.out.println("update User Role successfull");
	} catch(Exception e) {
		if(transaction!=null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
	finally {
		session.close();
	}
}

public void recruiterLogged() {
	Scanner sChoice=new Scanner(System.in);
	Scanner sc=new Scanner(System.in);
	Scanner newSc=new Scanner(System.in);
	int choice =0;
	boolean recExit=false;
	
	
	while(recExit==false) {
		System.out.println(" 1.Show Interview Details");
		System.out.println(" 2.Show All Interviews");
		System.out.println(" 3.Register Interview");
		System.out.println(" 4.Update interview Type");
		System.out.println(" 5.Update Interview Feedback");
		System.out.println(" 6.Delete Interview");
		System.out.println(" 7.Get my Interviews");
		System.out.println(" 8.Update Interview Date");
		System.out.println(" 9.Update Candidate Status");
		System.out.println(" 10.Delete Candidate");
		System.out.println(" 11.Show All Candidates");
		System.out.println(" 12.Register Job");
		System.out.println(" 13.See all Jobs ");
		System.out.println(" 14.Update Job Status");
		System.out.println(" 15.Serch Job Details");
		System.out.println(" 16.Delete Job ");
		System.out.println(" 17.Exit");
		System.out.println("Enter choice :");
	    choice=sChoice.nextInt();
	    
	     switch(choice) {
	     	case 1:
	     		System.out.println("Enter Interview Id :");
	     		int id=sc.nextInt();
	     		iDao.getInterviewDetailsById(id);
	     		break;
	     	case 2:
	     		System.out.println("Printing Interview Details");
	     		List<Interview> interviews=iDao.getAllInterviewList();
	     		System.out.format("%-5s %-20s %-20s %-20s %-20s  %-30s %-50s\n","Id","Date of Interview","Interviwer","Type","Candidate Name","Job Name","Feedback");
	     		for(Interview interview:interviews) {
	     			System.out.format("%-5s %-20s %-20s %-20s %-20s  %-30s %-50s\n",interview.getiId(),interview.getDate(),interview.getInterviewer(),interview.getType(),interview.getCandidate().getName(),interview.getJob().getTitle(),interview.getFeedback());
	     		}
	     		break;
	     	case 3:
	     		System.out.println("Enter Interview Date : ");
	     		String date=sc.nextLine();
	     		System.out.println("Enter Mode : ");
	     		String mode =sc.nextLine();
	     		System.out.println("Enter Interviwer Name : ");
	     		String interviewer=sc.nextLine();
	     		System.out.println("Enter Candidate Id : ");
	     		int cid=newSc.nextInt();
	     		System.out.println("Enter Job Id : ");
	     		int jid=newSc.nextInt();
	     		iDao.registerInterview(date, mode, interviewer.trim(), cid, jid);
	     		break;
	     	case 4:
	     		System.out.println("Enter Interview Id : ");
	     		int iId=newSc.nextInt();
	     		System.out.println("Enter Mode : ");
	     		String newMode =sc.nextLine();
	     		iDao.updateInterviewType(iId, newMode);
	     		break;
	     	case 5:
	     		System.out.println("Enter Intterview Id : ");
	     		int iid=newSc.nextInt();
	     		System.out.println("Enter Feedback : ");
	     		String feedback=sc.nextLine();
	     		iDao.updateInterviewFeedback(iid, feedback);
	     		break;
	     	case 6:
	     		System.out.println("Enter Interview Id : ");
	     		int iID=newSc.nextInt();
	     		iDao.deleteInterviewById(iID);
	     		break;
	     	case 7:
	     		System.out.println("Enter Interviewer Name : ");
	     		String iName=newSc.nextLine();
	     		List<Interview> interviewList= iDao.getInterviewlistByName(iName);
	     		System.out.format("%-5s %-20s %-20s %-20s %-20s  %-30s %-50s\n","Id","Date of Interview","Interviwer","Type","Candidate Name","Job Name","Feedback");
	     		for(Interview interview:interviewList) {
	     			System.out.format("%-5s %-20s %-20s %-20s %-20s  %-30s %-50s\n",interview.getiId(),interview.getDate(),interview.getInterviewer(),interview.getType(),interview.getCandidate().getName(),interview.getJob().getTitle(),interview.getFeedback());
	     		}
	     		break;
	     	case 8:
	     		System.out.println("Enter Interview Id : ");
	     		int interviewId=newSc.nextInt();
	     		System.out.println("Enter Interview Date :");
	     		String interviewdate=sc.nextLine();
	     		iDao.interviewDateUpdate(interviewId, interviewdate);
	     		break;
	     	case 9:
	     		System.out.println("Enter Candidate Id : ");
	     		int canid=newSc.nextInt();
	     		System.out.println("Enter Status : ");
	     		String candStat=sc.nextLine();
	     		cdao.candidateStatusUpdate(canid, candStat);
	     		break;
	     	case  10:
	     		System.out.println("Enter Candidate Id :");
	     		int candId=newSc.nextInt();
	     		cdao.deleteCandidateById(candId);
	     		break;
	     	case 11:
	     		System.out.println("printing all Candidate details");
	     		List<Candidate>candList=cdao.getAllCandidateDetails();
	     		System.out.format("%-5s  %-20s  %-40s  %-10s  %-50s %-80s %-10s\n" ,"Id "," Name ", "Email" ,"Mobile","Address","Resume","Status");
	     		for(Candidate candidate : candList) {
	     			System.out.format("%-5s  %-20s  %-40s  %-10s  %-50s %-80s %-10s\n" ,candidate.getcId(),candidate.getName(), candidate.getEmail(),candidate.getMobile(),candidate.getAddress(),candidate.getResume(),candidate.getStatus());
	     		}
	     		break;
	     	case 12:
	     		System.out.println("Enter Job Title : ");
	     		String title=sc.nextLine();
	     		System.out.println("Enter Job Description : ");
	     		String desc=sc.nextLine();
	     		System.out.println("Enter Job Requierments : ");
	     		String req=sc.nextLine();
	     		System.out.println("Enter Location : ");
	     		String location=sc.nextLine();
	     		System.out.println("Enter Salary : ");
	     		String salary =sc.nextLine();
	     		System.out.println("Enter Status : ");
	     		String jStatus=sc.nextLine();
	     		Job job=new Job();
	     		job.setTitle(title.trim());
	     		job.setDescription(desc.trim());
	     		job.setRequirements(req.trim());
	     		job.setLocation(location.trim());
	     		job.setSalary(salary.trim());
	     		job.setStatus(jStatus.trim());
	     		jdao.registerJob(job);
	     		break;
	     	case 13:
	     		System.out.println(" Printing Job posts");
				List<Job> jobList=jdao.getAllJobeDetails();
				System.out.format("%-5s %-20s %-60s %-50s %-70s %-30s %-30s %-30s %-30s\n","Id ","Title","Description","Requirements","Location","Salary","Status","Created At","Updated At");
				for(Job jobs:jobList) {
					System.out.format("%-5s %-20s %-60s %-50s %-70s %-30s %-30s %-30s %-30s\n",jobs.getjId(),jobs.getTitle(),jobs.getDescription(),jobs.getRequirements(),jobs.getLocation(),jobs.getSalary(),jobs.getStatus(),jobs.getCreatedAt(),jobs.getUpdatedAt());
				}
	     		break;
	     	case 14:
	     		System.out.println("Enter Job Id : ");
	     		int jobId=newSc.nextInt();
	     		sc.nextLine();
	     		System.out.println("Enter Status : ");
	     		String jobStat=sc.nextLine();
	     		jdao.jobStatusUpdate(jobId, jobStat);
	     		break;
	     	case 15:
	     		System.out.println("Enter Job Id : ");
	     		int jobID=newSc.nextInt();
	     		System.out.println();
	     		Job jobDetails=jdao.returnJobDetailsById(jobID);
	     		if(jobDetails!=null) {
	     		System.out.format("%-5s %-20s %-60s %-50s %-70s %-30s %-30s %-30s %-30s\n","Id ","Title","Description","Requirements","Location","Salary","Status","Created At","Updated At");
	     		System.out.format("%-5s %-20s %-60s %-50s %-70s %-30s %-30s %-30s %-30s\n",jobDetails.getjId(),jobDetails.getTitle(),jobDetails.getDescription(),jobDetails.getRequirements(),jobDetails.getLocation(),jobDetails.getSalary(),jobDetails.getStatus(),jobDetails.getCreatedAt(),jobDetails.getUpdatedAt());
	     		}
	     		else {
	     			System.out.println("Wrong Jod Id");
	     		}
	     		break;
	     	case 16:
	     		System.out.println("Enter Job Id : ");
	     		int joBId=newSc.nextInt();
	     		jdao.deleteJobById(joBId);
	     		break;
	     	case 17:
	     		System.out.println("bye !!! exitting from recruiter");
	     		recExit=true;
	     		break;
	     	default:
	     		System.out.println(" enter a valid choice");
	     		
	     }

	}
}

public void candidateLogged() {
	Scanner scanner=new Scanner(System.in);
	Scanner newSc=new Scanner(System.in);
	Scanner sc=new Scanner(System.in);
	Scanner log= new Scanner(System.in);
	int choice =0;
	boolean candExit=false;
	System.out.println(" Welcome Candidate ");
	
	while(candExit==false) {
		System.out.println(" 1.Show Candidate Details");
		System.out.println(" 2.Update Name");
		System.out.println(" 3.Update Email");
		System.out.println(" 4.Update Address");
		System.out.println(" 5.Update Resume");
		System.out.println(" 6.See Job posts");
		System.out.println(" 7.Apply for Job");
		System.out.println(" 8.Get my Applications");
		System.out.println(" 9.Exit");
		System.out.println("Enter choice :");
	    choice=sc.nextInt();
	    	switch(choice) {
	    	case 1:
	    		try {
	    		System.out.println("Enter your Name :");
	    		String name=newSc.nextLine().trim();
	    		sc.nextLine();
	    		System.out.println("Enter your Email :");
	    		String email= sc.nextLine().trim();
	    		System.out.println(name+email);
	    		cdao.getCandidateByEmail(name,email);
	    		}catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    		break;
	    	case 2:
	    		System.out.println("Enter Candidate Id :");
	    		int cid=log.nextInt();
	    		sc.nextLine();
	    		System.out.println("Enter new Name : ");
	    		String newName=sc.nextLine();
	    		cdao.candidateNameUpdate(cid ,newName.trim());
	    		break;
	    	case 3:
	    		System.out.println("Enter Candidate Id : ");
	    		int id=log.nextInt();
	    		sc.nextLine();
	    		System.out.println("Enter new Email : ");
	    		String newEmail=sc.nextLine();
	    		cdao.candidateEmailUpdate(id, newEmail);
	    		break;
	    	case 4:
	    		System.out.println("Enter Candidate Id : ");
	    		int cId=log.nextInt();
	    		sc.nextLine();
	    		System.out.println("Enter new Address : ");
	    		String newadd=sc.nextLine();
	    		cdao.candidateAddressUpdate(cId, newadd);
	    		break;
	    	case 5:
	    		System.out.println("Enter Candidate Id : ");
	    		int ciD=log.nextInt();
	    		sc.nextLine();
	    		System.out.println("Enter new Resume Link : ");
	    		String newlink=sc.nextLine();
	    		cdao.candidateResumeUpdate(ciD, newlink);
	    		break;
	    	case 6:
	    		List<Job> jobList=jdao.getAllJobeDetails();
	    		System.out.println(" Printing Job posts ");
	    		System.out.format("%-5s %-20s %-40s %-40s %-20s %-10s %-10s\n","Id ","Title","Description","Requirements","Location","Salary","Status");
	    		for(Job j:jobList) {
	    			System.out.format("%-5s %-20s %-40s %-40s %-20s %-10s %-10s\n",j.getjId(),j.getTitle(),j.getDescription(),j.getRequirements(),j.getLocation(),j.getSalary(),j.getStatus());
	    		}
	    		break;
	    	case 7:
	    		System.out.println("Enter your Candidate Id :");
	    		int canId=sc.nextInt();
	    		sc.nextLine();
	    		System.out.println("Enter Job Id :");
	    		int jId=sc.nextInt();
	    		aDao.applyApplicationById(canId, jId);
	    		break;
	    		
	    	case 8:
	    		System.out.println("Enter your Name :");
	    		String canName=scanner.nextLine();
	    		List<Application> appList=aDao.getApplicationByCandidate(canName);
	    		System.out.format("%-5s %-30s %-10s %-20s %-20s\n","Id","Created At","Status","Candidate Name","Job Name");
	    		for(Application app:appList) {
	    			System.out.format("%-5s %-30s %-10s %-20s %-20s\n",app.getaId(),app.getApplication_date(),app.getStatus(),app.getCandidate().getName(),app.getJob().getTitle());
	    		}
	    		break;
	    			
	    	case 9:
	    		System.out.println("Bye !! exitting from candidate ");
	    		candExit=true;
	    		break;
	    	default:
	    		System.out.println(" Opps !! you choose the wrong input ");
	    	}
		
		
	}
}

public void registerLogged() {
//	candidate have to give username and password
	Scanner sc =new Scanner (System.in);
	Scanner log=new Scanner(System.in); 
	try {
		System.out.println(" Welcome User  , At first you need to create an account for register ");
		System.out.println("Enter User Name : ");
		String username=sc.nextLine();
		System.out.println("Enter Password");
		String password=sc.nextLine();
		Users user=new Users();
		user.setUserName(username.trim());
		user.setPassword(password.trim());
		addUser(user);
		
	System.out.println(" By registration you are register as a candidate but you dont able to access the Candidate  level method until recruiter give you the permission");
	System.out.println("Enter your Name : ");
	String name=sc.nextLine();
	System.out.println("Enter you Email : ");
	String email=sc.nextLine();
	System.out.println("Enter Mobile No : ");
	long mob=log.nextLong();
	System.out.println("Enter your Address");
	String address=sc.nextLine();
	System.out.println("Enter your Resume Link : ");
	String link=sc.nextLine();
	
	
	Candidate  candidate=new Candidate();
	candidate.setName(name.trim());
	candidate.setEmail(email.trim());
	candidate.setMobile(mob);
	candidate.setAddress(address.trim());
	candidate.setResume(link);
	cdao.registerCandidate(candidate);
	}catch(Exception e) {
		
		e.printStackTrace();
	}


}
public void adminLogged2() {
	int choice =0;
	Scanner sc=new  Scanner(System.in);
	Scanner scIn=new Scanner(System.in);
	boolean sectionExit=false;
	boolean candidateExit=false;
	
	while(sectionExit==false) {
		System.out.println(" To Choose Section you want to perform operation");
		System.out.println("");
		System.out.println("");
		System.out.println(" 1. Candidate ");
		System.out.println(" 2. Job");
		System.out.println(" 3. Application ");
		System.out.println(" 4. Interview");
		System.out.println(" 5.Create User");
		System.out.println(" 6.Update User Role");
		System.out.println(" 7.Show all Users");
		System.out.println(" 8.Delete User");
		System.out.println(" 9. Exit");
		
		System.out.println(" Enter choice : ");
		choice=sc.nextInt();
			switch(choice) {
				case 1:
					System.out.println("\nWelcome to Candidate Section");
					while(candidateExit==false) {
						System.out.println(" 1.Show candidate details");
						System.out.println(" 2.Show all candidate details");
						System.out.println(" 3.Candidate Status Update");
						System.out.println(" 4.Delete Candidate");
						System.out.println(" 5.Exit");
						System.out.println(" Enter your choice ");
						choice=sc.nextInt();
						switch(choice) {
							case 1:
								System.out.println(" Enter Candidate id :");
								int id=sc.nextInt();
								cdao.getCandiadteDetailsById(id);
								break;
							case 2:
								System.out.println(" Candidate Details ");
								List<Candidate> candidates=cdao.getAllCandidateDetails();
								System.out.format("%-5s  %-20s  %-40s  %-10s  %-50s %-80s %-10s %-40s %-40s\n" ,"Id "," Name ", "Email" ,"Mobile","Address","Resume","Status","Created At","Updated At");
								for(Candidate c: candidates) {
									System.out.format("%-5s  %-20s  %-40s  %-10s  %-50s %-80s %-10s %-40s %-40s\n" ,c.getcId(),c.getName(), c.getEmail(),c.getMobile(),c.getAddress(),c.getResume(),c.getStatus(),c.getCreatedAt(),c.getUpdateddAt());
								}
								break; 
							case 3:
								System.out.println(" Enter Candidate Id : ");
								int cid=scIn.nextInt();
								scIn.nextLine();
								System.out.println(" Enter Status : ");
								String status=scIn.nextLine();
								cdao.candidateStatusUpdate(cid, status);
									break;
							case 4:
								System.out.println("Enter Canndidate Id : ");
								int did=scIn.nextInt();
								cdao.deleteCandidateById(did);
								break;
								
							case 5:
								System.out.println("Bye !! exiting from Candidate section");
								candidateExit=true;
								break;
								
							default:
								System.out.println("not a valid input ");
								break;
						}
					}
					break;
				case 2:
					System.out.println(" \nWelcome to Job Section");
					jobSection();
					break;
				case 3:
					System.out.println("\nWelcome to Application Section");
					applicationSection();
					break;
				case 4:
					System.out.println("\nWelcome to Interview Section");
					interviewSection();
					break;
				case 5:
					createUser();
					break;
				case 6:
					updateUserRole();
					break;
				case 7:
					System.out.println("Users Details ");
					showAllUsers();
					break;
				case 8:
					System.out.println("Enter User Id : ");
					int uId=sc.nextInt();
					deleteUserById(uId);
					
				case 9:
					System.out.println("Bye !!  exitting from progaramme ");
					sectionExit=true;
					break;
					
			}
	
	
	
	}
}

}
