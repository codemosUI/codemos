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
  isPostAvailable = false;
  postForm: FormGroup;
  postContent = '';

  constructor(private postService: PostService, private route: Router)  { }

  ngOnInit(): void {
    this.postForm = new FormGroup({
      postBody : new FormControl(),
    });

    this.postForm.get('postBody').valueChanges.subscribe(value => {
     value === '' ? this.isPostAvailable = false :  this.isPostAvailable = true;
    });
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
      this.isPostAvailable = false;
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
