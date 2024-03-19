package com.app.Main;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.app.Dao.ApplicationDao;
import com.app.Dao.CandidateDao;
import com.app.Dao.InterviewDao;
import com.app.Dao.JobDao;
import com.app.Dao.UserDao;
import com.app.entity.*;
import com.app.users.UserRole;
import com.app.users.Users;


public class Main {

	public static void main(String[] args) {
		
		Configuration config = new Configuration();
		config.configure("Hibernate.rms.config.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		
		
		Transaction transaction =session.beginTransaction();
//		Candidate can0 = new Candidate("Subir Mohapatra ","subirmohakur554@gmail.com",8207225625L,"51/2,Jema long sarani, kolkata-700008","https://drive.google.com/file/d/1N2szsDR85dakLIsLkcVEtimrnJUktWpS/view");
//		
//		session.save(can0);
//		transaction.commit();
//		
		
//		Candidate can1 = new Candidate("Subir Mohapatra ","subirmohakur554@gmail.com",8207225625L,"51/2,Jema long sarani, kolkata-700008","https://drive.google.com/file/d/1N2szsDR85dakLIsLkcVEtimrnJUktWpS/view");
//		Candidate can2 = new Candidate("Mamoni sahoo ","sahoomamoni@gmail.com",8812357892L,"Netaji rorad, Barisha, Bangalore - 500694","https://drive.google.com/file/d/1N2szsDR85dakLIsLkcVEtimrnJUktWpS/view");
//		Candidate can3 = new Candidate(" Raghav Roy","royraghav235@gmail.com",6245158536L,"22/6, Rampurhut, Barwan, west bengal","https://drive.google.com/file/d/1N2szsDR85dakLIsLkcVEtimrnJUktWpS/view");
//
//		Job jb = new Job("Cyber Security"," Cloud security"," Solid knowledge of Sql injection","Kolkata","12000","Open");
//		Job jb1 = new Job("Sofware Engineer"," website maintanance"," Accurate knowledge of web","Delhi","14000","Close");
//		Job jb2 = new Job("Cloud computing"," Cloud management"," Deep understading of Cloud","Kanpur","27000","open");
//		Job jb3 = new Job("Database Engineer"," Database managing"," Soild knowledge of Database","Kolkata","12000","Open");
		
		
//		Application app1 = new Application("Completed",can1,jb1);
//		Application app2 = new Application("Rejected",can2,jb2);
//		Application app3 = new Application("under process",can3,jb3);
//		Application app4 = new Application("under process",can3,jb2);
//		
//		Interview in1 = new Interview("11-06-2024","Offline","Mamata Dinda"," Modarate performane ",can1,jb1);
//		Interview in2= new Interview("26-02-2024","Online","Sudip Pal"," Extremlly happy ",can2,jb2);
//		Interview in3 = new Interview("05-06-2024","Offline","Santu Malakar"," Bad performance lake of understanding  ",can3,jb3);
////		Transaction tran = session.beginTransaction();
//		session.save(can1);
//		session.save(can2);
//		session.save(can3);
//		
//		session.save(jb1);
//		session.save(jb2);
//		session.save(jb3);
//		
//		session.save(app1);
//		session.save(app2);
//		session.save(app3);
//		session.save(app4);
//		session.save(in1);
//		session.save(in2);
//		session.save(in3);
//		transaction.commit();
//		tran.commit();
//		System.out.println(app1.getCandidate().getName());
//		

//		Candidate cand=new Candidate("zaid ahmed ","subirmohakur554@gmail.com",8207225625L,"51/2,Jema long sarani, kolkata-700008","https://drive.google.com/file/d/1N2szsDR85dakLIsLkcVEtimrnJUktWpS/view");
//				
//		CandidateDao impl=new CandidateDao(sf);	
//		impl.registerCandidate(cand);
//		Candidate cand2=new Candidate();
//		
//		impl.registerCandidate(cand2);
//		List<Candidate> list=impl.getAllCandidateDetails();
//		for(Candidate o:list) {
//			System.out.println(o);
//		}
//		JobDao jmpl=new JobDao(sf);
//		jmpl.getJobDetailsById(2);
//		Job j=new Job();
//		
//		jmpl.registerJob(j);
//		jmpl.jobStatusUpdate(4,"closed");
//		List<Job> job=jmpl.getAllJobeDetails();
//		job.forEach(jobs ->{
//			System.out.println(jobs);
//		});
//		
//		jmpl.deleteJobById(11);
//		
//		ApplicationDao ampl=new ApplicationDao(sf);
//		ampl.ApplicationStatusUpdate(7,"recruited");
//		List<Application> applist=ampl.getAllApplicationDetails();
//		
//		applist.forEach(application->{
//			System.out.println(application);
//		});
//		Users user=new Users();
//		user.setUserName("monjur");
//		user.setPassword("203");
//		user.setRole(UserRole.CANDIDATE);
//		 UserDao uDao=new UserDao(sf);
//		 uDao.addUser(user);
//		 
		 
//		CandidateDao cdao=new CandidateDao(sf);
//		Candidate canddate=cdao.returnCandidateDetailsById(2);
//		ampl.getApplicationDetailsById(2);
//		JobDao jmpl=new JobDao(sf);
//		Application app=new Application();
//		Job job=jmpl.returnJobDetailsById(2);
//		app.setStatus("gfh");
//		app.setJob(job);
//		app.setCandidate(canddate);
//		
//		
//		ampl.registerApplication(app);
		
//		ampl.deleteApplicationById(1);
//		InterviewDao impl=new InterviewDao(sf);
//		impl.getInterviewDetailsById(3);
//		impl.interviewDateUpdate(2,"kgfhhhh");
//		impl.updateInterviewFeedback(2, "hgdfzugiufuogig");
//		impl.deleteInterviewById(1);
		
//		Interview interview=new Interview();
//		
//		impl.registerInterview(interview);
		
//		CandidateDao cdao=new CandidateDao(sf);
//		JobDao jdao=new JobDao(sf);
//		jdao.jobStatusUpdate(3, "vacanny opened");
//		ApplicationDao adao=new ApplicationDao(sf);
//		adao.getApplicationDetailsById(2);
	
//		List<Application> list=adao.getApplicationByCandidate("Raghav Roy");
//		for(Application j:list) {
//			System.out.println(j);
//		}
//		InterviewDao iDao=new InterviewDao(sf);
////		iDao.registerInterview("2024-03-18","offline","shubhankar", 4, 1);
//		List<Interview> list=iDao.getInterviewlistByName("Santu Malakar");
//		for(Interview i:list) {
//			System.out.println(i);
//		}
		
//		Users user1= new Users();
//		user1.setUserName("sagar");
//		user1.setPassword("porn69");
//		user1.setRole(UserRole.ADMIN);
//		session.save(user1);
//		transaction.commit();
//		System.out.println("succefully register");
//		iDao.ses
//		adao.applyApplicationById(4, 1);
//		InterviewDao idao=new InterviewDao(sf);
//		
//		List<Candidate> list=cdao.getAllCandidateDetails();
//		for(Candidate c:list) {
//			
//			System.out.println(c);
//		}
//		adao.getApplicationDetailsById(2);
//		jdao.getJobDetailsById(2);
		
//		cdao.getCandiadteDetailsById(0);
//		cdao.candidateStatusUpdate(1, "selected");
//		List<Candidate> list=cdao.getAllCandidateDetails();
//		for(Candidate c : list) {
//			System.out.println(c);
//		}
		
		
//		Candidate candidate1=new Candidate();
//		Candidate candidate=cdao.returnCandidateDetailsById(2);
//		System.out.println(candidate);
//		cdao.deleteCandidateById(0);
//		cdao.registerCandidate(candidate1);
//		cdao.candidateNameUpdate(1, "subir mahoro");
//		cdao.candidateResumeUpdate(1,"hfguhggggouighgrhgro");
//		cdao.candidateAddressUpdate(1,"dgyguy yryrytryreytretret fdgg");
//		cdao.candidateMobileUpdate(2, 10000);
//		LocalDateTime date= LocalDateTime.now();
//		cdao.candidateStatusUpdate(0,"selected");

//		Users user=new Users();
//		user.setUserName("ritam");
//		user.setPassword("1234");
//		user.setRole(UserRole.CANDIDATE);
//		session.save(user);
//		transaction.commit();
		
		
		
		
		        System.out.println("\t _____                    _____                    _____          ");
		        System.out.println("\t/\\    \\                  /\\    \\                  /\\    \\         ");
		        System.out.println("       /::\\    \\                /::\\____\\                /::\\    \\        ");
		        System.out.println("      /::::\\    \\              /::::|   |               /::::\\    \\       ");
		        System.out.println("     /::::::\\    \\            /:::::|   |              /::::::\\    \\      ");
		        System.out.println("    /:::/\\:::\\    \\          /::::::|   |             /:::/\\:::\\    \\     ");
		        System.out.println("   /:::/__\\:::\\    \\        /:::/|::|   |            /:::/__\\:::\\    \\    ");
		        System.out.println("  /::::\\   \\:::\\    \\      /:::/ |::|   |            \\:::\\   \\:::\\    \\   ");
		        System.out.println(" /::::::\\   \\:::\\    \\    /:::/  |::|___|______    ___\\:::\\   \\:::\\    \\  ");
		        System.out.println("/:::/\\:::\\   \\:::\\____\\  /:::/   |::::::::\\    \\  /\\   \\:::\\   \\:::\\    \\ ");
		        System.out.println("/:::/  \\:::\\   \\:::|    |/:::/    |:::::::::\\____\\/::\\   \\:::\\   \\:::\\____\\");
		        System.out.println("\\::/   |::::\\  /:::|____|\\::/    / ~~~~~/:::/    /\\:::\\   \\:::\\   \\::/    /");
		        System.out.println(" \\/____|:::::\\/:::/    /  \\/____/      /:::/    /  \\:::\\   \\:::\\   \\/____/ ");
		        System.out.println("       |:::::::::/    /               /:::/    /    \\:::\\   \\:::\\    \\     ");
		        System.out.println("       |::|\\::::/    /               /:::/    /      \\:::\\   \\:::\\____\\    ");
		        System.out.println("       |::| \\::/____/               /:::/    /        \\:::\\  /:::/    /    ");
		        System.out.println("       |::|  ~|                    /:::/    /          \\:::\\/:::/    /     ");
		        System.out.println("       |::|   |                   /:::/    /            \\::::::/    /      ");
		        System.out.println("       \\::|   |                  /:::/    /              \\::::/    /       ");
		        System.out.println("        \\:|   |                  \\::/    /                \\::/    /        ");
		        System.out.println("         \\|___|                   \\/____/                  \\/____/         ");
		

		        
		        System.out.print("\t");
		        System.out.println("____ ____ ____ ____ _  _ _ ___ _  _ ____ _  _ ___    _  _ ____ _  _ ____ ____ ____ _  _ ____ _  _ ___    ____ _   _ ____ ___ ____ _  _ ");
		        System.out.print("\t");
		        System.out.println("|__/ |___ |    |__/ |  | |  |  |\\/| |___ |\\ |  |     |\\/| |__| |\\ | |__| | __ |___ |\\/| |___ |\\ |  |     [__   \\_/  [__   |  |___ |\\/| ");
		        System.out.print("\t");
		        System.out.println("|  \\ |___ |___ |  \\ |__| |  |  |  | |___ | \\|  |     |  | |  | | \\| |  | |__] |___ |  | |___ | \\|  |     ___]   |   ___]  |  |___ |  | ");
		    
		
//      #####################################################################################
//      ###################################################################################
               
		        System.out.println("");
		        System.out.println("");
		        System.out.println("");
          UserDao udao=new UserDao(sf);   
	          Scanner sc = new Scanner (System.in);
	          int choice = 0 ;
	          boolean exit=false;
	                while(exit==false) {
	                	
	                	System.out.println(" 1.Login ");
	                	System.out.println(" 2.Register");
	                	System.out.println(" 3.Exit");
	                	
	                	System.out.println("enter the choice : ");
	                	choice=sc.nextInt();
	                	switch(choice) {
	                	case 1:
	                		udao.login();
	                		break;
	                	case 2 :
	                		udao.registerLogged();
	                		break;
	                	case 3:
	                		System.out.println("Bye ! exitting the programme");
	                		exit=true;
	                		break;
	                	default:
	                		System.out.println("valid choice ");
	                	}
	                }
	                
	                
//	                #######################################################################
//	                ###########################################################
	                
//	                System.out.format("%-5s %-10s %-20s %-20s %-10s %-10s %-10s %-20s %-20s","Id ","Title","Description","Requirements","Location","Salary","Status","Created At","Updated At");

//	                System.out.format("%-10s  %-20s  %-10s  %-10s \n" ,"Id "," Name ", "Price" ,"QuentityOnHand");
//					System.out.format("%-10s  %-20s  %-10s  %-10s \n",,product.getName(),product.getPrice(),product.getQuentityOnHand());
	}
	

}
