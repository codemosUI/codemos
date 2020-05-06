import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Post } from 'src/app/components/posts/post.model';
import { PostService } from 'src/app/components/posts/post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  visible = true;
  postBody = '';
  postForm: FormGroup;
  postContent = '';
  divContent ="";

  constructor(private postService: PostService, private route: Router)  { }

  ngOnInit(): void {
    this.postForm = new FormGroup({
      postBody : new FormControl(),
    });
  }

  divChange()
  {
    document.getElementById('divContent').innerHTML="";
    var content = this.postBody;
    var res = content.split(" ");
    console.log(res);
    for(var i=0;i<res.length;++i)
    {
     if(res[i]){ 
       
      var newSpan = document.createElement('span');
      var txt = document.createTextNode(res[i] + " ");
      newSpan.appendChild(txt);
    document.getElementById('divContent').appendChild(newSpan);
     
    }
     else{
         var newSpan = document.createElement('span');
         newSpan.style.padding = "0px 2px 0px 0px";
       var txt = document.createTextNode(" ");
       newSpan.appendChild(txt);
         document.getElementById('divContent').appendChild(newSpan);
  }
  }
}
  onPostSubmit() {
    this.postContent = '';
    if (this.postForm.get('postBody')) {
      this.postContent = this.postForm.get('postBody').value;
    }
    this.savePostData();

    // Reset form after submitting
    if (this.postForm.valid) {
      this.postForm.reset();
    }
  }

  savePostData() {
    this.postService.initializeMonthArr();
    // set post body to be saved:
    const post: Post = {
      postId: 12, // To be auto generated
      userId: 15, // To be auto generated
      content: this.postContent,
      dateToDisplay: this.postService.formatCurrDate(new Date()), // Date that will be displayed
      createdAt: new Date(), // this date will be saved in DB
      parentId: null,
      reactionCount: 0
    };
    // Add element to the start of the list
    this.postService.getPosts().unshift(post);

    // Save newly created data to DB
    // this.postService.savePost(post);

  }

}
