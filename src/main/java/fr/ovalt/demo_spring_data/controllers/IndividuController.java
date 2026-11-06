package fr.ovalt.demo_spring_data.controllers;

import fr.ovalt.demo_spring_data.entities.Individu;
import fr.ovalt.demo_spring_data.repositories.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndividuController {

    private final IndividuRepository individuRepository;

    @Autowired
    public IndividuController(IndividuRepository individuRepository) {
        this.individuRepository = individuRepository;
    }

    @GetMapping("/individus")
    public List<Individu> getAll() {
        return individuRepository.findAll();
    }

    @PostMapping("/individus")
    public void insert(@RequestBody Individu individu) {
        individuRepository.save(individu);
    }

    @GetMapping("/individus/prenom/{pattern}")
    public List<Individu> findByPrenom(@PathVariable("pattern") String prenom){
        return individuRepository.findByPrenomContains(prenom);
    }

    @GetMapping("/individus/animal/{cri}")
    public List<Individu> findByCriAnimal(@PathVariable("cri") String cri){
        return individuRepository.findByAnimalDeCompagnieCriIs(cri);
    }
}
