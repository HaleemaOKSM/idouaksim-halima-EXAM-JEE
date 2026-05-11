// src/app/services/client.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client, PageResponse } from '../models/models';

@Injectable({ providedIn: 'root' })
export class ClientService {
  private readonly API = '/api/clients';
  constructor(private http: HttpClient) {}

  getAll(page = 0, size = 10, search = ''): Observable<PageResponse<Client>> {
    const params = new HttpParams().set('page', page).set('size', size).set('search', search);
    return this.http.get<PageResponse<Client>>(this.API, { params });
  }

  getById(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.API}/${id}`);
  }

  create(client: Omit<Client, 'id'>): Observable<Client> {
    return this.http.post<Client>(this.API, client);
  }

  update(id: number, client: Partial<Client>): Observable<Client> {
    return this.http.put<Client>(`${this.API}/${id}`, client);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
