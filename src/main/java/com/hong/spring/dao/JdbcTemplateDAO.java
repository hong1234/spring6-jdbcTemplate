package com.hong.spring.dao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
// import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;

import com.hong.model.Person;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JdbcTemplateDAO implements PersonDAO {

	private final String SQL_GET_ALL = "select * from people";
	private final String SQL_FIND_PERSON = "select * from people where id = :id";
	private final String SQL_INSERT_PERSON = "insert into people(first_name, last_name, age, gender, created_on) values(:fname, :lname, :age, :gender, :createdOn)";
	private final String SQL_UPDATE_PERSON = "update people set first_name = :fname, last_name = :lname, age = :age, created_on = :createdOn, updated_on = :updatedOn where id = :id";
	private final String SQL_DELETE_PERSON = "delete from people where id = :id";

	private final NamedParameterJdbcTemplate jdbcTemplate;

	// public JdbcTemplateDAO(NamedParameterJdbcTemplate jdbcTemplate) {
    // 	this.jdbcTemplate = jdbcTemplate;
	// }

	public List<Person> getAllPersons() {
		return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
	}

	public Person getPersonById(Integer id) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
		return jdbcTemplate.queryForObject(SQL_FIND_PERSON, parameters, new PersonMapper());
	}

	// public boolean createPerson(Person person) {
	// 	SqlParameterSource parameters = new MapSqlParameterSource()
	// 				.addValue("fname", person.getFirstName())
	// 				.addValue("lname", person.getLastName())
	// 				.addValue("age", person.getAge())
	// 				// .addValue("gender", person.getGender().toString())
	// 				.addValue("gender", person.getGender().name())
	// 				.addValue("createdOn", person.getCreatedOn());
	// 	return jdbcTemplate.update(SQL_INSERT_PERSON, parameters) > 0;
	// }

	public Person createPerson(Person person) {
		SqlParameterSource parameters = new MapSqlParameterSource()
					.addValue("fname", person.getFirstName())
					.addValue("lname", person.getLastName())
					.addValue("age", person.getAge())
					// .addValue("gender", person.getGender().toString())
					.addValue("gender", person.getGender().name())
					.addValue("createdOn", person.getCreatedOn());

		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(SQL_INSERT_PERSON, parameters, generatedKeyHolder);

        Number key = generatedKeyHolder.getKey();
        return getPersonById(key.intValue());
	}

	public boolean updatePerson(Person person) {
		SqlParameterSource parameters = new MapSqlParameterSource()
					.addValue("id", person.getId())
					.addValue("fname", person.getFirstName())
					.addValue("lname", person.getLastName())
					.addValue("age", person.getAge())
					.addValue("createdOn", person.getCreatedOn())
					.addValue("updatedOn", LocalDateTime.now());
		return jdbcTemplate.update(SQL_UPDATE_PERSON, parameters) > 0;
	}

	public boolean deletePerson(Person person) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", person.getId());
		return jdbcTemplate.update(SQL_DELETE_PERSON, parameters) > 0;
	}

}
