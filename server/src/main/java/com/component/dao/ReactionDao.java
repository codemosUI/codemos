package com.component.dao;

import javax.ws.rs.core.Response;

import com.component.model.Reaction;
public interface ReactionDao {
	public Response createReaction(int postId, Reaction reaction);
	public Response getReaction(int id);
	public Response getAllReactions(int postId);
}
