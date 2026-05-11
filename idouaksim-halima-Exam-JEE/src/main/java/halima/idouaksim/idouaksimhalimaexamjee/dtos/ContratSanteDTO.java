package halima.idouaksim.idouaksimhalimaexamjee.dtos;


import halima.idouaksim.idouaksimhalimaexamjee.enums.NiveauCouverture;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratAssuranceDTO {
    private NiveauCouverture niveauCouverture;
    private int nbPersonnesCouvertes;
}