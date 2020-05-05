import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './components/profile/profile/profile.component';
import { PostsComponent } from './components/posts/posts/posts.component';
import { AddPostComponent } from './components/posts/add-post/add-post.component';
import { HomeComponent } from './components/timeline/home/home.component';
import { PostDetailComponent } from './components/posts/post-detail/post-detail.component';
import { SettingsComponent } from './components/settings/settings/settings.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'post', component: PostsComponent },
  { path: 'post/:postId', component: PostDetailComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'addpost', component: AddPostComponent },
  { path: 'settings', component: SettingsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
