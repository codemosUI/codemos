package com.codemos;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codemos.data.model.Post;


@Path("/message")
public class RestServer {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPostRecord(){
		 Timestamp timeStampDate = null;
		 
		       // you can change format of date
			  Instant instant = Instant.parse( "2010-10-02T12:23:23Z" ); 
		    
		       timeStampDate = Timestamp.from(instant);
		   
		//create a student object and set some data
		Post post = new Post();
		post.setUserId("123");
		post.setTimestamp(timeStampDate);
		post.setPostId(12345);
		post.setParentId(34566);
		post.setLikecount(46677);
		return post;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postRecord(Post post){
		String result = "Record entered: "+ post;
		return Response.status(201).entity(result).build();
	}
}
