package halima.idouaksim.idouaksimhalimaexamjee.repositories;

import halima.idouaksim.idouaksimhalimaexamjee.entities.ContratHabitation;
import halima.idouaksim.idouaksimhalimaexamjee.enums.TypeLogement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratHabitationRepository extends JpaRepository<ContratHabitation, Long> {
    List<ContratHabitation> findByTypeLogement(TypeLogement type);
}