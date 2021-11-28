package es.pivol.superhero.service;

import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.persistence.SuperHeroRepository;
import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Data
@CacheConfig(cacheNames = "superhero")
public class SuperHeroServiceImpl implements SuperHeroService {
    private final SuperHeroRepository superHeroRepository;

    @Cacheable(value = "allSuperheroesCache")
    @Override
    public List<SuperHero> findAll() {
        return superHeroRepository.findAll();
    }

    @Cacheable(value = "superheroCache")
    @Override
    public Optional<SuperHero> findById(long id) {
        return superHeroRepository.findById(id);
    }

    @Override
    public List<SuperHero> findByNameContainingIgnoreCase(String name) {
        return superHeroRepository.findByNameContainingIgnoreCase(name);
    }

    @Caching(evict = {
            @CacheEvict(value = "allSuperheroesCache", allEntries = true),
            @CacheEvict(value = "superheroCache", key = "#superhero.id")
    })
    @Override
    public void delete(SuperHero superhero) {
        superHeroRepository.delete(superhero);
    }

    @Caching(evict = {
            @CacheEvict(value = "allSuperheroesCache", allEntries = true)
    })
    @Override
    public SuperHero create(SuperHero superhero) {
        return superHeroRepository.save(superhero);
    }

    @Caching(evict = {
            @CacheEvict(value = "allSuperheroesCache", allEntries = true),
            @CacheEvict(value = "superheroCache", key = "#superhero.id")
    })
    @Override
    public Optional<SuperHero> update(Long id, SuperHero superhero) {
        Optional<SuperHero> superheroDB = superHeroRepository.findById(id);
        if (superheroDB.isPresent()){
            superheroDB.get().setName(superhero.getName());
            SuperHero superHeroSaved = superHeroRepository.save(superheroDB.get());
            return Optional.of(superHeroSaved);
        }
        return superheroDB;
    }
}
