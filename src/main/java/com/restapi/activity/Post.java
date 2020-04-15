package com.restapi.activity;

public class Post {
	
	long postId;
    String userId;
    String body;
    String timestamp;
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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
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
    
  
	
    
}
