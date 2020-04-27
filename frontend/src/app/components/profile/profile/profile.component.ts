import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface DialogData {
  name: string;
  bio: string;
  dob: string;
  location: string;
  contact: number;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  name: string;
  bio: string;
  dob: Date;
  location: string;
  contact: number;
  constructor(public dialog: MatDialog) {
    this.name = "Utkarsh Tiwari";
    this.bio = "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Fugiat labore odit ducimus officia.\
    Nesciunt id, labore, velit placeat enim consequuntur, quia quae vitae iure nihil dolorem suscipit eum provident tenetur.";
    this.dob = new Date("1998-03-28");  
    this.location = "Gurgaon, India";
    this.contact = 9234151125;
   }

  ngOnInit() {
  }

  openEditDialog() {
    const edit_dialog = this.dialog.open(ProfileEditDialog, {
      width:'fit-content',
      height:'fit-content',
      data: {name: this.name, bio:this.bio, dob:this.dob, location:this.location, contact:this.contact}
    });
    console.log(this.dob);
    edit_dialog.afterClosed().subscribe(res => {
      console.log('The dialog was closed');
      console.log(res);
      this.name = res;
    });
  }
}

@Component({
  selector: 'profile-edit',
  templateUrl: 'profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditDialog {

  constructor(
    public dialogRef: MatDialogRef<ProfileEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

    onNoClick() {
      this.dialogRef.close()
    }

}
