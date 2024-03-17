import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, Validators,ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { AuthServiceService } from '../../services/Auth/auth-service.service';


@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule,FormsModule, MatFormFieldModule, MatInputModule,MatButtonModule,ReactiveFormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss'
})
export class SigninComponent {

  constructor(public authService:AuthServiceService){}

  isRegister=true;

  registrationForm= new FormGroup({
    fullName: new FormControl("",[Validators.required]),
    email: new FormControl("",[Validators.required,Validators.email]),
    password: new FormControl("",[Validators.required,Validators.min(6)])
  }) 

  loginForm= new FormGroup({
    email: new FormControl("",[Validators.required,Validators.email]),
    password: new FormControl("",[Validators.required])
  }) 

  handleRegister(){
    this.authService.register(this.registrationForm.value).subscribe({
      next:(response)=> {
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("Signup success",response)
      }
    })
  }

  handleLogin(){
    this.authService.login(this.loginForm.value).subscribe({
      next:(response)=> {
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("Login success",response)
      }
    })
  }

  

  togglePanel(){
    this.isRegister=!this.isRegister
  }

}
