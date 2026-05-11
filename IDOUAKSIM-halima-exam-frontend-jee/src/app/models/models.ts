// ===== MODELS / INTERFACES =====

export interface Client {
  id: number;
  nom: string;
  email: string;
  contrats?: Contrat[];
}

export type StatutContrat = 'EN_COURS' | 'VALIDE' | 'RESILIE';
export type TypePaiement = 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';
export type TypeLogement = 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
export type NiveauCouverture = 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';
export type TypeContrat = 'AUTOMOBILE' | 'HABITATION' | 'SANTE';

export interface Contrat {
  id: number;
  datesouscription: string;
  statut: StatutContrat;
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  typeContrat: TypeContrat;
  clientId: number;
  clientNom?: string;
}

export interface ContratAutomobile extends Contrat {
  numeroImmatriculation: string;
  marqueVehicule: string;
  modeleVehicule: string;
}

export interface ContratHabitation extends Contrat {
  typeLogement: TypeLogement;
  adresseLogement: string;
  superficie: number;
}

export interface ContratSante extends Contrat {
  niveauCouverture: NiveauCouverture;
  nombrePersonnesCouvertes: number;
}

export interface Paiement {
  id: number;
  datePaiement: string;
  montant: number;
  typePaiement: TypePaiement;
  contratId: number;
}

export interface AppUser {
  id: number;
  username: string;
  email: string;
  roles: string[];
  active: boolean;
}

export interface AuthRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  accessToken: string;
  refreshToken?: string;
  username: string;
  roles: string[];
}

// DTOs
export interface ContratDTO {
  id?: number;
  dateSouscription: string;
  statut: StatutContrat;
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  typeContrat: TypeContrat;
  clientId: number;
  // Auto
  numeroImmatriculation?: string;
  marqueVehicule?: string;
  modeleVehicule?: string;
  // Habitation
  typeLogement?: TypeLogement;
  adresseLogement?: string;
  superficie?: number;
  // Sante
  niveauCouverture?: NiveauCouverture;
  nombrePersonnesCouvertes?: number;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}
