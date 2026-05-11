import { Component, OnInit } from '@angular/core';
import {Router, NavigationEnd, RouterOutlet} from '@angular/router';
import { filter } from 'rxjs/operators';
import { AuthService } from '../../services/authservice';
import { AuthResponse } from '../../models/models';
@Component({
  selector: 'app-layout',
  imports: [
    RouterOutlet
  ],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout implements OnInit {
  sidebarCollapsed = false;
  notifications = 3;
  currentUser: AuthResponse | null = null;
  pageTitle = 'Dashboard';

  private pageTitles: Record<string, string> = {
    '/dashboard': 'Dashboard',
    '/clients': 'Clients',
    '/contrats': 'Contrats',
    '/contrats/automobile': 'Contrats Automobile',
    '/contrats/habitation': 'Contrats Habitation',
    '/contrats/sante': 'Contrats Santé',
    '/contrats/new': 'Nouveau contrat',
    '/paiements': 'Paiements',
    '/admin/utilisateurs': 'Gestion des utilisateurs',
  };

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => this.currentUser = user);
    this.router.events
      .pipe(filter(e => e instanceof NavigationEnd))
      .subscribe((e: any) => {
        this.pageTitle = this.pageTitles[e.urlAfterRedirects] || 'AssuranceApp';
      });
  }

  get initials(): string {
    return (this.currentUser?.username || 'U').slice(0, 2).toUpperCase();
  }

  get primaryRole(): string {
    const roles = this.currentUser?.roles || [];
    if (roles.includes('ROLE_ADMIN')) return 'Administrateur';
    if (roles.includes('ROLE_EMPLOYE')) return 'Employé';
    return 'Client';
  }

  toggleSidebar(): void {
    this.sidebarCollapsed = !this.sidebarCollapsed;
  }

  logout(): void {
    this.authService.logout();
  }
}
