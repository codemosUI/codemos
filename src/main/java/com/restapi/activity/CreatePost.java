package com.restapi.activity;
import java.util.ArrayList;
import java.util.List;
public class CreatePost {
	 private List<Post> postRecords;
	    private static CreatePost createPost = null;
	    private CreatePost(){
	    	postRecords = new ArrayList<Post>();
	    }
	    public static CreatePost getInstance() {
	        if(createPost == null) {
	        	createPost = new CreatePost();
	              return createPost;
	            }
	            else {
	                return createPost;
	            }
	    }
	    public void add(Post std) {
	    	postRecords.add(std);
	    }
	  
		public List<Post> getPostRecords() {
			 return postRecords;
		}
}
