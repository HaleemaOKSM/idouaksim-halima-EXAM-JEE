package halima.idouaksim.idouaksimhalimaexamjee.web;

import halima.idouaksim.idouaksimhalimaexamjee.dtos.*;
import halima.idouaksim.idouaksimhalimaexamjee.enums.StatutContrat;
import halima.idouaksim.idouaksimhalimaexamjee.services.IContratService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    // ================= AUTOMOBILE =================
    @PostMapping("/automobile/{clientId}")
    public ContratAutomobileDTO createAuto(@PathVariable Long clientId,
                                           @RequestBody ContratAutomobileDTO dto) {
        return contratService.creerContratAutomobile(clientId, dto);
    }

    // ================= HABITATION =================
    @PostMapping("/habitation/{clientId}")
    public ContratHabitationDTO createHabitation(@PathVariable Long clientId,
                                                 @RequestBody ContratHabitationDTO dto) {
        return contratService.creerContratHabitation(clientId, dto);
    }

    // ================= SANTE =================
    @PostMapping("/sante/{clientId}")
    public ContratSanteDTO createSante(@PathVariable Long clientId,
                                       @RequestBody ContratSanteDTO dto) {
        return contratService.creerContratSante(clientId, dto);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<ContratAssuranceDTO> getAll() {
        return contratService.getAllContrats();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ContratAssuranceDTO getById(@PathVariable Long id) {
        return contratService.getContratById(id);
    }

    // ================= BY CLIENT =================
    @GetMapping("/client/{clientId}")
    public List<ContratAssuranceDTO> getByClient(@PathVariable Long clientId) {
        return contratService.getContratsByClient(clientId);
    }

    // ================= BY STATUS =================
    @GetMapping("/statut/{statut}")
    public List<ContratAssuranceDTO> getByStatut(@PathVariable StatutContrat statut) {
        return contratService.getContratsByStatut(statut);
    }

    // ================= VALIDATION =================
    @PutMapping("/valider/{id}")
    public ContratAssuranceDTO valider(@PathVariable Long id) {
        return contratService.validerContrat(id);
    }

    // ================= RESILIATION =================
    @PutMapping("/resilier/{id}")
    public ContratAssuranceDTO resilier(@PathVariable Long id) {
        return contratService.resilierContrat(id);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contratService.supprimerContrat(id);
    }

    // ================= TYPE LISTS =================
    @GetMapping("/automobile")
    public List<ContratAutomobileDTO> getAuto() {
        return contratService.getAllAutomobile();
    }

    @GetMapping("/habitation")
    public List<ContratHabitationDTO> getHabitation() {
        return contratService.getAllHabitation();
    }

    @GetMapping("/sante")
    public List<ContratSanteDTO> getSante() {
        return contratService.getAllSante();
    }
}