package es.pivol.superhero.persistence;

import es.pivol.superhero.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    public List<SuperHero> findByNameContainingIgnoreCase(String name);
}
