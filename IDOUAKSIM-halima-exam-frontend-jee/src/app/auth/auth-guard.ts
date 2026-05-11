import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from '../services/authservice';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
      return false;
    }
    const roles = route.data['roles'] as string[];
    if (roles && roles.length > 0) {
      const hasRole = roles.some(r => this.authService.hasRole(r));
      if (!hasRole) {
        this.router.navigate(['/unauthorized']);
        return false;
      }
    }
    return true;
  }
}
