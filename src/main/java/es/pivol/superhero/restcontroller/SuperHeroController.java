package es.pivol.superhero.restcontroller;

import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.restcontroller.exception.ResourceNotFoundException;
import es.pivol.superhero.restcontroller.time.LogTime;
import es.pivol.superhero.service.SuperHeroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superhero")
@AllArgsConstructor
public class SuperHeroController {
    private final SuperHeroService superHeroService;

    @GetMapping
    @LogTime
    public List<SuperHero> getAll(){
        return superHeroService.findAll();
    }


    @GetMapping("{id}")
    @LogTime
    public ResponseEntity<SuperHero> findById(@PathVariable long id){
        SuperHero superhero = superHeroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found: " + id));
        return ResponseEntity.ok(superhero);

    }

    @GetMapping("/name")
    @LogTime
    public List<SuperHero> findByName(@RequestParam String name){
        return superHeroService.findByNameContainingIgnoreCase(name);

    }

    @PostMapping
    @LogTime
    public SuperHero create(@RequestBody SuperHero superHero){
        return superHeroService.create(superHero);
    }

    @PutMapping("{id}")
    @LogTime
    public ResponseEntity<SuperHero> update(@PathVariable long id, @RequestBody SuperHero superHero){
        SuperHero superheroSaved = superHeroService.update(id, superHero)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found: " + id));
        return ResponseEntity.ok(superheroSaved);
    }
    @DeleteMapping("{id}")
    @LogTime
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        SuperHero superhero = superHeroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found: " + id));
        superHeroService.delete(superhero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
