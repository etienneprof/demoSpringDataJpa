package fr.ovalt.demo_spring_data.repositories;

import fr.ovalt.demo_spring_data.entities.Individu;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividuRepository extends JpaRepository<Individu, Integer> {

    public List<Individu> findByPrenomContains(String pattern); // SELECT * FROM individus WHERE prenom LIKE '%pattern%';
    public List<Individu> findByAnimalDeCompagnieCriIs(String cri);

    /*
    @Query(value = "SELECT * FROM individus", nativeQuery = true) // SQL natif : si changement de base, syntaxe à changer
    public List<Individu> findByRequeteTresComplexeenSQL(String param1, String param2);

    @Query(value = "SELECT * FROM individus") // HQL : la syntaxe s'adapte automatiquement
    public List<Individu> findByRequeteTresComplexeenHQL(String param1, String param2);

     */
}
