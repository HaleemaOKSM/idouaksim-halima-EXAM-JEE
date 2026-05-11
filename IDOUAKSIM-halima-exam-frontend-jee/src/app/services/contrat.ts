import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  ContratDTO, PageResponse, StatutContrat, TypeContrat,
  ContratAutomobile, ContratHabitation, ContratSante
} from '../models/models';

@Injectable({ providedIn: 'root' })
export class ContratService {
  private readonly API = 'http://localhost:8080/api/contrats';
  constructor(private http: HttpClient) {}

  getAll(page = 0, size = 10, type?: TypeContrat, statut?: StatutContrat): Observable<PageResponse<ContratDTO>> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (type) params = params.set('type', type);
    if (statut) params = params.set('statut', statut);
    return this.http.get<PageResponse<ContratDTO>>(this.API, { params });
  }

  getById(id: number): Observable<ContratDTO> {
    return this.http.get<ContratDTO>(`${this.API}/${id}`);
  }

  createAuto(dto: ContratAutomobile): Observable<ContratAutomobile> {
    return this.http.post<ContratAutomobile>(`${this.API}/automobile`, dto);
  }

  createHabitation(dto: ContratHabitation): Observable<ContratHabitation> {
    return this.http.post<ContratHabitation>(`${this.API}/habitation`, dto);
  }

  createSante(dto: ContratSante): Observable<ContratSante> {
    return this.http.post<ContratSante>(`${this.API}/sante`, dto);
  }

  update(id: number, dto: Partial<ContratDTO>): Observable<ContratDTO> {
    return this.http.put<ContratDTO>(`${this.API}/${id}`, dto);
  }

  updateStatut(id: number, statut: StatutContrat): Observable<ContratDTO> {
    return this.http.patch<ContratDTO>(`${this.API}/${id}/statut`, { statut });
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }

  getByClientId(clientId: number): Observable<ContratDTO[]> {
    return this.http.get<ContratDTO[]>(`${this.API}/client/${clientId}`);
  }

  getAutomobiles(): Observable<ContratAutomobile[]> {
    return this.http.get<ContratAutomobile[]>(`${this.API}/automobile`);
  }

  getHabitations(): Observable<ContratHabitation[]> {
    return this.http.get<ContratHabitation[]>(`${this.API}/habitation`);
  }

  getSantes(): Observable<ContratSante[]> {
    return this.http.get<ContratSante[]>(`${this.API}/sante`);
  }
}
