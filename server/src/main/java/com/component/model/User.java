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
"userId",
"name",
"bio",
"location",
"skills",
"joining_date",
"contact"
})
public class User {

@JsonProperty("userId")
private Integer userId;
@JsonProperty("name")
private String name;
@JsonProperty("bio")
private String bio;
@JsonProperty("location")
private String location;
@JsonProperty("skills")
private String skills;
@JsonProperty("joining_date")
private Timestamp joiningDate;
@JsonProperty("contact")
private String contact;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("userId")
public Integer getUserId() {
return userId;
}

@JsonProperty("userId")
public void setUserId(Integer userId) {
this.userId = userId;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("bio")
public String getBio() {
return bio;
}

@JsonProperty("bio")
public void setBio(String bio) {
this.bio = bio;
}

@JsonProperty("location")
public String getLocation() {
return location;
}

@JsonProperty("location")
public void setLocation(String location) {
this.location = location;
}

@JsonProperty("skills")
public String getSkills() {
return skills;
}

@JsonProperty("skills")
public void setSkills(String skills) {
this.skills = skills;
}

@JsonProperty("joining_date")
public Timestamp getJoiningDate() {
return joiningDate;
}

@JsonProperty("joining_date")
public void setJoiningDate(Timestamp joiningDate) {
this.joiningDate = joiningDate;
}

@JsonProperty("contact")
public String getContact() {
return contact;
}

@JsonProperty("contact")
public void setContact(String contact) {
this.contact = contact;
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