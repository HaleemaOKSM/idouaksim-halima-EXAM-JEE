package halima.idouaksim.idouaksimhalimaexamjee.dtos;

import halima.idouaksim.idouaksimhalimaexamjee.enums.TypeLogement;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratAssuranceDTO {
    private TypeLogement typeLogement;
    private String adresse;
    private double superficie;
}