package es.pivol.superhero.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUPER_HEROES")
public class SuperHero {
    @Id
    @GeneratedValue(generator = "SEC", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEC", sequenceName = "SUPERHERO_SEC",allocationSize=1)
    private Long id;
    private String name;
}
