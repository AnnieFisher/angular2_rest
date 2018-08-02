import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', '../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class AppComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8080';
  private getUrl: string = this.baseUrl + '/room/reservation/v1/';
  private postUrl: string = this.baseUrl + '/room/reservation/v1';
  public submitted: boolean;

  roomsearch: FormGroup;
  rooms: Room[];
  currentCheckInVal: string;
  currentCheckOutVal: string;
  request: ReserveRoomRequest;


  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });
  }


  onSubmit({value, valid}: { value: Roomsearch, valid: boolean }) {
    this.getAll()
      .subscribe(
        rooms => {
          this.rooms = rooms;
          console.log(this.rooms);
        },
        err => {
          console.log(err);
        });
  }

  reserveRoom(value: string) {
    this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

    this.createReservation(this.request);
  }

  getAll(): Observable<any> {
    return this.httpClient.get<Room[]>(this.getUrl + '?checkin=' + this.currentCheckInVal + '&checkout=' + this.currentCheckOutVal);
  }

  createReservation(body: object) {

    let bodyString = JSON.stringify(body);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'my-auth-token'
      })
    };
    this.httpClient.post<any>(this.postUrl, bodyString, httpOptions).subscribe(res => console.log(res));
  }

}

export interface Roomsearch {
  checkin: string;
  checkout: string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string, checkin: string, checkout: string) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }

}
