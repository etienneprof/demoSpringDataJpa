package fr.ovalt.demo_spring_data.services;

import fr.ovalt.demo_spring_data.entities.Individu;
import fr.ovalt.demo_spring_data.repositories.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IndividuService {
    @Autowired
    private IndividuRepository repository;

    public Individu save(Individu individu) throws Exception {
        if (individu.getNom().length() < 2)
            throw new Exception("Le nom doit faire au moins 2 caractères");

        if (individu.getDateNaissance().isAfter(LocalDate.now()))
            throw new Exception("Nous n'acceptons pas les nons-nés");

        if (individu.getDateNaissance().isBefore(LocalDate.of(1900, 1, 1)))
            throw new Exception("Ta place est dans un musée");


        return repository.save(individu);
    }
}


