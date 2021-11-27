package es.pivol.superhero.repository;

import es.pivol.superhero.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
}
