package com.restapi.activity;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
	public class PostRetrieveController {
	  @RequestMapping(method = RequestMethod.GET, value="/get/allpost")
	  @ResponseBody
	  public List<Post> getAllBlogs() {
	  return CreatePost.getInstance().getPostRecords();
	  }

	}
