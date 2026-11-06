package fr.ovalt.demo_spring_data.repositories;

import fr.ovalt.demo_spring_data.entities.Individu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IndividuRepositoryTest {

    @Autowired
    private IndividuRepository repository;

    @BeforeEach
    void insertData() {
        repository.save(Individu.builder().prenom("Etienne").build());
        repository.save(Individu.builder().prenom("Thomas").build());
    }

    @Test
    void findByPrenomContains_e_retourneEtienne() {
        List<Individu> trouves = repository.findByPrenomContains("e");
        assertEquals(1, trouves.size());

        Individu ind = trouves.get(0);
        assertEquals("Etienne", ind.getPrenom());
    }
}