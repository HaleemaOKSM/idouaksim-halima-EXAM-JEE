package halima.idouaksim.idouaksimhalimaexamjee.services;


import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.enums.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import halima.idouaksim.idouaksimhalimaexamjee.mappers.*;
import halima.idouaksim.idouaksimhalimaexamjee.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final ContratAssuranceRepository contratRepository;
    private final ContratAutomobileRepository autoRepository;
    private final ContratHabitationRepository habRepository;
    private final ContratSanteRepository santeRepository;
    private final ClientRepository clientRepository;
    private final ContratMapper mapper;

    @Override
    public ContratAutomobileDTO creerContratAutomobile(Long clientId, ContratAutomobileDTO dto) {
        Client client = findClientOrThrow(clientId);
        ContratAutomobile contrat = mapper.toEntity(dto);
        contrat.setClient(client);
        contrat.setStatut(StatutContrat.EN_COURS);
        contrat.setDateSouscription(LocalDate.now());
        return mapper.toDTO(autoRepository.save(contrat));
    }

    @Override
    public ContratHabitationDTO creerContratHabitation(Long clientId, ContratHabitationDTO dto) {
        Client client = findClientOrThrow(clientId);
        ContratHabitation contrat = mapper.toEntity(dto);
        contrat.setClient(client);
        contrat.setStatut(StatutContrat.EN_COURS);
        contrat.setDateSouscription(LocalDate.now());
        return mapper.toDTO(habRepository.save(contrat));
    }

    @Override
    public ContratSanteDTO creerContratSante(Long clientId, ContratSanteDTO dto) {
        Client client = findClientOrThrow(clientId);
        ContratSante contrat = mapper.toEntity(dto);
        contrat.setClient(client);
        contrat.setStatut(StatutContrat.EN_COURS);
        contrat.setDateSouscription(LocalDate.now());
        return mapper.toDTO(santeRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public ContratAssuranceDTO getContratById(Long id) {
        return mapper.toDTO(findContratOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAssuranceDTO> getAllContrats() {
        return contratRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAssuranceDTO> getContratsByClient(Long clientId) {
        findClientOrThrow(clientId);
        return contratRepository.findByClientId(clientId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAssuranceDTO> getContratsByStatut(StatutContrat statut) {
        return contratRepository.findByStatut(statut).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ContratAssuranceDTO validerContrat(Long id) {
        ContratAssurance contrat = findContratOrThrow(id);
        if (contrat.getStatut() == StatutContrat.RESILIE) {
            throw new RuntimeException("Impossible de valider un contrat résilié");
        }
        contrat.setStatut(StatutContrat.VALIDE);
        contrat.setDateValidation(LocalDate.now());
        return mapper.toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratAssuranceDTO resilierContrat(Long id) {
        ContratAssurance contrat = findContratOrThrow(id);
        contrat.setStatut(StatutContrat.RESILIE);
        return mapper.toDTO(contratRepository.save(contrat));
    }

    @Override
    public void supprimerContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new RuntimeException("Contrat introuvable avec l id: " + id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAutomobileDTO> getAllAutomobile() {
        return autoRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratHabitationDTO> getAllHabitation() {
        return habRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratSanteDTO> getAllSante() {
        return santeRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    private ContratAssurance findContratOrThrow(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec l id: " + id));
    }

    private Client findClientOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l id: " + id));
    }
}