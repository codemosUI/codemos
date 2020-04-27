package com.component.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"postId",
"userId",
"content",
"createdAt",
"parentId",
"postcol",
"reaction_count",
"like_count"
})
public class Message {

@JsonProperty("postId")
private Integer postId;
@JsonProperty("userId")
private Integer userId;
@JsonProperty("content")
private String content;
@JsonProperty("createdAt")
private Timestamp createdAt;
@JsonProperty("parentId")
private Integer parentId;
@JsonProperty("postcol")
private String postcol;
@JsonProperty("reaction_count")
private Integer reactionCount;
@JsonProperty("like_count")
private Integer likeCount;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("postId")
public Integer getPostId() {
return postId;
}

@JsonProperty("postId")
public void setPostId(Integer postId) {
this.postId = postId;
}

@JsonProperty("userId")
public Integer getUserId() {
return userId;
}

@JsonProperty("userId")
public void setUserId(Integer userId) {
this.userId = userId;
}

@JsonProperty("content")
public String getContent() {
return content;
}

@JsonProperty("content")
public void setContent(String content) {
this.content = content;
}

@JsonProperty("createdAt")
public Timestamp getCreatedAt() {
return createdAt;
}

@JsonProperty("createdAt")
public void setCreatedAt(Timestamp createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("parentId")
public Integer getParentId() {
return parentId;
}

@JsonProperty("parentId")
public void setParentId(Integer parentId) {
this.parentId = parentId;
}

@JsonProperty("postcol")
public String getPostcol() {
return postcol;
}

@JsonProperty("postcol")
public void setPostcol(String postcol) {
this.postcol = postcol;
}

@JsonProperty("reaction_count")
public Integer getReactionCount() {
return reactionCount;
}

@JsonProperty("reaction_count")
public void setReactionCount(Integer reactionCount) {
this.reactionCount = reactionCount;
}

@JsonProperty("like_count")
public Integer getLikeCount() {
return likeCount;
}

@JsonProperty("like_count")
public void setLikeCount(Integer likeCount) {
this.likeCount = likeCount;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}