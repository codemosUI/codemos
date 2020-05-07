package com.component.dao.impl;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.log4j.Logger;
import com.component.dao.UserDao;
import com.component.model.StatusMessage;
import com.component.model.User;
import com.component.util.Database;


public class UserDAOImpl implements UserDao{
	
	private DataSource datasource = Database.getDataSource();
	private Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public Response createUser(User user) {
		Connection conn = null;
	    PreparedStatement ps = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    StatusMessage statusMessage = null;
	    int autoID = -1;
			
			String sql = "insert into t_user (userId, name, bio, location, "
					+ "skills, joining_date, contact) values (?,?,?,?,?,?,?)";
			
			try {
				conn = datasource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, user.getUserId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getBio());
				ps.setString(4, user.getLocation());
				ps.setString(5, user.getSkills());
				ps.setTimestamp(6, user.getJoiningDate());
				ps.setString(7, user.getContact());
				
				
	      int rows = ps.executeUpdate();
	      
	      if (rows == 0) {
	  			logger.error("Unable to create Message...");
	  			statusMessage = new StatusMessage();
	  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
	  			statusMessage.setMessage("Unable to create customer...");
	  			return Response.status(404).entity(statusMessage).build();
	      }
	      
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("select LAST_INSERT_ID()");

	      if (rs.next()) {
	          autoID = rs.getInt(1);
	          user.setUserId(autoID);
	      }
	       
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("Error closing resultset: " + e.getMessage());
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						logger.error("Error closing PreparedStatement: " + e.getMessage());
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("Error closing connection: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			return Response.status(200).entity(user).build();
	}

	@Override
	public Response getUser(int id) {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    User user = null;
			String sql = "select userId, name, bio, location, "
			+ "skills, joining_date, contact from t_user where postId = ?";
			try {
				conn = datasource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
	      rs = ps.executeQuery();
	      
	      if (rs.next()) {
	    	  user = new User();
	    	  user.setUserId(rs.getInt("userId"));
	    	  user.setName(rs.getString("name"));
	    	  user.setBio(rs.getString("bio"));
	    	  user.setLocation(rs.getString("location"));
	    	  user.setSkills(rs.getString("skills"));
	    	  user.setJoiningDate(rs.getTimestamp("joining_date"));
	    	  user.setContact(rs.getString("contact"));
	    	
	    	 
	      } else {
	  			logger.error(String.format("User with ID of %d is not found.", id));
	  			StatusMessage statusMessage = new StatusMessage();
	  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
	  			statusMessage.setMessage(String.format("User with ID of %d is not found.", id));
	  			return Response.status(404).entity(statusMessage).build();
	      }
			} catch (SQLException e) {
				logger.error("Error: " + e.getMessage());
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("Error closing resultset: " + e.getMessage());
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						logger.error("Error closing PreparedStatement: " + e.getMessage());
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("Error closing connection: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			return Response.status(200).entity(user).build();
	}

	@Override
	public Response getAllUsers() {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
			List<User> allUsers = new ArrayList<User>();
			
			String sql = "select userId, name, bio, location, "
					+ "skills, joining_date, contact from t_user";
			try {
				conn = datasource.getConnection();
				ps = conn.prepareStatement(sql);
	      rs = ps.executeQuery();
	      
	      while (rs.next()) {
	    	  
	    	 User user = new User();
	    	  user.setUserId(rs.getInt("userId"));
	    	  user.setName(rs.getString("name"));
	    	  user.setBio(rs.getString("bio"));
	    	  user.setLocation(rs.getString("location"));
	    	  user.setSkills(rs.getString("skills"));
	    	  user.setJoiningDate(rs.getTimestamp("joining_date"));
	    	  user.setContact(rs.getString("contact"));
	    	  allUsers.add(user);
	      }
	      
	      if (allUsers.isEmpty()) {
	      	logger.error("No Message Exists...");
	  			StatusMessage statusMessage = new StatusMessage();
	  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
	  			statusMessage.setMessage("No Messages Exists...");
	  			return Response.status(404).entity(statusMessage).build();
	      }
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("Error closing resultset: " + e.getMessage());
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						logger.error("Error closing PreparedStatement: " + e.getMessage());
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("Error closing connection: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			return Response.status(200).entity(allUsers).build();
	}

}
