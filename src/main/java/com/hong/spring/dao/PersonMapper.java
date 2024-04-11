package com.hong.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper; 

import com.hong.model.Person;
import com.hong.model.Gender;

public class PersonMapper implements RowMapper<Person> {

	public Person mapRow(ResultSet rs, int i) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("id"));
		person.setFirstName(rs.getString("first_name"));
		person.setLastName(rs.getString("last_name"));
		person.setAge(rs.getInt("age"));
		person.setGender(Gender.valueOf(rs.getString("gender")));
		person.setCreatedOn(convertToLocalDateTime(rs.getTimestamp("created_on")));
        person.setUpdatedOn(convertToLocalDateTime(rs.getTimestamp("updated_on")));
		return person;
	}

	private LocalDateTime convertToLocalDateTime(Timestamp tst) {
        if (tst == null) {
            return null;
        } else {
            return tst.toLocalDateTime();
        }
    }

}
