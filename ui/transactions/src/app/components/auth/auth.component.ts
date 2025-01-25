// auth.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {
  isLogin = true;

  toggleForm() {
    this.isLogin = !this.isLogin;
  }
}
