import { Component, OnInit } from '@angular/core';
// import { Post } from '../post';
import { FormGroup, FormControl } from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER, SPACE} from '@angular/cdk/keycodes';

export interface Tag {
  name: string;
}
export interface Post {
  name: string;
}

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA, SPACE];
  tags: Tag[] = [];
  tagField = '';
  postBody = '';
  post: Post;
  postForm: FormGroup;
  isItemPosted = false;

  constructor()  { }

  ngOnInit(): void {

    this.postForm = new FormGroup({
      postBody : new FormControl(),
      tagField : new FormControl(),
    });

  }

  onPostSubmit() {
    let postContent = '';
    if (this.postForm) {
      postContent = this.postForm.controls.postBody.value;
    }
    for (let tag of this.tags) {
      if (postContent) {
        postContent = postContent + ' ' + tag.name;
      } else {
        postContent = tag.name;
      }
    }
    this.isItemPosted = true;
  }

  remove(tag: Tag): void {
    const index = this.tags.indexOf(tag);
    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    let value = event.value;
    if (!value.startsWith('#') && value !== '') {
      value = '#' + value;
    }
    if ((value || '').trim()) {
      this.tags.push({name: value.trim()});
    }
    if (input) {
      input.value = '';
    }
  }
}