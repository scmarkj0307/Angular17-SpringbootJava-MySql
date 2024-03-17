import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {

  private baseUrl = 'http://localhost:8000/';

  constructor(private http:HttpClient) { }

  recipeSubject = new BehaviorSubject<{ recipes: any[]; loading: boolean; newRecipe: any | null; }>({
    recipes: [],
    loading: false,
    newRecipe: null,
  });

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem("jwt");
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`
    });
  }

  //get recipes
  getRecipes(): Observable<any[]> {
    const headers = this.getHeaders();
    return this.http.get<any[]>(`${this.baseUrl}/api/recipe`, { headers }).pipe(
      tap((recipes: any[]) => {
        const currentState = this.recipeSubject.value;
        this.recipeSubject.next({ ...currentState, recipes });
      })
    );
  }

  //post recipes
  createRecipe(recipe: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.baseUrl}/api/recipe`, recipe, { headers }).pipe(
      tap((newRecipe) => {
        const currentState = this.recipeSubject.value;
        this.recipeSubject.next({ ...currentState, recipes: [newRecipe, ...currentState.recipes] });
      })
    );
  }

  //put recipes
  updateRecipes(recipe: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.baseUrl}/api/recipe/${recipe.id}`, recipe, { headers }).pipe(
      tap((updatedRecipe: any) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item: any) => item.id === updatedRecipe.id ? updatedRecipe : item);
        this.recipeSubject.next({ ...currentState, recipes: updatedRecipes });
      })
    );
  }

  //delete recipes
  deleteRecipes(id: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.delete<any>(`${this.baseUrl}/api/recipe/${id}`,{ headers }).pipe(
      tap((deletedRecipe: any) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.filter((item: any) => item.id !== id);
        this.recipeSubject.next({ ...currentState, recipes: updatedRecipes });
      })
    );
  }

  //like recipes
  likeRecipes(id: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.baseUrl}/api/recipe/${id}/like`, { headers }).pipe(
      tap((updatedRecipe: any) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item: any) => item.id === updatedRecipe.id ? updatedRecipe : item);
        this.recipeSubject.next({ ...currentState, recipes: updatedRecipes });
      })
    );
  }
}
