package com.hong;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.hong.spring.config.AppConfig;
// import com.hong.spring.config.DataSourceCfg;
// import com.hong.spring.config.TransactionCfg;

import com.hong.model.Person;
import com.hong.spring.dao.PersonDAO;
import com.hong.spring.service.PersonManager;

public class SpringJdbcTemplateHApplication {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		// AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceCfg.class, TransactionCfg.class, AppConfig.class);

		// PersonDAO personDAO = context.getBean(PersonDAO.class);
		PersonManager personManager = context.getBean(PersonManager.class);
		
		personManager.testAdd();
		personManager.testGet();
		personManager.testTransaction();

		context.close();
	}
}
