package com.component.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.component.dao.impl.MessageDAOImpl;
import com.component.model.Message;


@Path("/")
public class MessageResource {
	private Logger logger = Logger.getLogger(MessageResource.class);	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMessage(@PathParam("userId") int userId, Message message) {

		MessageDAOImpl daoImpl = new MessageDAOImpl();
		logger.info("Inside createMessage...");
		
		Response resp = daoImpl.createMessage(userId, message);
		return resp;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllMessages(@PathParam("userId") int userId) throws JsonGenerationException,
		JsonMappingException, IOException {
		MessageDAOImpl daoImpl = new MessageDAOImpl();
		logger.info("Inside showAllMessages...");
		Response resp = daoImpl.getAllMessages(userId);
		return resp;
	}
}
