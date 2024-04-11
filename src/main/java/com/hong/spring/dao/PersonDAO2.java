package com.hong.spring.dao;

import java.util.List;
import java.util.Optional;

import com.hong.model.Person;

public interface PersonDAO2 {
	Person findPersonById(Integer id);
}