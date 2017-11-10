package com.deepak.spring.dao;

import com.deepak.spring.model.User;

public interface UserDao {
	User getUserByName(String username);
}
