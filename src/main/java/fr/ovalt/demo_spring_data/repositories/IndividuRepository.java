package fr.ovalt.demo_spring_data.repositories;

import fr.ovalt.demo_spring_data.entities.Individu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividuRepository extends JpaRepository<Individu, Integer> {
}
