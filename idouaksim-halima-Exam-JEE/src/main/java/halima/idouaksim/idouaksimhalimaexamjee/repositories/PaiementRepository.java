package halima.idouaksim.idouaksimhalimaexamjee.repositories;

import halima.idouaksim.idouaksimhalimaexamjee.entities.Paiement;
import halima.idouaksim.idouaksimhalimaexamjee.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContratId(Long contratId);
    List<Paiement> findByType(TypePaiement type);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.id = :contratId")
    Double sumMontantByContratId(Long contratId);
}
