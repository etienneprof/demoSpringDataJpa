package fr.ovalt.demo_spring_data.services;

import fr.ovalt.demo_spring_data.entities.Individu;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndividuServiceTest {
    @Autowired
    private IndividuService service;

    @Test
    void save_nominal_worksWithId() throws Exception {
        Individu aCreer = Individu.builder()
                .nom("CASSIN")
                .prenom("Etienne")
                .dateNaissance(LocalDate.of(1992,11,6))
                .build();

        Individu cree = service.save(aCreer);

        System.out.println(cree);

        assertEquals(aCreer.getNom(), cree.getNom());
        assertEquals(aCreer.getPrenom(), cree.getPrenom());
        assertEquals(aCreer.getDateNaissance(), cree.getDateNaissance());
        assertNotNull(cree.getId());
    }

    @Test
    void save_tooShortName_shouldThrowException() {
        Individu aCreer = Individu.builder()
                .nom("C")
                .prenom("Etienne")
                .dateNaissance(LocalDate.of(1992,11,6))
                .build();

        Exception e = assertThrows(Exception.class, () -> service.save(aCreer));
        assertEquals("Le nom doit faire au moins 2 caractères", e.getMessage());
    }

    @Test
    void save_futureBirthdate_shouldThrowException() {
        Individu aCreer = Individu.builder()
                .nom("CASSIN")
                .prenom("Etienne")
                .dateNaissance(LocalDate.of(2992,11,6))
                .build();

        Exception e = assertThrows(Exception.class, () -> service.save(aCreer));
        assertEquals("Nous n'acceptons pas les nons-nés", e.getMessage());
    }

    @Test
    void save_tooOldBirthdate_shouldThrowException() {
        Individu aCreer = Individu.builder()
                .nom("CASSIN")
                .prenom("Etienne")
                .dateNaissance(LocalDate.of(1892,11,6))
                .build();

        Exception e = assertThrows(Exception.class, () -> service.save(aCreer));
        assertEquals("Ta place est dans un musée", e.getMessage());
    }
}
