package halima.idouaksim.idouaksimhalimaexamjee.web;

import halima.idouaksim.idouaksimhalimaexamjee.dtos.PaiementDTO;
import halima.idouaksim.idouaksimhalimaexamjee.services.IPaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    // ✅ CREATE paiement for a contract
    @PostMapping("/contrat/{contratId}")
    public PaiementDTO create(@PathVariable Long contratId,
                              @RequestBody PaiementDTO dto) {
        return paiementService.ajouterPaiement(contratId, dto);
    }

    // GET ALL
    @GetMapping
    public List<PaiementDTO> getAll() {
        return paiementService.getAllPaiements();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PaiementDTO getById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paiementService.supprimerPaiement(id);
    }

    // GET BY CONTRAT
    @GetMapping("/contrat/{contratId}")
    public List<PaiementDTO> getByContrat(@PathVariable Long contratId) {
        return paiementService.getPaiementsByContrat(contratId);
    }

    // TOTAL PAYMENTS
    @GetMapping("/total/{contratId}")
    public Double total(@PathVariable Long contratId) {
        return paiementService.getTotalPaiementsContrat(contratId);
    }
}