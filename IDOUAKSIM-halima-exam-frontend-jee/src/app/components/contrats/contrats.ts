import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { ContratDTO, StatutContrat, TypeContrat, Client } from '../../models/models';
import { ContratService } from '../../services/contrat';
import { ClientService } from '../../services/client';
import { AuthService } from '../../services/authservice';
import {Observable} from 'rxjs';
import {DatePipe, DecimalPipe, NgClass} from '@angular/common';
@Component({
  selector: 'app-contrats',
  imports: [
    ReactiveFormsModule,
    DatePipe,
    DecimalPipe,
    NgClass,
    FormsModule
  ],
  templateUrl: './contrats.html',
  styleUrl: './contrats.css',
})
export class Contrats implements OnInit {
  contrats: ContratDTO[] = [];
  clientsList: Client[] = [];
  totalElements = 0;
  totalPages = 0;
  currentPage = 0;
  searchTerm = '';
  filterType = '';
  filterStatut = '';
  showModal = false;
  activeTab: 'general' | 'specifique' = 'general';
  contratForm!: FormGroup;
  loading = false;

  constructor(
    public authService: AuthService,
    private contratService: ContratService,
    private clientService: ClientService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadContrats();
    this.loadClients();
  }

  initForm(): void {
    this.contratForm = this.fb.group({
      clientId: ['', Validators.required],
      typeContrat: ['AUTOMOBILE', Validators.required],
      dateSouscription: [new Date().toISOString().split('T')[0], Validators.required],
      statut: ['EN_COURS'],
      montantCotisation: ['', [Validators.required, Validators.min(0)]],
      dureeContrat: ['', [Validators.required, Validators.min(1)]],
      tauxCouverture: ['', [Validators.required, Validators.min(0), Validators.max(100)]],
      automobile: this.fb.group({
        numeroImmatriculation: [''],
        marqueVehicule: [''],
        modeleVehicule: ['']
      }),
      habitation: this.fb.group({
        typeLogement: ['APPARTEMENT'],
        adresseLogement: [''],
        superficie: ['']
      }),
      sante: this.fb.group({
        niveauCouverture: ['BASIQUE'],
        nombrePersonnesCouvertes: [1]
      })
    });
  }

  loadContrats(): void {
    this.contratService.getAll(
      this.currentPage, 10,
      this.filterType as TypeContrat || undefined,
      this.filterStatut as StatutContrat || undefined
    ).subscribe({ next: (data) => {
        this.contrats = data.content;
        this.totalElements = data.totalElements;
        this.totalPages = data.totalPages;
      }});
  }

  loadClients(): void {
    this.clientService.getAll(0, 100).subscribe({
      next: (data) => this.clientsList = data.content
    });
  }

  onTypeChange(): void {
    // Type-specific validation could be added here
  }

  openModal(): void {
    this.contratForm.reset({ typeContrat: 'AUTOMOBILE', statut: 'EN_COURS',
      dateSouscription: new Date().toISOString().split('T')[0] });
    this.activeTab = 'general';
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  saveContrat(): void {
    if (this.contratForm.invalid) return;

    this.loading = true;
    const val = this.contratForm.value;

    const base = {
      clientId: val.clientId,
      typeContrat: val.typeContrat,
      dateSouscription: val.dateSouscription,
      statut: val.statut,
      montantCotisation: val.montantCotisation,
      dureeContrat: val.dureeContrat,
      tauxCouverture: val.tauxCouverture
    };

    let req!: Observable<any>;

    if (val.typeContrat === 'AUTOMOBILE') {
      req = this.contratService.createAuto({ ...base, ...val.automobile });
    } else if (val.typeContrat === 'HABITATION') {
      req = this.contratService.createHabitation({ ...base, ...val.habitation });
    } else {
      req = this.contratService.createSante({ ...base, ...val.sante });
    }

    req.subscribe({
      next: () => {
        this.closeModal();
        this.loadContrats();
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }
  viewContrat(c: ContratDTO): void {
    // Navigate to contrat detail
  }

  changeStatut(c: ContratDTO): void {
    const next: Record<StatutContrat, StatutContrat> = {
      EN_COURS: 'VALIDE', VALIDE: 'RESILIE', RESILIE: 'EN_COURS'
    };
    this.contratService.updateStatut(c.id!, next[c.statut]).subscribe({
      next: () => this.loadContrats()
    });
  }

  getStatutClass(statut: StatutContrat): string {
    const map: Record<StatutContrat, string> = {
      EN_COURS: 'badge-warning', VALIDE: 'badge-success', RESILIE: 'badge-danger'
    };
    return map[statut];
  }

  getStatutLabel(statut: StatutContrat): string {
    const map: Record<StatutContrat, string> = {
      EN_COURS: 'En cours', VALIDE: 'Validé', RESILIE: 'Résilié'
    };
    return map[statut];
  }
}
