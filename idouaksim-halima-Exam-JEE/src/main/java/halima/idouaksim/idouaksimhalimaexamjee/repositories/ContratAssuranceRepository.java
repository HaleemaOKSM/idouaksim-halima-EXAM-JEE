package halima.idouaksim.idouaksimhalimaexamjee.repositories;

import halima.idouaksim.idouaksimhalimaexamjee.entities.ContratAssurance;
import halima.idouaksim.idouaksimhalimaexamjee.enums.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {
    List<ContratAssurance> findByClientId(Long clientId);
    List<ContratAssurance> findByStatut(StatutContrat statut);

    @Query("SELECT c FROM ContratAssurance c WHERE c.client.id = :clientId AND c.statut = :statut")
    List<ContratAssurance> findByClientIdAndStatut(Long clientId, StatutContrat statut);

    long countByClientId(Long clientId);
}