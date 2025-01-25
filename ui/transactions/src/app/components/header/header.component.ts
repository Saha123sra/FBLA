import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.isAuthenticated().subscribe(
      (isAuthenticated) => {
        this.isLoggedIn = isAuthenticated;
      }
    );
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
