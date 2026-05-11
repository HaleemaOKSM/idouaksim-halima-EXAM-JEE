package halima.idouaksim.idouaksimhalimaexamjee.services;


import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import halima.idouaksim.idouaksimhalimaexamjee.enums.StatutContrat;

import java.util.List;

public interface IContratService {

    ContratAutomobileDTO creerContratAutomobile(Long clientId, ContratAutomobileDTO dto);

    ContratHabitationDTO creerContratHabitation(Long clientId, ContratHabitationDTO dto);

    ContratSanteDTO creerContratSante(Long clientId, ContratSanteDTO dto);

    ContratAssuranceDTO getContratById(Long id);

    List<ContratAssuranceDTO> getAllContrats();

    List<ContratAssuranceDTO> getContratsByClient(Long clientId);

    List<ContratAssuranceDTO> getContratsByStatut(StatutContrat statut);

    ContratAssuranceDTO validerContrat(Long id);

    ContratAssuranceDTO resilierContrat(Long id);

    void supprimerContrat(Long id);

    List<ContratAutomobileDTO> getAllAutomobile();

    List<ContratHabitationDTO> getAllHabitation();

    List<ContratSanteDTO> getAllSante();
}