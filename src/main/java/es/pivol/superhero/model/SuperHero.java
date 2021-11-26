package es.pivol.superhero.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SuperHero {
    @Id
    @GeneratedValue(generator = "SEC", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEC", sequenceName = "SUPERHERO_SEC",allocationSize=1)
    private Integer id;
    private String name;
}
