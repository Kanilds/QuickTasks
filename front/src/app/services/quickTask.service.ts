import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { QuickTask } from "../models/quickTask";

@Injectable({
  providedIn: "root",
})
export class QuickTaskService {
  baseUrl = environment.baseUrl;
  baseUrlLogin = environment.baseUrlLogin;

  constructor(private http: HttpClient, private snack: MatSnackBar) {}

  findAll(): Observable<QuickTask[]> {
    
    return this.http.get<QuickTask[]>(this.baseUrl);
  }

  findById(id: any): Observable<QuickTask> {
    
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<QuickTask>(url);
  }

  update(quickTask: QuickTask): Observable<QuickTask> {
    
    const url = `${this.baseUrl}/${quickTask.id}`;
    return this.http.put<QuickTask>(url, quickTask);
  }

  delete(id: any): Observable<void> {
   
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  create(quickTask: QuickTask): Observable<QuickTask> {
    
    return this.http.post<QuickTask>(this.baseUrl, quickTask);
  }

  message(msg: String): void {
    this.snack.open(`${msg}`, "OK", {
      horizontalPosition: "end",
      verticalPosition: "top",
      duration: 4000,
    });
  }
}
