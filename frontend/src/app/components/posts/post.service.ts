import { Injectable } from '@angular/core';
import { Post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  month = new Array();

  POSTS: Post[] = [
    { postId: 11, userId: 11, content: 'This is post 1 to check how post gets displayed',
      dateToDisplay: '15 April at 13:20', createdAt: new Date(), parentId: null, reactionCount: 10},
    { postId: 12, userId: 12, content: 'This is post 2 to validate the post body and check how the body is being displayed', 
      dateToDisplay: '1 February at 18:00', createdAt: new Date(), parentId: null, reactionCount: 10},
  ];

  constructor() { }

  /* This method is used in
     formatting the post creation date
  */
  initializeMonthArr() {
    this.month[0] = 'January';
    this.month[1] = 'February';
    this.month[2] = 'March';
    this.month[3] = 'April';
    this.month[4] = 'May';
    this.month[5] = 'June';
    this.month[6] = 'July';
    this.month[7] = 'August';
    this.month[8] = 'September';
    this.month[9] = 'October';
    this.month[10] = 'November';
    this.month[11] = 'December';
  }

  getPosts(): Post[] {
    for (const postElem in this.POSTS) {
      if (this.POSTS.hasOwnProperty(postElem) && this.POSTS[postElem].dateToDisplay === '') {
        this.POSTS[postElem].dateToDisplay = this.formatCurrDate(this.POSTS[postElem].createdAt);
      }
    }
    // Currently using hardcoded list of posts, later to be replaced with list fetched from backend
    return this.POSTS;
  }

  formatCurrDate(dateToFormat: Date) {
   console.log(dateToFormat.getMonth());
   const dateToDisplay = dateToFormat.getDate() + ' ' + this.month[dateToFormat.getMonth()] + ' at '
           + dateToFormat.getHours() + ':' + dateToFormat.getMinutes();
   return dateToDisplay;
  }
}
