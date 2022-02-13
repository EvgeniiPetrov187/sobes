import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "./student";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<Student[]>("lesson7-spring/student/all")
  }

  public findById(id:number) {
    return this.http.get<Student>(`lesson7-spring/student/${id}/id`);
  }

  public addStudent(student: Student) {
    return this.http.post('lesson7-spring/student', student);
  }
}
