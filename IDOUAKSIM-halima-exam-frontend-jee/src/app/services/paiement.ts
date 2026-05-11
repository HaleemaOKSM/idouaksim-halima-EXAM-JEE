import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Paiement, TypePaiement, PageResponse } from '../models/models';

@Injectable({ providedIn: 'root' })
export class PaiementService {
  private readonly API = 'http://localhost:8080/api/paiements';
  constructor(private http: HttpClient) {}

  getAll(page = 0, size = 10, type?: TypePaiement): Observable<PageResponse<Paiement>> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (type) params = params.set('type', type);
    return this.http.get<PageResponse<Paiement>>(this.API, { params });
  }

  getByContrat(contratId: number): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(`${this.API}/contrat/${contratId}`);
  }

  create(paiement: Omit<Paiement, 'id'>): Observable<Paiement> {
    return this.http.post<Paiement>(this.API, paiement);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
