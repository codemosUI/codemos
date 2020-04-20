import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostsComponent } from './components/posts/posts.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NewpostComponent } from './components/newpost/newpost.component';
const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "post", component: PostsComponent },
  { path: "profile", component: ProfileComponent },
  { path: "newpost", component: NewpostComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
