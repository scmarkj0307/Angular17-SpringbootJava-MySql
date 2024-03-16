import { Component } from '@angular/core';
import { RecipeCardComponent } from '../../components/recipe-card/recipe-card.component';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../../components/create-recipe-form/create-recipe-form.component';

@Component({
  selector: 'app-home-screen',
  standalone: true,
  imports: [RecipeCardComponent,MatIconModule,MatButtonModule],
  templateUrl: './home-screen.component.html',
  styleUrl: './home-screen.component.scss'
})
export class HomeScreenComponent {

  recipes=[1,1,1,1,1,1]

  constructor(public dialog: MatDialog){}

  handleOpenCreateRecipeForm(){
    this.dialog.open(CreateRecipeFormComponent)
  }

}
