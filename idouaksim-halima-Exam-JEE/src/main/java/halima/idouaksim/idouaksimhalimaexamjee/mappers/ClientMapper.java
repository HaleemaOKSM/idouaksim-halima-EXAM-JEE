package halima.idouaksim.idouaksimhalimaexamjee.mappers;

import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientDTO toDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrats", ignore = true)
    Client toEntity(ClientRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrats", ignore = true)
    void updateClientFromDTO(ClientRequestDTO dto, @MappingTarget Client client);
}
