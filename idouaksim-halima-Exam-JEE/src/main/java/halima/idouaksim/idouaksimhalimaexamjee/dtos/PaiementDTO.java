package halima.idouaksim.idouaksimhalimaexamjee.dtos;

import halima.idouaksim.idouaksimhalimaexamjee.enums.TypePaiement;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {
    private Long id;
    private LocalDate date;
    private double montant;
    private TypePaiement type;
    private Long contratId;
}