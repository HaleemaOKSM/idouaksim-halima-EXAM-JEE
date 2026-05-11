package halima.idouaksim.idouaksimhalimaexamjee.repositories;

import halima.idouaksim.idouaksimhalimaexamjee.entities.ContratAutomobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratAutomobileRepository extends JpaRepository<ContratAutomobile, Long> {
    List<ContratAutomobile> findByMarqueVehicule(String marque);
    List<ContratAutomobile> findByNumImmatriculation(String numImmat);
}

