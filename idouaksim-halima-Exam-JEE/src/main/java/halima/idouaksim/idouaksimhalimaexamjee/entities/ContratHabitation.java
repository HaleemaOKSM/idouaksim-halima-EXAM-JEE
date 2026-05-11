package halima.idouaksim.idouaksimhalimaexamjee.entities;

import halima.idouaksim.idouaksimhalimaexamjee.enums.TypeLogement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrats_habitation")
@DiscriminatorValue("HABITATION")
@PrimaryKeyJoinColumn(name = "contrat_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContratHabitation extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TypeLogement typeLogement;

    @Column(nullable = false, length = 200)
    private String adresse;

    @Column(nullable = false)
    private double superficie;
}
