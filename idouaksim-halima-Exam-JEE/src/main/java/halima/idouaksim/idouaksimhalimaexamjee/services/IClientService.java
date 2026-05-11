package halima.idouaksim.idouaksimhalimaexamjee.services;

import java.util.List;

import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
public interface IClientService {

    ClientDTO creerClient(ClientRequestDTO dto);

    ClientDTO modifierClient(Long id, ClientRequestDTO dto);

    ClientDTO getClientById(Long id);

    List<ClientDTO> getAllClients();

    void supprimerClient(Long id);

    List<ContratAssuranceDTO> getContratsClient(Long clientId);

    boolean existsByEmail(String email);
}
