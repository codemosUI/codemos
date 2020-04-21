import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile/profile.component';
import { PostsComponent } from './posts/posts/posts.component';
import { AddPostComponent } from './posts/add-post/add-post.component';
import { HomeComponent } from './timeline/home/home.component';

const routes: Routes = [
   { path: '', component: HomeComponent },
   { path: 'post', component: PostsComponent },
   { path: 'profile', component: ProfileComponent },
   { path: 'addpost', component: AddPostComponent }
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
