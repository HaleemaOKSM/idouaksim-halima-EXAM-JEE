// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { AuthRequest, AuthResponse } from '../models/models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly API_URL = '/api/auth';
  private readonly TOKEN_KEY = 'access_token';
  private readonly USER_KEY = 'current_user';

  private currentUserSubject = new BehaviorSubject<AuthResponse | null>(this.loadUser());
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  login(credentials: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, credentials).pipe(
      tap(response => {
        localStorage.setItem(this.TOKEN_KEY, response.accessToken);
        localStorage.setItem(this.USER_KEY, JSON.stringify(response));
        this.currentUserSubject.next(response);
      })
    );
  }

  logout(): void {
    localStorage.clear();
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  getToken(): string | null { return localStorage.getItem(this.TOKEN_KEY); }
  isLoggedIn(): boolean { return !!this.getToken(); }
  hasRole(role: string): boolean { return this.currentUserSubject.value?.roles?.includes(role) ?? false; }
  isAdmin(): boolean { return this.hasRole('ROLE_ADMIN'); }
  isEmploye(): boolean { return this.hasRole('ROLE_EMPLOYE'); }
  isClient(): boolean { return this.hasRole('ROLE_CLIENT'); }
  get currentUser(): AuthResponse | null { return this.currentUserSubject.value; }

  private loadUser(): AuthResponse | null {
    try { const u = localStorage.getItem(this.USER_KEY); return u ? JSON.parse(u) : null; }
    catch { return null; }
  }
}
