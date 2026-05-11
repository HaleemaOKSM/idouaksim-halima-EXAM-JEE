import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../services/authservice';
@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  error = '';
  showPwd = false;
  returnUrl = '/dashboard';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
) {}

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
    this.router.navigate(['/dashboard']);
    return;
  }
  this.loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
}

  get f() { return this.loginForm.controls; }

  fillCredentials(username: string, password: string): void {
    this.loginForm.patchValue({ username, password });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
    this.loginForm.markAllAsTouched();
    return;
  }
  this.loading = true;
  this.error = '';
  this.authService.login(this.loginForm.value).subscribe({
    next: () => this.router.navigate([this.returnUrl]),
    error: (err) => {
      this.error = err.status === 401
        ? 'Identifiants incorrects'
        : 'Erreur de connexion. Réessayez.';
      this.loading = false;
    }
  });
}
}
