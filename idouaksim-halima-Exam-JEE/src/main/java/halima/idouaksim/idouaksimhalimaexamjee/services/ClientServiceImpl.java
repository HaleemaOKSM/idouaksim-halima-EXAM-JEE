package halima.idouaksim.idouaksimhalimaexamjee.services;

import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import halima.idouaksim.idouaksimhalimaexamjee.mappers.*;
import halima.idouaksim.idouaksimhalimaexamjee.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ContratAssuranceRepository contratRepository;
    private final ClientMapper clientMapper;
    private final ContratMapper contratMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientDTO creerClient(ClientRequestDTO dto) {
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un client avec cet email existe déjà: " + dto.getEmail());
        }
        Client client = clientMapper.toEntity(dto);
        client.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO modifierClient(Long id, ClientRequestDTO dto) {
        Client client = findClientOrThrow(id);
        clientMapper.updateClientFromDTO(dto, client);
        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isBlank()) {
            client.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        }
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id) {
        return clientMapper.toDTO(findClientOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void supprimerClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client introuvable avec l id: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAssuranceDTO> getContratsClient(Long clientId) {
        findClientOrThrow(clientId);
        return contratRepository.findByClientId(clientId)
                .stream()
                .map(contratMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    private Client findClientOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l id: " + id));
    }
}