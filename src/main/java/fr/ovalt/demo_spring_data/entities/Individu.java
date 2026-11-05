package fr.ovalt.demo_spring_data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "individus")
public class Individu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    // "One Individu to One Animal (Un individu n'a qu'un animal ET un animal n'a qu'un maitre)
    @OneToOne(cascade = CascadeType.ALL) // Toutes les actions sur individus sont répercutées sur Animal
    private Animal animalDeCompagnie;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Coordonnees> coordonnees;

    @ManyToMany // Un individu visite de multiples pays ET un pays est visité par de nombreux individus
    private List<Pays> paysVisites;
}
