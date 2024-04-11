package com.hong.model;

// import lombok.*; 
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

// import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class Person {

	private Integer id;

	@NotNull
	@Size(min = 3, max = 50, message = "must be minimum 3 characters, and maximum 50 characters long")
	private String firstName;

	@NotNull
	@Size(min = 3, max = 50, message = "must be minimum 3 characters, and maximum 50 characters long")
	private String lastName;

	@NotNull
	private Integer age;

	@NotNull
	private Gender gender;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

	// public Person() {
	// }

	// public Person(Integer id, Integer age, String firstName, String lastName) {
	// 	this.id = id;
	// 	this.age = age;
	// 	this.firstName = firstName;
	// 	this.lastName = lastName;
	// }

	// public Person(Integer age, String firstName, String lastName) {
	// 	this.age = age;
	// 	this.firstName = firstName;
	// 	this.lastName = lastName;
	// }

	// public Integer getId() {
	// 	return id;
	// }

	// public void setId(Integer id) {
	// 	this.id = id;
	// }

	// public Integer getAge() {
	// 	return age;
	// }

	// public void setAge(Integer age) {
	// 	this.age = age;
	// }

	// public String getFirstName() {
	// 	return firstName;
	// }

	// public void setFirstName(String firstName) {
	// 	this.firstName = firstName;
	// }

	// public String getLastName() {
	// 	return lastName;
	// }

	// public void setLastName(String lastName) {
	// 	this.lastName = lastName;
	// }

	// @Override
	// public String toString() {
	// 	return "Person{" + "id=" + id + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
	// }
	
}
