package halima.idouaksim.idouaksimhalimaexamjee.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import halima.idouaksim.idouaksimhalimaexamjee.enums.StatutContrat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typeContrat")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContratAutomobileDTO.class, name = "AUTOMOBILE"),
        @JsonSubTypes.Type(value = ContratHabitationDTO.class, name = "HABITATION"),
        @JsonSubTypes.Type(value = ContratSanteDTO.class, name = "SANTE")
})
public abstract class ContratAssuranceDTO {
    private Long id;
    private LocalDate dateSouscription;
    private StatutContrat statut;
    private LocalDate dateValidation;
    private double montantCotisation;
    private int duree;
    private double tauxCouverture;
    private Long clientId;
    private String clientNom;
}

