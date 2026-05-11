package halima.idouaksim.idouaksimhalimaexamjee.repositories;

import halima.idouaksim.idouaksimhalimaexamjee.entities.ContratSante;
import halima.idouaksim.idouaksimhalimaexamjee.enums.NiveauCouverture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratSanteRepository extends JpaRepository<ContratSante, Long> {
    List<ContratSante> findByNiveauCouverture(NiveauCouverture niveau);
}