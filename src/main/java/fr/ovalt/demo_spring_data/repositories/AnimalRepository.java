package fr.ovalt.demo_spring_data.repositories;

import fr.ovalt.demo_spring_data.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
