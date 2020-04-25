package com.component.dao;

import javax.ws.rs.core.Response;

import com.component.model.Message;
import com.component.model.User;

public interface UserDao {
	public Response createUser(User user);
	public Response getUser(int id);
	public Response getAllUsers();
}
