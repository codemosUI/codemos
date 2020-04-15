package com.restapi.activity;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreatePostController {
  @RequestMapping(method = RequestMethod.POST, value="/create/post")
  @ResponseBody
  public PostReply createBlog(@RequestBody Post post) {
  System.out.println("In createPost");
        PostReply postReply = new PostReply();           
        CreatePost.getInstance().add( post);
        //We are setting the below value just to reply a message back to the caller
        postReply.setPostId(post.getPostId());
        postReply.setBody( post.getBody());
        postReply.setLikecount(post.getLikecount());
        postReply.setParentId(post.getParentId());
        postReply.setTimestamp(post.getTimestamp());
        postReply.setUserId(post.getUserId());
        postReply.setCreatePostStatus("Successful");
        return postReply;
}
}