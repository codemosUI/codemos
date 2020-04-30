import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from '../post.model';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {

  post: Post;
  constructor(private route: ActivatedRoute, private postService: PostService) { }

  ngOnInit(): void {
    this.getPostDetails();
  }

  getPostDetails() {
    const id = +this.route.snapshot.paramMap.get('postId');
    this.post = this.postService.getPostDetail(id);
  }

  displayList() {

  }
}
