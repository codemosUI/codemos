import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile/profile.component';
import { PostsComponent } from './posts/posts/posts.component';
import { AddPostComponent } from './posts/add-post/add-post.component';
import { HomeComponent } from './timeline/home/home.component';
import { SettingsComponent } from './settings/settings/settings.component';
import { GamesComponent } from './games/games/games.component';
import { NavbarComponent} from './common-components/codemos-nav/navbar/navbar.component'
const routes: Routes = [
  {
    path: "", component: NavbarComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'post', component: PostsComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'addpost', component: AddPostComponent },
      { path: "settings", component: SettingsComponent },
      { path: "games", component: GamesComponent }

    ]
  }
 
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
