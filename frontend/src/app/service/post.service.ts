import { Injectable } from '@angular/core';
import { environment as env } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../model/post.model'
@Injectable({
  providedIn: 'root'
})
export class PostService {
  headers: HttpHeaders;
  constructor(private client: HttpClient) {
    this.headers = new HttpHeaders({ 'content-type': 'application/json' });
  }

  GetPost(id:string): Observable<Post> {
    return this.client.get<Post>(env.apiAddress + '/post/'+id);
  }
  GetPosts(): Observable<Post[]> {
    return this.client.get<Post[]>(env.apiAddress + '/post');
  }

  AddPosts(post: Post): Observable<HttpResponse<any>> {
    return this.client.post<HttpResponse<any>>(env.apiAddress + '/post', JSON.stringify(post), { headers: this.headers, observe: 'response' });
  }
  
}
