package com.component.dao;

import javax.ws.rs.core.Response;

import com.component.model.Message;

public interface MessageDao {
	public Response createMessage(int userId, Message message);
	public Response getMessage(int id);
	public Response getAllMessages(int userId);
}
