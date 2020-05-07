package com.component.dao.impl;

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

import com.component.dao.ReactionDao;
import com.component.model.Reaction;
import com.component.model.StatusMessage;
import com.component.util.Database;

public class ReactionDAOImpl implements ReactionDao {

	private DataSource datasource = Database.getDataSource();
	private Logger logger = Logger.getLogger(ReactionDAOImpl.class);

	@Override
	public Response createReaction(int postId, Reaction reaction) {
		reaction.setPostId(postId);
		boolean isPostIdExist = false;
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		StatusMessage statusMessage = null;
		int autoID = -1;
		int numberRow = 0;
		String sqlPostIdCheck = "select postId,count(*) from t_post where postId=?";

		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sqlPostIdCheck);
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			while (rs.next()) {
				numberRow = rs.getInt("count(*)");
			}
			if (numberRow == 0) {
				isPostIdExist = false;
				logger.error("post id is not found");
				statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
				statusMessage.setMessage("post id is not found");
				return Response.status(404).entity(statusMessage).build();
			} else {
				isPostIdExist = true;
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
		if(isPostIdExist) {
		String sql = "insert into t_reaction (userId, postId, type) values (?,?,?)";

		try {

			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, reaction.getUserId());
			ps.setInt(2, reaction.getPostId());
			ps.setInt(3, reaction.getType());
			int rows = ps.executeUpdate();
			if (rows == 0) {
				logger.error("Unable to create reaction...");
				statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
				statusMessage.setMessage("Unable to create Reaction...");
				return Response.status(404).entity(statusMessage).build();
			}

			stmt = conn.createStatement();
			rs = stmt.executeQuery("select LAST_INSERT_ID()");

			if (rs.next()) {
				autoID = rs.getInt(1);
				reaction.setId(autoID);
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
		}
		return Response.status(200).entity(reaction).build();
	}

	@Override
	public Response getReaction(int postId, int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reaction reaction = null;
		String sql = "select id, userId, postId, type from t_reaction where postId = ? AND id = ?";
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				reaction = new Reaction();
				reaction.setId(rs.getInt("id"));
				reaction.setUserId(rs.getInt("userId"));
				reaction.setPostId(rs.getInt("postId"));
				reaction.setType(rs.getInt("type"));

			} else {
				logger.error(String.format("Reaction with ID of %d is not found.", id));
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
				statusMessage.setMessage(String.format("Reaction with ID of %d is not found.", id));
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
		return Response.status(200).entity(reaction).build();
	}

	@Override
	public Response getAllReactions(int postId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Reaction> allReactions = new ArrayList<Reaction>();

		String sql = "select id, userId, postId, type from t_reaction where postId = ?";
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			rs = ps.executeQuery();

			while (rs.next()) {

				Reaction reaction = new Reaction();
				reaction.setId(rs.getInt("id"));
				reaction.setUserId(rs.getInt("userId"));
				reaction.setPostId(rs.getInt("postId"));
				reaction.setType(rs.getInt("type"));
				allReactions.add(reaction);
			}

			if (allReactions.isEmpty()) {
				logger.error("No Reactions Exists...");
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
				statusMessage.setMessage("No Reactions Exists...");
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
		return Response.status(200).entity(allReactions).build();
	}

}
