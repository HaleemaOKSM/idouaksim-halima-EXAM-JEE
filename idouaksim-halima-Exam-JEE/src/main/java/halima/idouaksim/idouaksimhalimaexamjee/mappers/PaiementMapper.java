package halima.idouaksim.idouaksimhalimaexamjee.mappers;


import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaiementMapper {

    @Mapping(target = "contratId", source = "contrat.id")
    PaiementDTO toDTO(Paiement paiement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    Paiement toEntity(PaiementDTO dto);
}