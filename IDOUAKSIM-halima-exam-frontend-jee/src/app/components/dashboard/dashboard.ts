import { Component, OnInit } from '@angular/core';
import { ContratService } from '../../services/contrat';
import { ContratDTO } from '../../models/models';
import {DatePipe, DecimalPipe} from '@angular/common';

interface DashStats {
  totalClients: number;
  contratsActifs: number;
  totalContrats: number;
  paiementsMois: number;
  contratsResilies: number;
  contratsValides: number;
  contratsEnCours: number;
  contratsAuto: number;
  contratsHab: number;
  contratsSante: number;
}

@Component({
  selector: 'app-dashboard',
  imports: [
    DecimalPipe,
    DatePipe
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  derniersContrats: ContratDTO[] = [];
  stats: DashStats = {
    totalClients: 128, contratsActifs: 247, totalContrats: 261,
    paiementsMois: 38400, contratsResilies: 14, contratsValides: 183,
    contratsEnCours: 64, contratsAuto: 99, contratsHab: 91, contratsSante: 71
  };

  constructor(private contratService: ContratService) {}

  ngOnInit(): void {
    this.contratService.getAll(0, 5).subscribe({
      next: (data) => { this.derniersContrats = data.content; }
    });
  }

  getDonut(value: number, total: number): number {
    if (!total) return 0;
    return Math.round((value / total) * 100);
  }
}

