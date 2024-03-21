package com.app.Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main_Class {

	public static void main(String[] args) {
		
		
		Configuration config = new Configuration();
		config.configure("Hibernate.Configuration.xml");
		
		SessionFactory sfactory = config.buildSessionFactory();
		Session session = sfactory.openSession();
		
		Transaction tran = session.beginTransaction();
		
		tran.commit();

	}

}
