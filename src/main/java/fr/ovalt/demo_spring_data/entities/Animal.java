package fr.ovalt.demo_spring_data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "animaux")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String cri;

    @OneToOne(mappedBy = "animalDeCompagnie") // Pour éviter de créer 2 jointures identiques
    private Individu maitre;
}
