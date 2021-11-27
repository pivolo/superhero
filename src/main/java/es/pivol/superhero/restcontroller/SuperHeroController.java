package es.pivol.superhero.restcontroller;

import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.persistence.SuperHeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superhero")
@AllArgsConstructor
public class SuperHeroController {
    private final SuperHeroRepository superHeroRepository;

    @GetMapping
    public List<SuperHero> getAll(){
        return superHeroRepository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<SuperHero> findById(@PathVariable long id){
        SuperHero superhero = superHeroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found: " + id));
        return ResponseEntity.ok(superhero);

    }

    @GetMapping("/name")
    public List<SuperHero> findByName(@RequestParam String name){
        return superHeroRepository.findByNameContainingIgnoreCase(name);

    }

    @PostMapping
    public SuperHero create(@RequestBody SuperHero superHero){
        return superHeroRepository.save(superHero);
    }

    @PutMapping("{id}")
    public ResponseEntity<SuperHero> update(@PathVariable long id, @RequestBody SuperHero superHero){
        SuperHero superheroDB = superHeroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found: " + id));
        superheroDB.setName(superHero.getName());
        SuperHero superHeroSaved = superHeroRepository.save(superheroDB);
        return ResponseEntity.ok(superHeroSaved);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        SuperHero superhero = superHeroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found: " + id));
        superHeroRepository.delete(superhero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
