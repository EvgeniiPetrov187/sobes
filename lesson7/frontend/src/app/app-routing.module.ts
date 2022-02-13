import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentsComponent} from "./students/students.component";
import {NewStudentComponent} from "./new-student/new-student.component";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "student"},
  {path: "student/all", component: StudentsComponent},
  {path: "student/:id", component: NewStudentComponent},
  {path: "student/new_student", component: NewStudentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
