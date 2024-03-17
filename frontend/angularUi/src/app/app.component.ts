import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { HomeScreenComponent } from './screens/home-screen/home-screen.component';
import { SigninComponent } from './screens/signin/signin.component';
import { AuthServiceService } from './services/Auth/auth-service.service';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,NavbarComponent,FooterComponent,HomeScreenComponent,SigninComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'angularUi';

  user:any=null

  constructor(public authService:AuthServiceService){}

  ngOnInit(){
    this.authService.authSubject.subscribe(
      (auth)=>{
        this.user=auth.user
      }
    )
  }
}
