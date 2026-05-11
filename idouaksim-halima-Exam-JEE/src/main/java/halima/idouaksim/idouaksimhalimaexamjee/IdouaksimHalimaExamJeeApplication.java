package halima.idouaksim.idouaksimhalimaexamjee;

import halima.idouaksim.idouaksimhalimaexamjee.entities.*;
import halima.idouaksim.idouaksimhalimaexamjee.enums.*;
import halima.idouaksim.idouaksimhalimaexamjee.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class IdouaksimHalimaExamJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdouaksimHalimaExamJeeApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            ClientRepository clientRepository,
            ContratAutomobileRepository contratRepository,
            PaiementRepository paiementRepository
    ) {

        return args -> {

            Client client = new Client();
            client.setNom("Halima");
            client.setEmail("halima@gmail.com");
            client.setMotDePasse("1234");

            clientRepository.save(client);

            ContratAutomobile contrat = new ContratAutomobile();

            contrat.setDateSouscription(LocalDate.now());
            contrat.setStatut(StatutContrat.EN_COURS);
            contrat.setMontantCotisation(5000);
            contrat.setDuree(12);
            contrat.setTauxCouverture(80);

            contrat.setNumImmatriculation("12345A7");
            contrat.setMarqueVehicule("Dacia");
            contrat.setModeleVehicule("Logan");

            contrat.setClient(client);

            contratRepository.save(contrat);

            Paiement paiement = new Paiement();

            paiement.setDate(LocalDate.now());
            paiement.setMontant(1000);
            paiement.setType(TypePaiement.MENSUALITE);

            paiement.setContrat(contrat);

            paiementRepository.save(paiement);

            System.out.println(" CLIENTS =====");
            clientRepository.findAll().forEach(System.out::println);

            System.out.println(" CONTRATS =====");
            contratRepository.findAll().forEach(System.out::println);

            System.out.println("PAIEMENTS =====");
            paiementRepository.findAll().forEach(System.out::println);
        };
    }
}