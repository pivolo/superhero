package es.pivol.superhero.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SUPERHEROS")
public class SuperHero {
    @Id
    @GeneratedValue(generator = "SEC", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEC", sequenceName = "SUPERHERO_SEC",allocationSize=1)
    private Long id;
    private String name;
}
