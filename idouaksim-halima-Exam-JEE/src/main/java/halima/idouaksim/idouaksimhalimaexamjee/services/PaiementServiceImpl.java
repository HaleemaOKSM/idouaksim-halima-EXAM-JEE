package halima.idouaksim.idouaksimhalimaexamjee.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import halima.idouaksim.idouaksimhalimaexamjee.mappers.*;
import halima.idouaksim.idouaksimhalimaexamjee.repositories.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratAssuranceRepository contratRepository;
    private final PaiementMapper mapper;

    @Override
    public PaiementDTO ajouterPaiement(Long contratId, PaiementDTO dto) {
        ContratAssurance contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec l id: " + contratId));
        Paiement paiement = mapper.toEntity(dto);
        paiement.setContrat(contrat);
        if (paiement.getDate() == null) {
            paiement.setDate(LocalDate.now());
        }
        return mapper.toDTO(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementDTO getPaiementById(Long id) {
        return mapper.toDTO(paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec l id: " + id)));
    }

    @Override
    public void supprimerPaiement(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new RuntimeException("Paiement introuvable avec l id: " + id);
        }
        paiementRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getTotalPaiementsContrat(Long contratId) {
        Double total = paiementRepository.sumMontantByContratId(contratId);
        return total != null ? total : 0.0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}