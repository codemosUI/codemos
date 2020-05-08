import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './components/profile/profile/profile.component';
import { PostsComponent } from './components/posts/posts/posts.component';
import { AddPostComponent } from './components/posts/add-post/add-post.component';
import { HomeComponent } from './components/timeline/home/home.component';
import { PostDetailComponent } from './components/posts/post-detail/post-detail.component';
import { AuthorizeComponent } from './components/authorize/authorize.component';
import { NavbarComponent } from './components/common/navbar/navbar.component';

const routes: Routes = [
  {path: '', component: AuthorizeComponent},
  {path: 'home', component: NavbarComponent, children:[
    {path: '', component: HomeComponent},
    { path: 'post', component: PostsComponent },
    { path: 'post/:postId', component: PostDetailComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'addpost', component: AddPostComponent },
  ]}, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
