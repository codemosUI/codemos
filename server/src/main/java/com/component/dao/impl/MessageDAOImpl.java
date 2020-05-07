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

import com.component.dao.MessageDao;
import com.component.model.Message;
import com.component.model.StatusMessage;
import com.component.util.Database;

public class MessageDAOImpl implements MessageDao {
	private DataSource datasource = Database.getDataSource();
	private Logger logger = Logger.getLogger(MessageDAOImpl.class);

	@Override
	public Response createMessage(int userId, Message message) {
		message.setUserId(userId);
		boolean isUserExist = false;
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		StatusMessage statusMessage = null;
		int autoID = -1;
		int numberRow = 0;

		String sqlUserCheck = "select userId,count(*) from t_user where userId = ?";

		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sqlUserCheck);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				numberRow = rs.getInt("count(*)");
			}
			if (numberRow == 0) {
				isUserExist = false;
				logger.error("Unable to create Message...");
				statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
				statusMessage.setMessage("user id is not found");
				return Response.status(404).entity(statusMessage).build();
			} else {
				isUserExist = true;
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
		if (isUserExist) {
			String sql = "insert into t_post (userId, content, createdAt, "
					+ "parentId, postcol, reaction_count, like_count) values (?,?,?,?,?,?,?)";
			try {
				conn = datasource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, message.getUserId());
				ps.setString(2, message.getContent());
				ps.setTimestamp(3, message.getCreatedAt());
				ps.setInt(4, message.getParentId());
				ps.setString(5, message.getPostcol());
				ps.setInt(6, message.getReactionCount());
				ps.setInt(7, message.getLikeCount());

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
					message.setPostId(autoID);
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
		return Response.status(200).entity(message).build();
	}

	@Override
	public Response getMessage(int id, int messageId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Message message = null;

		String sql = "select postId, userId, content, createdAt, "
				+ "parentId, postcol, reaction_count, like_count from t_post where userId = ? AND postId = ?";
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, messageId);
			rs = ps.executeQuery();

			if (rs.next()) {
				message = new Message();
				message.setPostId(rs.getInt("postId"));
				message.setUserId(rs.getInt("userId"));
				message.setContent(rs.getString("content"));
				message.setCreatedAt(rs.getTimestamp("createdAt"));
				message.setParentId(rs.getInt("parentId"));
				message.setPostcol(rs.getString("postcol"));
				message.setReactionCount(rs.getInt("reaction_count"));
				message.setLikeCount(rs.getInt("like_count"));
			} else {
				logger.error(String.format("Customer with ID of %d is not found.", id));
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
				statusMessage.setMessage(String.format("Customer with ID of %d is not found.", id));
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
		return Response.status(200).entity(message).build();
	}

	@Override
	public Response getAllMessages(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Message> allMessages = new ArrayList<Message>();

		String sql = "select postId, userId, content, createdAt, "
				+ "parentId, postcol, reaction_count, like_count from t_post";
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Message message = new Message();
				message.setPostId(rs.getInt("postId"));
				message.setUserId(rs.getInt("userId"));
				message.setContent(rs.getString("content"));
				message.setCreatedAt(rs.getTimestamp("createdAt"));
				message.setParentId(rs.getInt("parentId"));
				message.setPostcol(rs.getString("postcol"));
				message.setReactionCount(rs.getInt("reaction_count"));
				message.setLikeCount(rs.getInt("like_count"));
				allMessages.add(message);
			}

			if (allMessages.isEmpty()) {
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
		return Response.status(200).entity(allMessages).build();
	}
}
