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

import com.component.dao.ReactionDao;
import com.component.dao.UserDao;
import com.component.dao.impl.ReactionDAOImpl;
import com.component.dao.impl.UserDAOImpl;
import com.component.model.Reaction;

@Path("/message")
public class ReactionResource {
private Logger logger = Logger.getLogger(ReactionResource.class);	
	
	@POST
	@Path("/{postId}/reaction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReaction(@PathParam("postId") int postId, Reaction reaction) {
		ReactionDao daoImpl = new ReactionDAOImpl();
		logger.info("Inside createReaction...");
		Response resp = daoImpl.createReaction(postId, reaction);
		return resp;
	}
	
	@GET
	@Path("/{postId}/reaction")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllReactions(@PathParam("postId") int postId) throws JsonGenerationException,
		JsonMappingException, IOException {
		ReactionDao daoImpl = new ReactionDAOImpl();
		logger.info("Inside showAllReactions...");
		Response resp = daoImpl.getAllReactions(postId);
		return resp;
	}

}