package halima.idouaksim.idouaksimhalimaexamjee.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratAutomobileDTO extends ContratAssuranceDTO {
    private String numImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}