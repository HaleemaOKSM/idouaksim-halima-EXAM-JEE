package halima.idouaksim.idouaksimhalimaexamjee.entities;


import halima.idouaksim.idouaksimhalimaexamjee.enums.NiveauCouverture;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrats_sante")
@DiscriminatorValue("SANTE")
@PrimaryKeyJoinColumn(name = "contrat_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContratSante extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NiveauCouverture niveauCouverture;

    @Column(nullable = false)
    private int nbPersonnesCouvertes;
}