package halima.idouaksim.idouaksimhalimaexamjee.web;

import halima.idouaksim.idouaksimhalimaexamjee.dtos.ClientDTO;
import halima.idouaksim.idouaksimhalimaexamjee.dtos.ClientRequestDTO;
import halima.idouaksim.idouaksimhalimaexamjee.services.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    public ClientDTO create(@RequestBody ClientRequestDTO dto) {
        return clientService.creerClient(dto);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id,
                            @RequestBody ClientRequestDTO dto) {
        return clientService.modifierClient(id, dto);
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.supprimerClient(id);
    }

    @GetMapping("/{id}/contrats")
    public List<?> getContrats(@PathVariable Long id) {
        return clientService.getContratsClient(id);
    }
}