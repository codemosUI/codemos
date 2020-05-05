package com.codemos.data.model;

import java.sql.Timestamp;

public class Post {
	long postId;
	String userId;
    String body;
    Timestamp timestamp;
    long parentId;
    long likecount;
    
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getLikecount() {
		return likecount;
	}
	public void setLikecount(long likecount) {
		this.likecount = likecount;
	}

	 @Override
		public String toString() {
			return "Post [postId=" + postId + ", userId=" + userId + ", body=" + body + ", timestamp=" + timestamp
					+ ", parentId=" + parentId + ", likecount=" + likecount + "]";
		}
	
    
}