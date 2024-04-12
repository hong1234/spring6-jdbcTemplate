package com.hong.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hong.model.Person;
import com.hong.model.Gender;

import com.hong.spring.dao.PersonDAO;
import com.hong.spring.dao.PersonDAO2;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
// @Transactional
public class PersonManager {

	private final PersonDAO  personDao;
	private final PersonDAO2 jdbcDao;

	// public PersonManager(PersonDAO personDao, PersonDAO2 jdbcDao) {
	// 	this.personDao = personDao;
	// 	this.jdbcDao = jdbcDao;
	// }

	// @Transactional
	public void testAdd() {

		LocalDateTime ldt = LocalDateTime.now();

		Person person = new Person();
		person.setFirstName("Vlad");
		person.setLastName("Boyarskiy");
		person.setAge(21);
		person.setGender(Gender.valueOf("Male"));  // Male, Female, Third
		person.setCreatedOn(ldt);
		personDao.createPerson(person);

		person = new Person();
		person.setFirstName("Oksi");
		person.setLastName("Bahatskaya");
		person.setAge(30);
		person.setGender(Gender.Female);
		person.setCreatedOn(ldt);
		personDao.createPerson(person);

		System.out.println("\nList of person is:");
		for (Person p : personDao.getAllPersons()) {
			System.out.println(p);
		}

		System.out.println("\nCreating person: ");
		person = new Person();
		person.setFirstName("Sergey");
		person.setLastName("Emets");
		person.setAge(36);
		person.setGender(Gender.Third);
		person.setCreatedOn(ldt);
		personDao.createPerson(person);

	}

	public void testGet() {
		Person personById = jdbcDao.findPersonById(3);
		System.out.println(personById);
	}

	@Transactional
	public void testTransaction() {

		System.out.println("\nList of person is:");
		for (Person p : personDao.getAllPersons()) {
			System.out.println(p);
		}

		// System.out.println("\nGet person with ID 2");
		Person personById = personDao.getPersonById(2); 
		// System.out.println(personById);

		System.out.println("\nDeleting person with ID 2");
		personDao.deletePerson(personById);

		System.out.println("\nUpdate person with ID 3");
		Person pperson = personDao.getPersonById(3);
		pperson.setLastName("CHANGED");
		personDao.updatePerson(pperson);

		System.out.println("\nList of person is:");
		for (Person p : personDao.getAllPersons()) {
			System.out.println(p);
		}

		System.out.println("\n");

	}

}