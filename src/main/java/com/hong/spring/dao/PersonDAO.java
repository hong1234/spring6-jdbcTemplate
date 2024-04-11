package com.hong.spring.dao;

import java.util.List;
import com.hong.model.Person;

public interface PersonDAO {
	Person getPersonById(Integer id);
	List<Person> getAllPersons();
	
	boolean deletePerson(Person person);
	boolean updatePerson(Person person);
	boolean createPerson(Person person);
}
