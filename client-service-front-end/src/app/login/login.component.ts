import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ClientService} from "../service/client.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.clientService.login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe({
        next: next => {
          if (next) {
            this.router.navigate(['/home']);
          } else {
            this.loading = false;
            alert("Authentication Failed.")
          }
        },
        error: error => {
          console.log(error);
          alert(error);
          this.loading = false;
        }
      });
  }
}
