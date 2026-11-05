package fr.ovalt.demo_spring_data.controllers;

import fr.ovalt.demo_spring_data.entities.Individu;
import fr.ovalt.demo_spring_data.repositories.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndividuController {
    @Autowired
    private IndividuRepository individuRepository;

    @GetMapping("/individus")
    public List<Individu> getAll() {
        return individuRepository.findAll();
    }

    @PostMapping("/individus")
    public void insert(@RequestBody Individu individu) {
        individuRepository.save(individu);
    }
}
