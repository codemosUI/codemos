import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/components/posts/post.model';
import { PostService } from 'src/app/components/posts/post.service';

@Component({
   selector: 'app-posts',
   templateUrl: './posts.component.html',
   styleUrls: ['./posts.component.css']
 })
 export class PostsComponent implements OnInit {
   posts: Post[];
   // tags: string[];
   // selectable = true;

   constructor(private postService: PostService) {
      this.posts = postService.getPosts();

   }
   ngOnInit(): void {}

 }
