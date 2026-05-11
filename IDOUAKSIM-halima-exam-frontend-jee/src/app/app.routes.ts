import { Routes } from '@angular/router';
import { authGuard } from './auth/auth-guard';

export const routes: Routes = [
  {
    path: 'login',
    loadChildren: () =>
      import('./components/auth/auth').then(m => m.Auth)
  },
  {
    path: '',
    loadChildren: () =>
      import('./components/layout/layout').then(m => m.Layout),
    canActivate: [authGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
];
