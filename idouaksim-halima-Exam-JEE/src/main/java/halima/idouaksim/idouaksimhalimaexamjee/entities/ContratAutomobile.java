package halima.idouaksim.idouaksimhalimaexamjee.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrats_automobile")
@DiscriminatorValue("AUTOMOBILE")
@PrimaryKeyJoinColumn(name = "contrat_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContratAutomobile extends ContratAssurance {

    @Column(nullable = false, length = 20)
    private String numImmatriculation;

    @Column(nullable = false, length = 50)
    private String marqueVehicule;

    @Column(nullable = false, length = 50)
    private String modeleVehicule;
}