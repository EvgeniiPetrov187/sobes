import {Component, OnInit} from '@angular/core';
import {StudentService} from "../service/student.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Student} from "../service/student";

@Component({
  selector: 'app-new-student',
  templateUrl: './new-student.component.html',
  styleUrls: ['./new-student.component.scss']
})
export class NewStudentComponent implements OnInit {

  public student = new Student(null, "", 0);

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param["id"] === "new") {
        this.student = new Student(null, "", 0);
      }
      this.studentService.findById(param["id"])
        .subscribe(student => {
          this.student = student;
        }, error => {
          console.log(error);
        });
    });
  }

  addStudent(id: number | null, name: string, age: number) {
    this.studentService.addStudent(new Student(id, name, age))
      .subscribe();
  }
}
