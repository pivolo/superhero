package es.pivol.superhero.service;

import es.pivol.superhero.model.SuperHero;

import java.util.List;
import java.util.Optional;

public interface SuperHeroService {
    List<SuperHero> findAll();

    Optional<SuperHero> findById(long id);

    List<SuperHero> findByNameContainingIgnoreCase(String name);

    void delete(SuperHero superhero);

    SuperHero create(SuperHero superheroDB);

    Optional<SuperHero> update(Long id , SuperHero superheroDB);
}
