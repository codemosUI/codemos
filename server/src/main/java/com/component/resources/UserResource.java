package com.component.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.component.dao.UserDao;
import com.component.dao.impl.UserDAOImpl;
import com.component.model.User;

@Path("/user")
public class UserResource {
private Logger logger = Logger.getLogger(UserResource.class);	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {

		UserDao daoImpl = new UserDAOImpl();
		logger.info("Inside createUser...");
		
		Response resp = daoImpl.createUser(user);
		return resp;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllUsers() throws JsonGenerationException,
		JsonMappingException, IOException {
		UserDao daoImpl = new UserDAOImpl();
		logger.info("Inside showAllUsers...");
		Response resp = daoImpl.getAllUsers();
		return resp;
	}
	@Path("/{userId}/message")
	public MessageResource getMessageResource() {
		return new MessageResource();
	}
}