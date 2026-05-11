package halima.idouaksim.idouaksimhalimaexamjee.entities;

import halima.idouaksim.idouaksimhalimaexamjee.enums.TypePaiement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "paiements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TypePaiement type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrat_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ContratAssurance contrat;
}