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

    @Column(name = "name", length = 127, nullable = true)
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    // "One Individu to One Animal (Un individu n'a qu'un animal ET un animal n'a qu'un maitre)
    @OneToOne(cascade = CascadeType.ALL) // Toutes les actions sur individus sont répercutées sur Animal
    @JoinColumn(name = "id_animal_de_compagnie")
    private Animal animalDeCompagnie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individu")
    private List<Coordonnees> coordonnees;

    @ManyToMany // Un individu visite de multiples pays ET un pays est visité par de nombreux individus
    @JoinTable(
            name = "asso_individu_pays",
            joinColumns = @JoinColumn(name = "id_individu"),
            inverseJoinColumns = @JoinColumn(name = "id_pays_visite")
    )
    private List<Pays> paysVisites;
}
