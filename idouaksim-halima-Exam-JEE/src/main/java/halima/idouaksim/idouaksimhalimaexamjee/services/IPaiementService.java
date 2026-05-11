package halima.idouaksim.idouaksimhalimaexamjee.services;



import halima.idouaksim.idouaksimhalimaexamjee.dtos.PaiementDTO;

import java.util.List;

public interface IPaiementService {

    PaiementDTO ajouterPaiement(Long contratId, PaiementDTO dto);

    List<PaiementDTO> getPaiementsByContrat(Long contratId);

    PaiementDTO getPaiementById(Long id);

    void supprimerPaiement(Long id);

    Double getTotalPaiementsContrat(Long contratId);

    List<PaiementDTO> getAllPaiements();
}