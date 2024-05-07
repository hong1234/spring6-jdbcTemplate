package com.hong.spring.dao;

import java.util.List;
import com.hong.model.Person;

public interface PersonDAO {
	Person getPersonById(Integer id);
	List<Person> getAllPersons();
	
	// boolean createPerson(Person person);
	Person createPerson(Person person);
	boolean updatePerson(Person person);
	boolean deletePerson(Person person);
}
