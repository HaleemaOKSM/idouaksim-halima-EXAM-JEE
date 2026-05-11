
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';
import { Client } from '../../models/models';
import { ClientService } from '../../services/client';
import { AuthService } from '../../services/authservice';


@Component({
  selector: 'app-clients',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './clients.html',
  styleUrl: './clients.css',
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  totalElements = 0;
  totalPages = 0;
  currentPage = 0;
  pageSize = 10;
  searchTerm = '';
  showModal = false;
  editMode = false;
  selectedClientId?: number;
  clientForm!: FormGroup;
  loading = false;
  successMsg = '';
  errorMsg = '';
  clientsAvecContrat = 0;
  nouveauxCeMois = 0;

  private searchSubject = new Subject<string>();

  constructor(
    public authService: AuthService,
    private clientService: ClientService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadClients();
    this.searchSubject.pipe(debounceTime(300), distinctUntilChanged())
      .subscribe(term => { this.currentPage = 0; this.loadClients(); });
  }

  initForm(): void {
    this.clientForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  get f() { return this.clientForm.controls; }

  loadClients(): void {
    this.clientService.getAll(this.currentPage, this.pageSize, this.searchTerm)
      .subscribe({
        next: (data) => {
          this.clients = data.content;
          this.totalElements = data.totalElements;
          this.totalPages = data.totalPages;
          this.clientsAvecContrat = this.clients.filter(c => (c.contrats?.length || 0) > 0).length;
        },
        error: () => this.errorMsg = 'Erreur lors du chargement des clients'
      });
  }

  onSearch(): void {
    this.searchSubject.next(this.searchTerm);
  }

  openModal(): void {
    this.editMode = false;
    this.clientForm.reset();
    this.clearMessages();
    this.showModal = true;
  }

  editClient(client: Client): void {
    this.editMode = true;
    this.selectedClientId = client.id;
    this.clientForm.patchValue({ nom: client.nom, email: client.email });
    this.clearMessages();
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.clearMessages();
  }

  saveClient(): void {
    if (this.clientForm.invalid) { this.clientForm.markAllAsTouched(); return; }
    this.loading = true;
    const data = this.clientForm.value;
    const req = this.editMode && this.selectedClientId
      ? this.clientService.update(this.selectedClientId, data)
      : this.clientService.create(data);
    req.subscribe({
      next: () => {
        this.successMsg = this.editMode ? 'Client mis à jour' : 'Client créé avec succès';
        this.loading = false;
        this.loadClients();
        setTimeout(() => this.closeModal(), 1000);
      },
      error: () => {
        this.errorMsg = 'Erreur lors de l\'enregistrement';
        this.loading = false;
      }
    });
  }

  deleteClient(id: number): void {
    if (!confirm('Confirmer la suppression ?')) return;
    this.clientService.delete(id).subscribe({
      next: () => this.loadClients(),
      error: () => alert('Erreur lors de la suppression')
    });
  }

  viewClient(client: Client): void {
    // Navigate to client detail
  }

  goToPage(page: number): void {
    if (page < 0 || page >= this.totalPages) return;
    this.currentPage = page;
    this.loadClients();
  }

  get pages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i);
  }

  getInitials(nom: string): string {
    return nom.split(' ').map(n => n[0]).join('').slice(0, 2).toUpperCase();
  }

  getAvatarBg(nom: string): string {
    const colors = ['#1a56db', '#0f6e56', '#ba7517', '#a32d2d', '#7c3aed'];
    return colors[nom.charCodeAt(0) % colors.length];
  }

  private clearMessages(): void {
    this.successMsg = '';
    this.errorMsg = '';
  }
}
