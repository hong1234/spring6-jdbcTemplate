package com.hong.spring.dao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import java.sql.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hong.model.Person;

@Component
public class JdbcDAO implements PersonDAO2 {

	private final String SQL_FIND_PERSON = "select * from people where id = ?";
	// private final String SQL_INSERT_PERSON = "insert into people(first_name, last_name, age) values(?, ?, ?)";

	private final DataSource dataSource;

	public JdbcDAO(DataSource dataSource) {
    	this.dataSource = dataSource;
	}

    public Person findPersonById(Integer id) {

		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
		Person person = null;

		try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_PERSON);
			statement.setInt(1, id);
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                PersonMapper mapper = new PersonMapper();
                person = mapper.mapRow(resultSet, 1);
            }

        } catch (SQLException e) {
            // ??? What should be done here ???
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }
        }

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
