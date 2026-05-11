package halima.idouaksim.idouaksimhalimaexamjee.mappers;


import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContratMapper {

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientNom", source = "client.nom")
    ContratAutomobileDTO toDTO(ContratAutomobile entity);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientNom", source = "client.nom")
    ContratHabitationDTO toDTO(ContratHabitation entity);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientNom", source = "client.nom")
    ContratSanteDTO toDTO(ContratSante entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    ContratAutomobile toEntity(ContratAutomobileDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    ContratHabitation toEntity(ContratHabitationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    ContratSante toEntity(ContratSanteDTO dto);

    default ContratAssuranceDTO toDTO(ContratAssurance entity) {
        if (entity instanceof ContratAutomobile a) return toDTO(a);
        if (entity instanceof ContratHabitation h) return toDTO(h);
        if (entity instanceof ContratSante s)     return toDTO(s);
        throw new IllegalArgumentException("Type de contrat inconnu: " + entity.getClass());
    }
}